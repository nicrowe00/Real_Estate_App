package nicrowe.assignment01.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import nicrowe.assignment01.helpers.exists
import nicrowe.assignment01.helpers.read
import nicrowe.assignment01.helpers.write

import java.util.*


val JSON_FILE = "estates.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<EstateModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class EstateJSONStore : EstateStore {

    var estates = mutableListOf<EstateModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAllEstates(): MutableList<EstateModel> {
        return estates
    }

    override fun findOne(id: Long) : EstateModel? {
        var foundEstate: EstateModel? = estates.find { e -> e.id == id }
        return foundEstate
    }

    override fun createEstate(estate: EstateModel) {
        estate.id = generateRandomId()
        estates.add(estate)
        serialize()
    }

    override fun updateEstate(estate: EstateModel) {
        var foundEstate = findOne(estate.id!!)
        if (foundEstate != null) {
            foundEstate.name = estate.name
            foundEstate.phonenumber = estate.phonenumber
            foundEstate.type = estate.type
            foundEstate.address = estate.address
            foundEstate.city = estate.city
            foundEstate.county = estate.county
            foundEstate.eircode = estate.eircode
            foundEstate.estimated = estate.estimated
            foundEstate.residents = estate.residents
        }
        serialize()
    }

    override fun deleteEstate(estate: EstateModel) {
        estates.remove(estate)
        serialize()
    }

    override fun deleteAllEstates(){
        estates.removeAll(estates)
        serialize()
    }

    internal fun printAll() {
        estates.forEach { println("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(estates, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        estates = Gson().fromJson(jsonString, listType)
    }
}


