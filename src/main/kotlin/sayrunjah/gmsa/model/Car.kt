package sayrunjah.gmsa.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import com.j256.ormlite.table.TableUtils
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import sayrunjah.gmsa.util.DbService
import tornadofx.*

@DatabaseTable(tableName = "cars")
class Car{
    @DatabaseField(generatedId = true)
    var id: Int = 0
    @DatabaseField(columnName = "model", canBeNull = false)
    var model: String = ""
    @DatabaseField(columnName = "make", canBeNull = false)
    var make: String = ""
    @DatabaseField(columnName = "license_plate", canBeNull = false)
    var licensePlate: String =  ""
    @DatabaseField(columnName = "owner", canBeNull = false)
    var owner: Int = 0

    var idProperty = SimpleIntegerProperty(id)
    var modelProperty = SimpleStringProperty(model)
    var makeProperty  = SimpleStringProperty(make)
    var licensePlateProperty  = SimpleStringProperty(licensePlate)
    var ownerProperty = SimpleIntegerProperty(owner)

    val dbservice = DbService()

    constructor()
    constructor(model: String, make: String, licensePlate: String, owner: Int){
        this.model = model
        this.make = make
        this.licensePlate = licensePlate
        this.owner = owner
    }
    constructor(id: Int, model: String, make: String, licensePlate: String, owner: Int){
        this.id = id
        this.model = model
        this.make = make
        this.licensePlate = licensePlate

        this.idProperty = SimpleIntegerProperty(id)
        this.modelProperty = SimpleStringProperty(model)
        this.makeProperty = SimpleStringProperty(make)
        this.licensePlateProperty = SimpleStringProperty(licensePlate)
        this.ownerProperty = SimpleIntegerProperty(owner)
    }

    fun toPropertyModel() : CarModel = CarModel(this)

    fun createCustomerTable() {
        dbservice.dropTable("cars")
        TableUtils.createTable(dbservice.connectionSource(), this::class.java)
    }
}

class CarModel(val car: Car) : ViewModel(){
    var id  = bind{car.idProperty}
    var model = bind {car.modelProperty}
    var make = bind {car.makeProperty}
    var licensePlate = bind {car.licensePlateProperty}
    var owner = bind { car.ownerProperty }

    fun toData() : Car{
        return Car(this.model.value, this.make.value, this.licensePlate.value, this.owner.value as Int)
    }
}