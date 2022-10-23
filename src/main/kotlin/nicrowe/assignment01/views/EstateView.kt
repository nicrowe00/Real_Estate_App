package nicrowe.assignment01.views


import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class EstateView {
    val houseView = MenuView()

    var rs: ResultSet? = null
    var ps: PreparedStatement? = null
    val con = DriverManager.getConnection()
    val st = con!!.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE
    )
    var id = ""; var name = ""; var phonenumber = ""; var type = ""; var bathrooms = ""; var bedrooms = ""; var attic = ""; var basement = ""; var address = ""; var city = ""; var county = ""; var eircode = ""; var estimated = ""; var residents = ""

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
        println(" 7. Filter List")
        println("-1. Exit")
        println()
        print("Enter Option: ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listEstates() {
        try {3
            rs = st.executeQuery("select * from estates")
                while (rs!!.next()) {
                    println(
                        "ID: " + rs!!.getString("id") + ", Name: " + rs!!.getString("name") + ", Phone Number: " + rs!!.getString("phonenumber"
                        ) + ", Type: " + rs!!.getString("type") + ", Attic: " + rs!!.getString("Attic") + ", Basement: " + rs!!.getString("basement") +
                                ", Bedrooms: " + rs!!.getString("bedrooms") + ", Bathrooms: " + rs!!.getString("bathrooms") + ", Street/Building: " +
                                rs!!.getString("address") + ", City: " + rs!!.getString("city") + ", County: " + rs!!.getString("county")
                                + ", Eircode: " + rs!!.getString("eircode") + ", Estimated Value: " + rs!!.getString("estimated") + ", Number of Residents: " + rs!!.getString("residents")
                    )
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
                    "ID: " + rs!!.getString("id") + ", Name: " + rs!!.getString("name") + ", Phone Number: " + rs!!.getString("phonenumber"
                    ) + ", Type: " + rs!!.getString("type") + ", Attic: " + rs!!.getString("Attic") + ", Basement: " + rs!!.getString("basement") +
                            ", Bedrooms: " + rs!!.getString("bedrooms") + ", Bathrooms: " + rs!!.getString("bathrooms") + ", Street/Building: " +
                            rs!!.getString("address") + ", City: " + rs!!.getString("city") + ", County: " + rs!!.getString("county")
                            + ", Eircode: " + rs!!.getString("eircode") + ", Estimated Value: " + rs!!.getString("estimated") + ", Number of Residents: " + rs!!.getString("residents")
                )
            }
        }catch (ex: SQLException) {
            println("Invalid Option")
        }
    }

    fun addEstate() : Any {
        try{
            val input: Int

            val addEstate = "insert into estates (id, name, phonenumber, type, attic, basement, bedrooms, bathrooms,  address, city, county, eircode, estimated, residents) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
            ps = con!!.prepareStatement(addEstate)
            listEstates()
            println()
            print("Enter a new ID: ")
            val estateid = readLine()!!
            if (estateid == "" || !estateid.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
                println("You must enter a number for this category")
                println()
                return false
            }
            id = estateid
            print("Enter your full name: ")
            name = readLine()!!
            print("Enter your phone number: ")
            val estatePhone = readLine()!!
            if (estatePhone == "" || !estatePhone.matches("-?[0-9]+(\\.[0-9]+)?".toRegex()) || estatePhone.length > 16 || estatePhone.length < 4){
                println("You must enter a valid phone number for this category.")
                println()
                return false
            }
            phonenumber = estatePhone
            input = houseView.houseMenu()
            when (input) {
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
            if (type == "Apartment" || type == "Bungalow"){
                attic = "No"
            }else{
                print("Does your estate have an attic? Enter Yes or No: ")
                attic = readLine()!!
            }
            if (type == "Apartment"){
                basement = "No"
            }else{
                print("Does your Estate have a basement? Enter Yes or No: ")
                basement = readLine()!!
            }
            print("Enter the number of bedrooms: ")
            val estateBedrooms = readLine()!!
            if (estateBedrooms == "" || !estateBedrooms.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
                println("You must enter a number for this category.")
                println()
                return false
            }
            bedrooms = estateBedrooms
            print("Enter the number of bathrooms: ")
            val estateBathrooms = readLine()!!
            if (estateBathrooms == "" || !estateBathrooms.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
                println("You must enter a number for this category")
                println()
                return false
            }
            bathrooms = estateBathrooms
            print("Enter an Address, e.g. 506 Greystone Street, 20 Collins Hall: ")
            address = readLine()!!
            print("Enter City: ")
            city = readLine()!!
            print("Enter County: ")
            county = readLine()!!
            print("Enter Eircode: ")
            eircode = readLine()!!
            print("Enter Estimated Value of Estate: ")
            val estateEstimated = readLine()!!
            if (estateEstimated == "" || !estateEstimated.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
                println("You must enter a number for this category.")
                println()
                return false
            }
            estimated = estateEstimated
            print("Enter number of Residents in Estate: ")
            val estateResidents = readLine()!!
            if (estateResidents == "" || !estateResidents.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
                println("You must enter a number for this category.")
                println()
                return false
            }
            residents = estateResidents

            return if (name.isNotEmpty() && type.isNotEmpty() &&attic.isNotEmpty() && basement.isNotEmpty() && address.isNotEmpty() && city.isNotEmpty() && county.isNotEmpty() && eircode.isNotEmpty()){
                ps!!.setString(1, id)
                ps!!.setString(2, name)
                ps!!.setString(3, phonenumber)
                ps!!.setString(4, type)
                ps!!.setString(5, attic)
                ps!!.setString(6, basement)
                ps!!.setString(7, bedrooms)
                ps!!.setString(8, bathrooms)
                ps!!.setString(9, address)
                ps!!.setString(10, city)
                ps!!.setString(11, county)
                ps!!.setString(12, eircode)
                ps!!.setString(13, estimated)
                ps!!.setString(14, residents)
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
                ex.printStackTrace()
        }
        return ""
    }

    fun updateEstate() : Any {
        try {
            val input: Int
            println("Update an Estate")
            listEstates()
            println()
            println("Enter an Estate ID to update: ")
            val inputID = readLine()!!
            if (inputID == "" || !inputID.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())) {
                println("You must enter a number")
                return false
            }
            val inputIDUpdate = inputID
            val updateEstate = "update estates set id=?, name=?, phonenumber=?, type=?, attic=?, basement=?, bedrooms=?, bathrooms=?, address=?, city=?, county=?, eircode=?, estimated=?, residents=? where id=?"
            ps = con!!.prepareStatement(updateEstate)
            print("Enter a new ID (current ID cannot be used): ")
            val estateid = readLine()!!
            if (estateid == "" || !estateid.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
                println("You must enter a number for this category")
                println()
                return false
            }
            id = estateid
            print("Enter your full name: ")
            name = readLine()!!
            print("Enter your phone number: ")
            val estatePhone = readLine()!!
            if (estatePhone == "" || !estatePhone.matches("-?[0-9]+(\\.[0-9]+)?".toRegex()) || estatePhone.length > 16 || estatePhone.length < 4){
                println("You must enter a valid phone number for this category.")
                println()
                return false
            }
            phonenumber = estatePhone
            input = houseView.houseMenu()
            when (input) {
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
            if (type == "Apartment" || type == "Bungalow"){
                attic = "No"
            }else{
                print("Does your estate have an attic? Enter Yes or No: ")
                attic = readLine()!!
            }
            if (type == "Apartment"){
                basement = "No"
            }else{
                print("Does your Estate have a basement? Enter Yes or No: ")
                basement = readLine()!!
            }
            print("Enter the number of bedrooms: ")
            val estateBedrooms = readLine()!!
            if (estateBedrooms == "" || !estateBedrooms.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
                println("You must enter a number for this category.")
                println()
                return false
            }
            bedrooms = estateBedrooms
            print("Enter the number of bathrooms: ")
            val estateBathrooms = readLine()!!
            if (estateBathrooms == "" || !estateBathrooms.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
                println("You must enter a number for this category")
                println()
                return false
            }
            bathrooms = estateBathrooms
            print("Enter an Address, e.g. 506 Greystone Street, 20 Collins Hall: ")
            address = readLine()!!
            print("Enter City: ")
            city = readLine()!!
            print("Enter County: ")
            county = readLine()!!
            print("Enter Eircode: ")
            eircode = readLine()!!
            print("Enter Estimated Value of Estate: ")
            val estateEstimated = readLine()!!
            if (estateEstimated == "" || !estateEstimated.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
                println("You must enter a number for this category.")
                println()
                return false
            }
            estimated = estateEstimated
            print("Enter number of Residents in Estate: ")
            val estateResidents = readLine()!!
            if (estateResidents == "" || !estateResidents.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())){
                println("You must enter a number for this category.")
                println()
                return false
            }
            residents = estateResidents

            return if (name.isNotEmpty() && type.isNotEmpty() &&attic.isNotEmpty() && basement.isNotEmpty() && address.isNotEmpty() && city.isNotEmpty() && county.isNotEmpty() && eircode.isNotEmpty()){
                ps!!.setString(1, id)
                ps!!.setString(2, name)
                ps!!.setString(3, phonenumber)
                ps!!.setString(4, type)
                ps!!.setString(5, attic)
                ps!!.setString(6, basement)
                ps!!.setString(7, bedrooms)
                ps!!.setString(8, bathrooms)
                ps!!.setString(9, address)
                ps!!.setString(10, city)
                ps!!.setString(11, county)
                ps!!.setString(12, eircode)
                ps!!.setString(13, estimated)
                ps!!.setString(14, residents)
                ps!!.setString(15, inputIDUpdate)
                ps!!.executeUpdate()
                println("Estate updated")
                true
            } else {
                println()
                println("You must enter data for every category")
                false
            }
        } catch (ex: SQLException){
            ex.printStackTrace()
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
            val inputID = readLine()!!
            if (inputID == "" || !inputID.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())) {
                println("You must enter a number")
                return false
            }
            val deleteID = inputID
            val deleteQuery = ("delete from estates where id=" + deleteID)
            ps = con!!.prepareStatement(deleteQuery)
            rs = st.executeQuery("select * from estates where id=" + deleteID)
            if(rs!!.next()) {
                ps!!.executeUpdate()
                println("Estate deleted.")
                return true
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
            val deleteAllQuery = "delete from estates"
            ps = con!!.prepareStatement(deleteAllQuery)
            rs = st!!.executeQuery("select * from estates")
            if(rs!!.next()){
                ps!!.executeUpdate()
                println("All Estates Deleted.")
                return true
            } else {
                println("No Estates to Delete.")
                return false
            }
        } catch (ex: SQLException){
            println("An error has occurred.")
        }
        return ""
    }
}
