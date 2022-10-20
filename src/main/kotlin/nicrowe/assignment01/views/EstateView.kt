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

    fun menu(): Int {

        var option: Int
        var input: String?

        println("MAIN MENU")
        println(" 1. List Estates")
        println(" 2. Search Estates")
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
}
