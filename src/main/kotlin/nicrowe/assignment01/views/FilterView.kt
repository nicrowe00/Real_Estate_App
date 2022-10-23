package nicrowe.assignment01.views

import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException


class FilterView {
    val menuView = MenuView()

    var rs: ResultSet? = null
    val con = DriverManager.getConnection()
    val st = con!!.createStatement()
    fun menu(): Int{
        val option: Int
        val input: String?

        println("Filter Selection")
        println(" 1. House Type")
        println(" 2. Attic")
        println(" 3. Basement")
        println(" 4. City")
        println(" 5. County")
        println()
        print("Enter option for Filter View: ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun filterMenu() {
        val input = menu()
        when (input) {
            1 -> filterHouse()
            2 -> filterAttic()
            3 -> filterBasement()
            4 -> filterCity()
            5 -> filterCounty()
            else -> println("Invalid option entered.")
        }
    }

    fun filterHouse(){
        println("Filter by House Type")
        println()
        var type = ""
        when (menuView.houseMenu()) {
                1 -> type = "Detached House"
                2 -> type = "Semi-Detached House"
                3 -> type = "Terraced House"
                4 -> type = "Apartment"
                5 -> type = "Bungalow"
                6 -> type = "Condominium"
                7 -> type = "Mansion"
                8 -> type = "Villa"
                else -> println("Invalid Option. Please select a house type by entering its corresponding number.")
        }
        rs = st.executeQuery("select * from estates where type = '$type'")
        try {
            while (rs!!.next()) {
                println(
                    "ID: " + rs!!.getString("id") + ", Name: " + rs!!.getString("name") + ", Phone Number: " + rs!!.getString("phonenumber"
                    ) + ", Type: " + rs!!.getString("type") + ", Attic: " + rs!!.getString("Attic") + ", Basement: " + rs!!.getString("basement") +
                            ", Bedrooms: " + rs!!.getString("bedrooms") + ", Bathrooms: " + rs!!.getString("bathrooms") + ", Street/Building: " +
                            rs!!.getString("address") + ", City: " + rs!!.getString("city") + ", County: " + rs!!.getString("county")
                            + ", Eircode: " + rs!!.getString("eircode") + ", Estimated Value: " + rs!!.getString("estimated") + ", Number of Residents: " + rs!!.getString("residents")
                )
            }
        } catch (ex: SQLException){
            ex.printStackTrace()
        }
        }

    fun filterAttic(){
        println("Filter by Estates with or without Attics")
        println()
        var attic = ""
        when (menuView.atticMenu()) {
            1 -> attic = "Yes"
            2 -> attic = "No"
            else -> println("Invalid option. Please select the appropriate option by its corresponding number.")
        }
        rs = st.executeQuery("select * from estates where attic = '$attic'")
        try{
            while (rs!!.next()) {
                println(
                    "ID: " + rs!!.getString("id") + ", Name: " + rs!!.getString("name") + ", Phone Number: " + rs!!.getString("phonenumber"
                    ) + ", Type: " + rs!!.getString("type") + ", Attic: " + rs!!.getString("Attic") + ", Basement: " + rs!!.getString("basement") +
                            ", Bedrooms: " + rs!!.getString("bedrooms") + ", Bathrooms: " + rs!!.getString("bathrooms") + ", Street/Building: " +
                            rs!!.getString("address") + ", City: " + rs!!.getString("city") + ", County: " + rs!!.getString("county")
                            + ", Eircode: " + rs!!.getString("eircode") + ", Estimated Value: " + rs!!.getString("estimated") + ", Number of Residents: " + rs!!.getString("residents")
                )
            }
        } catch (ex: SQLException){
            ex.printStackTrace()
        }
    }

    fun filterBasement(){
        println("Filter by Estates with or without Basements")
        println()
        var basement = ""
        when (menuView.basementMenu()) {
            1 -> basement = "Yes"
            2 -> basement = "No"
            else -> println("Invalid option. Please select the appropriate option by its corresponding number.")
        }
        rs = st.executeQuery("select * from estates where basement = '$basement'")
        try{
            while (rs!!.next()) {
                println(
                    "ID: " + rs!!.getString("id") + ", Name: " + rs!!.getString("name") + ", Phone Number: " + rs!!.getString("phonenumber"
                    ) + ", Type: " + rs!!.getString("type") + ", Attic: " + rs!!.getString("Attic") + ", Basement: " + rs!!.getString("basement") +
                            ", Bedrooms: " + rs!!.getString("bedrooms") + ", Bathrooms: " + rs!!.getString("bathrooms") + ", Street/Building: " +
                            rs!!.getString("address") + ", City: " + rs!!.getString("city") + ", County: " + rs!!.getString("county")
                            + ", Eircode: " + rs!!.getString("eircode") + ", Estimated Value: " + rs!!.getString("estimated") + ", Number of Residents: " + rs!!.getString("residents")
                )
            }
        } catch (ex: SQLException){
            ex.printStackTrace()
        }
    }

    fun filterCity(){
        println("Filter by City")
        println()
        var city: String
        print("Enter the City you wish to Filter by: ")
        city = readLine()!!
        rs = st.executeQuery("select * from estates where city='$city'")
        try{
            while (rs!!.next()){
                println(
                    "ID: " + rs!!.getString("id") + ", Name: " + rs!!.getString("name") + ", Phone Number: " + rs!!.getString("phonenumber"
                    ) + ", Type: " + rs!!.getString("type") + ", Attic: " + rs!!.getString("Attic") + ", Basement: " + rs!!.getString("basement") +
                            ", Bedrooms: " + rs!!.getString("bedrooms") + ", Bathrooms: " + rs!!.getString("bathrooms") + ", Street/Building: " +
                            rs!!.getString("address") + ", City: " + rs!!.getString("city") + ", County: " + rs!!.getString("county")
                            + ", Eircode: " + rs!!.getString("eircode") + ", Estimated Value: " + rs!!.getString("estimated") + ", Number of Residents: " + rs!!.getString("residents")
                )
            }
        } catch (ex: SQLException){
            ex.printStackTrace()
        }
    }

    fun filterCounty(){
        println("Filter by County")
        println()
        var county: String
        print("Enter the County you wish to Filter by: ")
        county = readLine()!!
        rs = st.executeQuery("select * from estates where city='$county'")
        try{
            while (rs!!.next()){
                println(
                    "ID: " + rs!!.getString("id") + ", Name: " + rs!!.getString("name") + ", Phone Number: " + rs!!.getString("phonenumber"
                    ) + ", Type: " + rs!!.getString("type") + ", Attic: " + rs!!.getString("Attic") + ", Basement: " + rs!!.getString("basement") +
                            ", Bedrooms: " + rs!!.getString("bedrooms") + ", Bathrooms: " + rs!!.getString("bathrooms") + ", Street/Building: " +
                            rs!!.getString("address") + ", City: " + rs!!.getString("city") + ", County: " + rs!!.getString("county")
                            + ", Eircode: " + rs!!.getString("eircode") + ", Estimated Value: " + rs!!.getString("estimated") + ", Number of Residents: " + rs!!.getString("residents")
                )
            }
        } catch (ex: SQLException){
            ex.printStackTrace()
        }
    }

    }
