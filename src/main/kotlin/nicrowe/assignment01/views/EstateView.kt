package nicrowe.assignment01.views


import nicrowe.assignment01.models.EstateModel
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class EstateView {
    val con = DriverManager.getConnection("jdbc:mysql://localhost:3306/realestate", "root", "")
    val st = con!!.createStatement()
    var rs: ResultSet? = null
    var ps: PreparedStatement? = null
    var id: Long = 0; var name = ""; var phonenumber = 0; var type = ""; var address = ""; var city = ""; var county = ""; var eircode = ""; var estimated = 0; var residents = 0

    fun menu(): Int {

        var option: Int
        var input: String?

        println("MAIN MENU")
        println(" 1. List Estates")
        println(" 2. Search Estates")
        println(" 3. Add Estate")
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

    fun listEstates() {
        try {
            rs = st.executeQuery("select * from estates")
                while (rs!!.next()) {
                    println(
                        "ID: " + rs!!.getString("id") + ", Name: " + rs!!.getString("name") + ", Phone Number: " + rs!!.getString(
                            "phonenumber"
                        ) + ", Type: " + rs!!.getString("type") + ", Street/Building: " + rs!!.getString("address") + ", City: " + rs!!.getString(
                            "city"
                        ) + ", County: " + rs!!.getString("county") + ", Eircode: " + rs!!.getString("eircode") + ", Estimated Value: " + rs!!.getString(
                            "estimated"
                        ) + ", Number of Residents: " + rs!!.getString("residents"))
            }
            }catch (ex: SQLException) {
        }
    }

    fun searchEstate(){
        println()
        println("Enter an ID to search: ")
        var inputID : String = readLine()!!
        try{
            rs = st.executeQuery("select * from estates where id = " + inputID)
            if (rs!!.next()) {
                println(
                    "ID: " + rs!!.getString("id") + ", Name: " + rs!!.getString("name") + ", Phone Number: " + rs!!.getString(
                        "phonenumber"
                    ) + ", Type: " + rs!!.getString("type") + ", Street/Building: " + rs!!.getString("address") + ", City: " + rs!!.getString(
                        "city"
                    ) + ", County: " + rs!!.getString("county") + ", Eircode: " + rs!!.getString("eircode") + ", Estimated Value: " + rs!!.getString(
                        "estimated"
                    ) + ", Number of Residents: " + rs!!.getString("residents")
                )
            }
        }catch (ex: SQLException) {
            println("Invalid Option")
        }
    }

    fun addEstate() : Any {
        try{
            var addEstate : String = "insert into estates (id, name, phonenumber, type, address, city, county, eircode, estimated, residents) values (?,?,?,?,?,?,?,?,?,?)"
            ps = con!!.prepareStatement(addEstate)
            listEstates()
            println()
            println("Enter a new ID: ")
            var estateid = readLine()!!
            if (estateid == "" || !estateid.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
                println("You must enter a number for this category")
                println()
                return false
            }
            id = estateid.toLong()
            print("Enter your full name: ")
            name = readLine()!!
            print("Enter your phone number: ")
            var estatePhone = readLine()!!
            if (estatePhone == "" || !estatePhone.matches("-?[0-9]+(\\.[0-9]+)?".toRegex()) || estatePhone.length > 16 || estatePhone.length < 4){
                println("You must enter a valid phone number for this category.")
                println()
                return false
            }
            phonenumber = estatePhone.toInt()
            print("Enter Estate type (House, Apartment, Bungalow, Condominium, Mansion or Villa): ")
            type = readLine()!!
            print("Enter an Address, e.g. 506 Greystone Street, 20 Collins Hall: ")
            address = readLine()!!
            print("Enter City: ")
            city = readLine()!!
            print("Enter County: ")
            county = readLine()!!
            print("Enter Eircode: ")
            eircode = readLine()!!
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
            residents = estateResidents.toInt()

            return if (name.isNotEmpty() && type.isNotEmpty() && address.isNotEmpty() && city.isNotEmpty() && county.isNotEmpty() && eircode.isNotEmpty()){
                ps!!.setString(1, id.toString())
                ps!!.setString(2, name)
                ps!!.setString(3, phonenumber.toString())
                ps!!.setString(4, type)
                ps!!.setString(5, address)
                ps!!.setString(6, city)
                ps!!.setString(7, county)
                ps!!.setString(8, eircode)
                ps!!.setString(9, estimated.toString())
                ps!!.setString(10, residents.toString())
                ps!!.execute()
                true
            }
            else {
                println()
                println("You must enter data for every category")
                false
            }
            } catch (ex: SQLException){
                println()
            println("An error has occurred. The most likely error is that an ID that was already in use was entered. Please try again or exit the app")
        }
        return ""
    }
}
