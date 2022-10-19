package nicrowe.assignment01.controllers;

import nicrowe.assignment01.models.EstateJSONStore
import nicrowe.assignment01.models.EstateModel
import nicrowe.assignment01.views.EstateView

class EstateController {
    val estates = EstateJSONStore()
    val estateView = EstateView()

    init {
        println("Launching Real Estate App" )
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                6 -> deleteAll()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        println("Shutting Down Real Estate App")
    }

    fun menu(): Int {
            return estateView.menu()
    }

    fun add() {
        val anEstate = EstateModel()

        if (estateView.addEstateData(anEstate))
                estates.createEstate(anEstate)
        else
                println("Estate Not Added")
    }

    fun list() {
        estateView.listEstates(estates)
    }

    fun update() {

        estateView.listEstates(estates)
        var searchId = estateView.getId()
        val anEstate = search(searchId)

        if (anEstate != null) {
            if (estateView.updateEstateData(anEstate)) {
                estates.updateEstate(anEstate)
                estateView.showEstate(anEstate)
                println("Estate Updated : [ $anEstate ]")
            } else
                println("Estate Not Updated")
        } else
            println("Estate Not Updated...")
    }

    fun delete() {
        estateView.listEstates(estates)
        var searchId = estateView.getId()
        val anEstate = search(searchId)

        if(anEstate != null) {
            estates.deleteEstate(anEstate)
            println("Estate Deleted...")
            estateView.listEstates(estates)
        }
        else
            println("Estate Not Found")
        }

    fun deleteAll() {
        if (estates.estates.isEmpty()){
            println("No Estates to delete")
        } else {
            estateView.deleteEstates(estates)
            println("All Estates Deleted")
        }
    }

    fun search() {
        val anEstate = search(estateView.getId())!!
        estateView.showEstate(anEstate)
    }


    fun search(id: Long): EstateModel? {
        var foundEstate = estates.findOne(id)
        return foundEstate
    }
}
