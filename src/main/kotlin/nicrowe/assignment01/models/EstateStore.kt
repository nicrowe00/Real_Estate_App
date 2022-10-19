package nicrowe.assignment01.models


interface EstateStore {
    fun findAllEstates(): List<EstateModel>
    fun findOne(id: Long): EstateModel?
    fun createEstate(estate: EstateModel)
    fun updateEstate(estate: EstateModel)
    fun deleteEstate(estate: EstateModel)
    fun deleteAllEstates()
}