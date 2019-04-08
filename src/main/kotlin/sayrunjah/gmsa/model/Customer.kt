package sayrunjah.gmsa.model

import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import com.j256.ormlite.table.TableUtils
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import sayrunjah.gmsa.util.DbService
import tornadofx.*

@DatabaseTable(tableName = "customers")
class Customer{

    @DatabaseField(generatedId = true)
    var id : Int = 0

    @DatabaseField(columnName = "name", canBeNull = false)
    var name : String = ""

    @DatabaseField(columnName = "contact", canBeNull = false)
    var contact : String = ""

    var idProperty = SimpleIntegerProperty()
    var nameProperty = SimpleStringProperty()
    var contactProperty = SimpleStringProperty()
    val dbservice = DbService()


    constructor()
    constructor(name: String, contact: String){
        this.name = name
        this.contact = contact
    }
    constructor(id: Int, name: String, contact: String){
        this.id = id
        this.name = name
        this.contact = contact

        idProperty = SimpleIntegerProperty(this.id)
        nameProperty = SimpleStringProperty(this.name)
        contactProperty = SimpleStringProperty(this.contact)
    }

   fun toPropertyModel() : CustomrtModel = CustomrtModel(this)

   fun createCustomerTable() {
       dbservice.dropTable("customers")
       TableUtils.createTable(dbservice.connectionSource(), this::class.java)
   }

}

class CustomrtModel(val customer: Customer) : ViewModel(){
    var id = bind { customer.idProperty}
    var name = bind { customer.nameProperty}
    var contact = bind { customer.contactProperty }
}