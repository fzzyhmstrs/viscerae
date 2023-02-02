package me.fzzyhmstrs.viscerae.entity.block

import com.google.common.collect.ArrayListMultimap
import com.google.gson.*
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.viscerae.Viscerae
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.loot.context.LootContext
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.LootNumberProvider
import net.minecraft.loot.provider.number.LootNumberProviderTypes
import net.minecraft.resource.Resource
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceType
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

object Trials: SimpleSynchronousResourceReloadListener {

    private val trialMap: MutableMap<Identifier,TrialData> = mutableMapOf()
    private val keyMap: ArrayListMultiMap<Item,Identifier> = ArrayListMultiMap.create()
    private val keyRawMap: MutableMap<Identifier,Ingredient> = mutableMapOf()
    private val reloaderId = Identifier(Viscerae.MOD_ID,"trial_reloader")
    private val numberDeserializer = GsonBuilder().registerTypeHierarchyAdapter(LootNumberProvider::class.java,LootNumberProviderTypes.createGsonSerializer()).create()
    private val FALLBACK_LOOT = "gameplay/fallback_trial_loot"

    fun registerTrialsLoader(){
        ResourceManagerHelper.get(ResourceType.SERVER_DATA)
            .registerReloadListener(this)
    }

    fun getTrialData(trial: Identifier): TrialData?{
        return trialMap[trial]
    }
    
    fun checkKeyItem(item: Item, dim: Identifier): List<Identifier>{
        if (keyMap.isEmpty()){
            processKeyMap()
        }
        val list = keyMap.get(item)
        val list2: ArrayList<Identifier> = ArrayList()
        for (id in list){
            val trial = getTrialData(id)?:continue
            if (trial.checkDimension(dim)){
                list2.add(id)
            }
        }
        return list2
    }
    
    private fun processKeyMap(){
        for (entry in keyRawMap){
            val id = entry.key
            for (stack in entry.value.matchingStacks){
                keyMap.put(stack.item,id)
            }
        }
    }

    class TrialData(id: Identifier,
                    private val waveData: ArrayListMultimap<Int, WaveData> = ArrayListMultimap.create(),
                    private val width: Double = 16.0,
                    private val height: Double = 6.0){

        val trialTitle = AcText.translatable("trial.${id.namespace}.${id.path}")
        private var lootTable: Identifier = Identifier(Viscerae.MOD_ID,FALLBACK_LOOT)
        private val dimensions: MutableList<Identifier> = mutableListOf()

        fun withLootId(lootId: Identifier): TrialData{
            this.lootTable = lootId
            return this
        }
        
        fun withDimension(dim: Identifier): TrialData{
            this.dimensions.add(dim)
            return this
        }
        
        fun checkDimension(dim: Identifier): Boolean{
            if (dimensions.isEmpty() return true
            return dimensions.contains(dim)
        }
        
        fun numberOfWaves(): Int{
            return waveData.size()
        }

        fun maxWaveTime(): Int{
            var maxTime = 0
            for (time in waveData.keys()){
                if (time > maxTime){
                    maxTime = time
                }
            }
            return maxTime
        }
        
        fun getWidth(): Double{
            return width
        }
        
        fun getHeight(): Double{
            return height
        }

        fun provideNextWave(tick: Int, world: World, playerEntities: List<PlayerEntity>, waveMultiplier: Float = 1f): List<Identifier>{
            if (world !is ServerWorld) return listOf()
            val dataList = waveData[tick]?:return listOf()
            val list: ArrayList<Identifier> = ArrayList()
            for (data in dataList){
                val contextBuilder = LootContext.Builder(world).random(world.random)
                var maxLuck = 0f
                var negativeLuck = 0f
                for (player in playerEntities){
                    if (player.luck < 0f && player.luck < negativeLuck){
                        negativeLuck = player.luck
                    }else if (player.luck > maxLuck){
                        maxLuck = player.luck
                    }
                }
                if (negativeLuck < 0f){
                    maxLuck = negativeLuck
                }
                val count = ((data.waveCount.nextFloat(contextBuilder.build(LootContextTypes.EMPTY)) + data.luckModifier * maxLuck) * (1 + data.extraPlayerModifier * (playerEntities.size - 1)) * waveMultiplier).toInt()
                if (count <= 0) return listOf()
                for (i in 1..count){
                    list.add(data.mobs[world.random.nextInt(data.mobs.size)])
                }
                if (data.waveMessage != AcText.empty()){
                    for (player in playerEntities){
                        player.sendMessage(data.waveMessage,true)
                    }
                }
            }
            return list
        }

        class WaveData(val mobs: ArrayList<Identifier>, val waveCount: LootNumberProvider, val luckModifier: Float, val extraPlayerModifier: Float, val waveMessage: Text){
        }

    }

    override fun reload(manager: ResourceManager) {
        trialMap.clear()
        keyMap.clear()
        keyRawMap.clear()
        manager.findResources("trial_data"
        ) { path: Identifier ->
            path.path.endsWith(".json")
        }.forEach { (id: Identifier, resource: Resource) ->
            load(
                id,
                resource
            )
        }
    }

    private fun load(id: Identifier, resource: Resource){
        try {
            val reader = resource.reader
            val json = JsonParser.parseReader(reader).asJsonObject
            val mobLists: MutableMap<String,ArrayList<Identifier>> = mutableMapOf()
            val jsonMobLists = json.get("mob_lists")
            if (jsonMobLists != null) {
                if (!jsonMobLists.isJsonObject) {
                    Viscerae.LOGGER.error("Invalid default mob list: $json in trial file: $id")
                } else {
                    val jsonDefaultLists = jsonMobLists.asJsonObject
                    var failed = false
                    for (mobList in jsonDefaultLists.entrySet()) {
                        val mobListElement = mobList.value
                        if (mobListElement !is JsonArray) {
                            Viscerae.LOGGER.error("Invalid default mob list: $mobListElement in trial file: $id")
                            failed = true
                            break
                        } else {
                            val result = deserializeMobList(mobListElement)
                            if (result.first != "") {
                                Viscerae.LOGGER.error("Problem deserializing mob list: [${result.first}] in trial file: $id")
                                failed = true
                                break
                            }
                            mobLists[mobList.key] = result.second
                        }
                    }
                    if (failed){
                        return
                    }
                }

            }
            val jsonWaves = json.get("trial_waves")
            if (jsonWaves == null || !jsonWaves.isJsonArray){
                Viscerae.LOGGER.error("Invalid trials list (not an array) in trial file: $id")
            } else {
                val waves = jsonWaves.asJsonArray
                if (waves.isEmpty){
                    Viscerae.LOGGER.error("empty waves array in trial file: $id")
                    return
                }
                var failed = false
                val waveData: ArrayListMultimap<Int,TrialData.WaveData> = ArrayListMultimap.create()
                for (it in waves) {
                    if (!it.isJsonObject){
                        Viscerae.LOGGER.error("Invalid trial object $it in trial file: $id")
                        failed = true
                        break
                    } else {
                        val jsonTime = (it as JsonObject).get("wave_time")
                        val waveTime = if (jsonTime == null){
                            0
                        } else {
                            if (!jsonTime.isJsonPrimitive){
                                Viscerae.LOGGER.error("Invalid luck modifier $it in trial file: $id")
                                failed = true
                                break
                            } else {
                                jsonTime.asInt
                            }
                        }
                        val jsonTrial = it.get("wave_count")
                        val numberProvider: LootNumberProvider = if (jsonTrial == null){
                            ConstantLootNumberProvider.create(5f)
                        } else {
                            numberDeserializer.fromJson(jsonTrial, LootNumberProvider::class.java)
                        }
                        val jsonLuck = it.get("luck_modifier")
                        val luckModifier = if (jsonLuck == null){
                            0f
                        } else {
                            if (!jsonLuck.isJsonPrimitive){
                                Viscerae.LOGGER.error("Invalid luck modifier $it in trial file: $id")
                                failed = true
                                break
                            } else {
                                jsonLuck.asFloat
                            }
                        }
                        val jsonMultiplier = it.get("luck_modifier")
                        val playerModifier = if (jsonMultiplier == null){
                            0f
                        } else {
                            if (!jsonMultiplier.isJsonPrimitive){
                                Viscerae.LOGGER.error("Invalid player modifier $it in trial file: $id")
                                failed = true
                                break
                            } else {
                                jsonMultiplier.asFloat
                            }
                        }
                        val jsonText = it.get("wave_message")
                        val waveMessage = if (jsonText == null){
                            AcText.empty()
                        } else {
                            Text.Serializer.fromJson(jsonText)
                        }
                        if (waveMessage == null){
                            Viscerae.LOGGER.error("Wave message $jsonText null for some reason, in trial file: $id")
                            failed = true
                            break
                        }
                        val jsonMobs = it.get("mobs")
                        var mobs: ArrayList<Identifier> = ArrayList()
                        if (!jsonMobs.isJsonArray){
                            if (jsonMobs.isJsonPrimitive){
                                val jsonMobKey =  jsonMobs.asString
                                if (!mobLists.containsKey(jsonMobKey)){
                                    Viscerae.LOGGER.error("Invalid default mob list reference $it in trial file: $id")
                                } else {
                                    val mobsTemp = mobLists[jsonMobKey]
                                    if (mobsTemp == null){
                                        Viscerae.LOGGER.error("Invalid default mob list reference $it in trial file: $id")
                                        failed = true
                                        break
                                    } else {
                                        mobs = mobsTemp
                                    }
                                }
                            } else {
                                Viscerae.LOGGER.error("Invalid mob list $it (needs to be an array or a reference to a default mob list) in trial file: $id")
                            }
                        } else {
                            val jsonArrayMobs = jsonMobs.asJsonArray
                            val result = deserializeMobList(jsonArrayMobs)
                            if (result.first != "") {
                                Viscerae.LOGGER.error("Problem deserializing mob list: [${result.first}] in trial file: $id")
                                failed = true
                                break
                            }
                            mobs = result.second
                        }
                        if (mobs.isEmpty()){
                            Viscerae.LOGGER.error("Mob list can't be empty; in trial file: $id")
                            failed  = true
                            break
                        }
                        waveData.put(waveTime,TrialData.WaveData(mobs, numberProvider,luckModifier,playerModifier,waveMessage))
                    }
                }
                if (failed){
                    return
                }
                val jsonId = json.get("id")
                if (jsonId == null || !jsonId.isJsonPrimitive){
                    Viscerae.LOGGER.error("Invalid or missing trial identifier $jsonId in trial file: $id")
                    return
                }
                val trialId = Identifier.tryParse(jsonId.asString)
                if (trialId == null){
                    Viscerae.LOGGER.error("Couldn't parse identifier $jsonId in trial file: $id")
                    return
                }
                val jsonKey = json.get("key_item")
                if (jsonKey == null){
                    Viscerae.LOGGER.error("Invalid or missing key item(s) $jsonId in trial file: $id")
                    return
                }
                val key = Ingredient.fromJson(jsonKey)
                val jsonSize = json.get("trial_area")
                val size = if (jsonSize != null && jsonSize.isJsonObject){
                    jsonSizeWidth = (jsonSize as JsonObject).get("width")
                    val width = if (jsonSizeWidth != null && jsonSizeWidth.isJsonPrimitive){
                        jsonSizeWidth.asDouble
                    } else {
                        16.0
                    }
                    jsonSizeHeight = (jsonSize as JsonObject).get("height")
                    val height = if (jsonSizeHeight != null && jsonSizeHeight.isJsonPrimitive){
                        jsonSizeHeight.asDouble
                    } else {
                        6.0
                    }
                    Pair(width,height)
                } else {
                    Pair(16.0,6.0)
                }
                var trialData = TrialData(trialId,waveData,size.left, size.right)
                val jsonLootId = json.get("loot_id")
                if (jsonLootId != null){
                    val lootId = Identifier.tryParse(jsonLootId.asString)
                    if (lootId != null){
                        trialData = trialData.withLootId(lootId)
                    }
                }
                val jsonDims = json.get("dimensions")
                if (jsonDims != null){
                    if (jsonDims.isJsonPrimitive){
                        val dimId = Identifier.tryParse(jsonDims.asString)
                        if (dimId != null){
                            trialData = trialData.withDimension(dimId)
                        }
                    } else if (jsonDims.isJsonArray){
                        for (chk in jsonDims.asJsonArray){
                            if (chk.isJsonPrimitive){
                                val dimId = Identifier.tryParse(chk.asString)
                                if (dimId != null){
                                    trialData = trialData.withDimension(dimId)
                                }
                            }
                        }
                    }
                }
                trialMap[trialId] = trialData
                keyRawMap[trialId] = key
            }
        } catch (e: Exception) {
            Viscerae.LOGGER.error("Failed to open or read trials file: $id")
            e.printStackTrace()
        }
    }

    private fun deserializeMobList(json: JsonArray): Pair<String,ArrayList<Identifier>>{
        val list: ArrayList<Identifier> = ArrayList()
        for (mob in json){
            val result = deserializeMob(mob)
            if (result.first != ""){
                return Pair(result.first, ArrayList())
            }
            list.addAll(result.second)
        }
        return Pair("",list)
    }

    private fun deserializeMob(json:JsonElement): Pair<String,ArrayList<Identifier>>{
        if (!json.isJsonPrimitive){
            if (!json.isJsonObject){
                return Pair("mob $json can't be parsed...", ArrayList())
            } else {
                val jsonObject = json.asJsonObject
                val mob = jsonObject.get("mob") ?: return Pair("mob id missing in $jsonObject...", ArrayList())
                if (!mob.isJsonPrimitive) return Pair("mob id malformed in $jsonObject...", ArrayList())
                val id = Identifier.tryParse(mob.asString)
                if (id == null || !Registry.ENTITY_TYPE.containsId(id)){
                    return Pair("mob id $id null or not found in registry...", ArrayList())
                }
                val weight = jsonObject.get("weight")
                val actualWeight = if (weight == null || !weight.isJsonPrimitive){
                    1
                } else {
                    weight.asInt
                }
                val list: ArrayList<Identifier> = ArrayList()
                for (i in 1..actualWeight){
                    list.add(id)
                }
                return Pair("",list)
            }
        } else {
            val id = Identifier.tryParse(json.asString)
            if (id == null || !Registry.ENTITY_TYPE.containsId(id)){
                return Pair("mob id $id null or not found in registry...", ArrayList())
            }
            return Pair("", ArrayList(listOf(id)))
        }
    }

    override fun getFabricId(): Identifier {
        return reloaderId
    }

}
