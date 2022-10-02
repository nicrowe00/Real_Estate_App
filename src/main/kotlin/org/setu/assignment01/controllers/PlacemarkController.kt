package org.setu.assignment01.controllers;

import org.setu.assignment01.models.PlacemarkJSONStore
import org.setu.assignment01.models.PlacemarkModel
import org.setu.assignment01.views.PlacemarkView

class PlacemarkController {
    val placemarks = PlacemarkJSONStore()
    val placemarkView = PlacemarkView()

    init {
        println("Launching Placemark Console App" )
        println("Placemark Kotlin App Version 3.0")
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
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        println("Shutting Down Placemark Console App")
    }

    fun menu(): Int {
            return placemarkView.menu()
    }

    fun add() {
        var aPlacemark = PlacemarkModel()

        if (placemarkView.addPlacemarkData(aPlacemark))
                placemarks.create(aPlacemark)
        else
                println("Placemark Not Added")
    }

    fun list() {
        placemarkView.listPlacemarks(placemarks)
    }

    fun update() {

        placemarkView.listPlacemarks(placemarks)
        var searchId = placemarkView.getId()
        val aPlacemark = search(searchId)

        if (aPlacemark != null) {
            if (placemarkView.updatePlacemarkData(aPlacemark)) {
                placemarks.update(aPlacemark)
                placemarkView.showPlacemark(aPlacemark)
                println("Placemark Updated : [ $aPlacemark ]")
            } else
                println("Placemark Not Updated")
        } else
            println("Placemark Not Updated...")
    }

    fun delete() {
        placemarkView.listPlacemarks(placemarks)
        var searchId = placemarkView.getId()
        val aPlacemark = search(searchId)

        if(aPlacemark != null) {
            placemarks.delete(aPlacemark)
            println("Placemark Deleted...")
            placemarkView.listPlacemarks(placemarks)
        }
        else
            println("Placemark Not Deleted...")
        }

    fun search() {
        val aPlacemark = search(placemarkView.getId())!!
        placemarkView.showPlacemark(aPlacemark)
    }


    fun search(id: Long): PlacemarkModel? {
        var foundPlacemark = placemarks.findOne(id)
        return foundPlacemark
    }
}
