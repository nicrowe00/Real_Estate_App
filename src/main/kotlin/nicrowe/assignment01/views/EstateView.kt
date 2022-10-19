package nicrowe.assignment01.views

import nicrowe.assignment01.helpers.read
import nicrowe.assignment01.models.EstateJSONStore
import nicrowe.assignment01.models.EstateModel

class EstateView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Estate")
        println(" 2. Update Estate")
        println(" 3. List All Estates ")
        println(" 4. Search Estates")
        println(" 5. Delete Estates")
        println(" 6. Delete All Estates")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listEstates(estates : EstateJSONStore) {
        println("List All Estates")
        estates.printAll()
        println()
    }

    fun deleteEstates(estates: EstateJSONStore) {
        estates.deleteAllEstates()
    }

    fun showEstate(estate : EstateModel) {
        if(estate != null)
            println("Estate Details [ $estate ]")
        else
            println("Estate Not Found...")
    }

    fun addEstateData(estate : EstateModel) : Boolean {

        println()
        print("Enter your full name: ")
        estate.name = readLine()!!
        print("Enter your phone number: ")
        var estatePhone = readLine()!!
        if (estatePhone == "" || !estatePhone.matches("-?[0-9]+(\\.[0-9]+)?".toRegex()) || estatePhone.length > 16 || estatePhone.length < 4){
            println("You must enter a valid phone number for this category.")
            println()
            return false
        }
        estate.phonenumber = estatePhone.toInt()
        print("Enter Estate type (House, Apartment, Bungalow, Condominium, Mansion or Villa): ")
        estate.type = readLine()!!
        print("Enter an Address, e.g. 506 Greystone Street, 20 Collins Hall: ")
        estate.address = readLine()!!
        print("Enter City: ")
        estate.city = readLine()!!
        print("Enter County: ")
        estate.county = readLine()!!
        print("Enter Eircode: ")
        estate.eircode = readLine()!!
        print("Enter Estimated Value of Estate: ")
        var estateEstimated = readLine()!!
        if (estateEstimated == "" || !estateEstimated.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
            println("You must enter a number for this category.")
            println()
           return false
        }
        print("Enter number of Residents in Estate: ")
        var estateResidents = readLine()!!
        if (estateResidents == "" || !estateResidents.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
            println("You must enter a number for this category.")
            println()
            return false
        }
        estate.residents = estateResidents.toInt()

        return if (estate.name.isNotEmpty() && estate.type.isNotEmpty() && estate.address.isNotEmpty() && estate.city.isNotEmpty() && estate.county.isNotEmpty() && estate.eircode.isNotEmpty()){
            true
        } else {
            println()
            println("You must enter data for every category.")
            false
        }

    }

    fun updateEstateData(estate : EstateModel) : Boolean {

        val tempName: String?
        val tempPhoneNumber: Int?
        val tempType: String?
        val tempAddress: String?
        val tempCity: String?
        val tempCounty: String?
        val tempEircode: String?
        val tempEstimated: Int?
        val tempResidents: Int?

        if (estate != null) {
            print("Enter a new Name (Previous Name: " + estate.name + ") : ")
            tempName = readLine()
            print("Enter a new Phone Number (Previous Name: " + estate.phonenumber + ") : ")
            var estatePhone = readLine()!!
            if (estatePhone == "" || !estatePhone.matches("-?[0-9]+(\\.[0-9]+)?".toRegex()) || estatePhone.length > 16 || estatePhone.length < 4){
                println("You must enter a valid phone number for this category.")
                println()
                return false
            }
            tempPhoneNumber = estatePhone.toInt()
            print("Enter a new Estate Type (Previous Type: " + estate.type + ") : ")
            tempType = readLine()!!
            print("Enter a new Address (Previous Address: " + estate.address + ") : ")
            tempAddress = readLine()!!
            print("Enter a new City (Previous Type: " + estate.city + ") : ")
            tempCity = readLine()!!
            print("Enter a new County (Previous Type: " + estate.county + ") : ")
            tempCounty = readLine()!!
            print("Enter a new Eircode (Previous Eircode: " + estate.eircode + ") : ")
            tempEircode = readLine()!!
            print("Enter a new Estimated Value (Previous Type: " + estate.estimated + ") : ")
            var estateEstimated = readLine()!!
            if (estateEstimated == "" || !estateEstimated.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
                println("You must enter a number for this category.")
                println()
                return false
            }
            tempEstimated = estateEstimated.toInt()
            print("Enter a new number of Residents (Previous Number: " + estate.residents + ") : ")
            var estateResidents = readLine()!!
            if (estateResidents == "" || !estateResidents.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
                println("You must enter a number for this category.")
                println()
                return false
            }
            tempResidents = estateResidents.toInt()

            if (!tempName.isNullOrEmpty() && !tempType.isNullOrEmpty() && !tempAddress.isNullOrEmpty() && !tempCity.isNullOrEmpty() && !tempCounty.isNullOrEmpty() && !tempEircode.isNullOrEmpty()) {
                estate.name = tempName
                estate.phonenumber = tempPhoneNumber
                estate.type = tempType
                estate.address = tempAddress
                estate.city = tempCity
                estate.county = tempCounty
                estate.eircode = tempEircode
                estate.estimated = tempEstimated
                estate.residents = tempResidents
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update/Delete : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}