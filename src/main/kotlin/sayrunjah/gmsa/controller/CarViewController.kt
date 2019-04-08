package sayrunjah.gmsa.controller

import com.j256.ormlite.dao.DaoManager
import javafx.collections.ObservableList
import sayrunjah.gmsa.model.Car
import sayrunjah.gmsa.model.CarModel
import sayrunjah.gmsa.model.Customer
import sayrunjah.gmsa.util.DbService
import tornadofx.*

class CarViewController : Controller(){


    val dbconnection = DbService().connectionSource()
    val doa = DaoManager.createDao(dbconnection, Car::class.java)

    init {
        doa.clearObjectCache()
    }

    fun saveCar(car : Car){
        doa.create(car)
        dbconnection.close()
    }

    fun updateCar(car: Car){
        doa.update(car)
        dbconnection.close()
    }
    fun deleteCar(car: Car){
        doa.delete(car)
        dbconnection.close()
    }

    fun getCaLIst(owner: Int): ObservableList<Car> {
        return doa.query(doa.queryBuilder()
                .where().eq("owner", owner).prepare())
                .observable()
    }

    fun resetModel(car: Car): CarModel{
        return CarModel(Car())
    }

}