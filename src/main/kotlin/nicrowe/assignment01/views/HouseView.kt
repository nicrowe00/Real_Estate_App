package nicrowe.assignment01.views

class HouseView {

    fun menu(): Int{

        var option: Int
        var input: String?

        println("HOUSE TYPE SELECTION")
        println(" 1. Detached House")
        println(" 2. Semi-Detached House")
        println(" 3. Terraced House")
        println(" 4. Apartment")
        println(" 5. Bungalow")
        println(" 6. Condominium")
        println(" 7. Mansion")
        println(" 8. Villa")
        println()
        print("Enter option for House Type: ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }
}