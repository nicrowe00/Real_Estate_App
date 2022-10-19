package nicrowe.assignment01.models

data class EstateModel(var id: Long = 0,
                       var name: String = "",
                       var phonenumber: Int = 0,
                       var type: String = "",
                       var address: String = "",
                       var city: String = "",
                       var county: String = "",
                       var eircode: String = "",
                       var estimated: Int = 0,
                       var residents: Int = 0
)