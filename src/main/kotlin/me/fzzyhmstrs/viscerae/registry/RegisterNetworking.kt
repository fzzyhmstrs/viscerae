package me.fzzyhmstrs.viscerae.registry

import me.fzzyhmstrs.viscerae.scepter.LeechLifeAugment


object RegisterNetworking {

    fun registerServer(){

    }
    fun registerClient(){
        LeechLifeAugment.registerClient()
        //ImbuingRecipeBookScreen.registerClientReceiver()
    }
}