package nicrowe.assignment01.controllers;

import nicrowe.assignment01.views.EstateView

class EstateController {
    val estateView = EstateView()

    init {
        println("Launching Real Estate App")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> list()
                2 -> search()
                3 -> add()
                4 -> update()
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

    private fun list() {
        println("List All Estates")
        estateView.listEstates()
    }

    fun search(){
        estateView.searchEstate()
    }

    fun add(){
        println("Add an Estate")
        println()
        estateView.addEstate()
    }

    fun update(){
        estateView.updateEstate()
    }
}
