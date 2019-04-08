package sayrunjah.gmsa.controller

import com.j256.ormlite.dao.DaoManager
import javafx.collections.ObservableList
import sayrunjah.gmsa.model.Customer
import sayrunjah.gmsa.util.DbService
import tornadofx.*

class HomeController: Controller(){

    val dbconnection = DbService().connectionSource()
    val doa = DaoManager.createDao(dbconnection, Customer::class.java)

    init {
        doa.clearObjectCache()
    }

    fun saveCustomer(customer : Customer){
        doa.create(customer)
        dbconnection.close()
    }

    fun updateCustomer(customer: Customer){
        doa.update(customer)
        dbconnection.close()
    }
    fun deleteCustomer(customer: Customer){
        println("Customer:  "+customer.id)
        doa.delete(customer)
       // doa.clearObjectCache()
        dbconnection.close()
    }

    fun getCustomerList(): ObservableList<Customer> {
        return doa.queryForAll().observable()
    }
}