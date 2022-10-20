package nicrowe.assignment01.views


import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class EstateView {
    var rs: ResultSet? = null
    var ps: PreparedStatement? = null
    val con = DriverManager.getConnection("jdbc:mysql://localhost:3306/realestate", "root", "")
    val st = con!!.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE
    )
    var id = ""; var name = ""; var phonenumber = ""; var type = ""; var address = ""; var city = ""; var county = ""; var eircode = ""; var estimated = ""; var residents = ""

    fun menu(): Int {

        var option: Int
        var input: String?

        println("MAIN MENU")
        println(" 1. List Estates")
        println(" 2. Search Estates")
        println(" 3. Add Estate")
        println(" 4. Update Estate")
        println(" 5. Delete Estate")
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
        var inputID = readLine()!!
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
            var addEstate = "insert into estates (id, name, phonenumber, type, address, city, county, eircode, estimated, residents) values (?,?,?,?,?,?,?,?,?,?)"
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
            id = estateid
            print("Enter your full name: ")
            name = readLine()!!
            print("Enter your phone number: ")
            var estatePhone = readLine()!!
            if (estatePhone == "" || !estatePhone.matches("-?[0-9]+(\\.[0-9]+)?".toRegex()) || estatePhone.length > 16 || estatePhone.length < 4){
                println("You must enter a valid phone number for this category.")
                println()
                return false
            }
            phonenumber = estatePhone
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
            residents = estateResidents

            return if (name.isNotEmpty() && type.isNotEmpty() && address.isNotEmpty() && city.isNotEmpty() && county.isNotEmpty() && eircode.isNotEmpty()){
                ps!!.setString(1, id)
                ps!!.setString(2, name)
                ps!!.setString(3, phonenumber)
                ps!!.setString(4, type)
                ps!!.setString(5, address)
                ps!!.setString(6, city)
                ps!!.setString(7, county)
                ps!!.setString(8, eircode)
                ps!!.setString(9, estimated)
                ps!!.setString(10, residents)
                ps!!.execute()
                println("Estate added.")
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

    fun updateEstate() : Any {
        try {
            println("Update an Estate")
            println()
            listEstates()
            println()
            println("Enter an Estate ID to update: ")
            var inputID = readLine()!!
            if (inputID == "" || !inputID.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())) {
                println("You must enter a number")
                false
            }
            var inputIDUpdate = inputID
            var updateEstate =
                "update estates set id=?, name=?, phonenumber=?, type=?, address=?, city=?, county=?, eircode=?, estimated=?, residents=? where id=?"
            ps = con!!.prepareStatement(updateEstate)
            println("Enter a new ID (current ID cannot be used): ")
            var updateID = readLine()!!
            if (updateID == "" || !inputID.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())) {
                println("You must enter a number for this category")
                false
            }
            id = updateID
            print("Enter a new full name: ")
            name = readLine()!!
            print("Enter a new phone number: ")
            var updatePhone = readLine()!!
            if (updatePhone == "" || !updatePhone.matches("-?[0-9]+(\\.[0-9]+)?".toRegex()) || updatePhone.length > 16 || updatePhone.length < 4) {
                println("You must enter a valid phone number for this category.")
                println()
                return false
            }
            phonenumber = updatePhone
            print("Enter a new Estate type (House, Apartment, Bungalow, Condominium, Mansion or Villa): ")
            type = readLine()!!
            print("Enter an new Address, e.g. 506 Greystone Street, 20 Collins Hall: ")
            address = readLine()!!
            print("Enter a new City: ")
            city = readLine()!!
            print("Enter a new County: ")
            county = readLine()!!
            print("Enter a new Eircode: ")
            eircode = readLine()!!
            print("Enter a new Estimated Value of Estate: ")
            var updateEstimated = readLine()!!
            if (updateEstimated == "" || !updateEstimated.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())) {
                println("You must enter a number for this category.")
                println()
                return false
            }
            print("Enter number of Residents in Estate: ")
            var updateResidents = readLine()!!
            if (updateResidents == "" || !updateResidents.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())) {
                println("You must enter a number for this category.")
                println()
                return false
            }
            residents = updateResidents

            return if (name.isNotEmpty() && type.isNotEmpty() && address.isNotEmpty() && city.isNotEmpty() && county.isNotEmpty() && eircode.isNotEmpty()) {
                ps!!.setString(1, id)
                ps!!.setString(2, name)
                ps!!.setString(3, phonenumber)
                ps!!.setString(4, type)
                ps!!.setString(5, address)
                ps!!.setString(6, city)
                ps!!.setString(7, county)
                ps!!.setString(8, eircode)
                ps!!.setString(9, estimated)
                ps!!.setString(10, residents)
                ps!!.setString(11, inputIDUpdate)
                ps!!.executeUpdate()
                println("Estate updated")
                true
            } else {
                println()
                println("You must enter data for every category")
                false
            }
        } catch (ex: SQLException){
            println("An error has occurred. The most likely error is that an ID that was already in use was entered. Please try again or exit the app")
        }
        return ""
    }

    fun deleteEstate() : Any {
        try {
            println("Delete an Estate")
            println()
            listEstates()
            println()
            println("Select an Estate to delete by its ID: ")
            var inputID = readLine()!!
            if (inputID == "" || !inputID.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())) {
                println("You must enter a number")
                false
            }
            var deleteID = inputID
            var deleteQuery = ("delete from estates where id=" + deleteID)
            ps = con!!.prepareStatement(deleteQuery)
            rs = st.executeQuery("select * from estates where id=" + deleteID)
            if(rs!!.next()) {
                ps!!.executeUpdate()
                println("Estate deleted.")
                true
            }
            else{
                println("There was no Estate to delete.")
            }
        } catch (ex: SQLException){
            println("An error has occurred.")
        }
        return ""
    }

    fun deleteAllEstates() : Any {
        try{
            println("Delete All Estates")
            println()
            var deleteAllQuery = "delete from estates"
            ps = con!!.prepareStatement(deleteAllQuery)
            rs = st!!.executeQuery("select * from estates")
            if(rs!!.next()){
                ps!!.executeUpdate()
                println("All Estates Deleted.")
                true
            } else {
                println("No Estates to Delete.")
                false
            }
        } catch (ex: SQLException){
            println("An error has occurred.")
        }
        return ""
    }
}
