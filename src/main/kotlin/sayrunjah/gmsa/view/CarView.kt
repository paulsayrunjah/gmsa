package sayrunjah.gmsa.view

import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Orientation
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.util.Duration
import sayrunjah.gmsa.app.HomeStyles
import sayrunjah.gmsa.app.Styles
import sayrunjah.gmsa.controller.CarViewController
import sayrunjah.gmsa.model.Car
import sayrunjah.gmsa.model.CarModel
import sayrunjah.gmsa.model.Customer
import sayrunjah.gmsa.model.CustomrtModel
import tornadofx.*

class CarView : View("") {

    val customrtModel : CustomrtModel by inject()
    val carViewController: CarViewController by inject()
    var carModel = CarModel(Car())
    var carList  = mutableListOf<Car>().observable()
    var modelTextField: TextField by singleAssign()
    var makeTextField: TextField by singleAssign()
    var licensePlateTextField: TextField by singleAssign()
    var customer = Customer()
    var selectedTableRow : Int? = null
    val jobOrderView : JobOrderView by inject()

    init {
        //Car().createCustomerTable()
        customer = customrtModel.customer
        carModel.owner = SimpleIntegerProperty(customer.id)
        carList = carViewController.getCaLIst(customer.id)
    }

    override val root = vbox {
        vgrow = Priority.ALWAYS
        spacing = 10.0
        style {
            padding = box(10.0.px)
        }


        borderpane {
            style{
                backgroundColor += Color.WHITE
                padding = box(10.0.px)
            }

            left =  button ("Back"){
                setOnAction {
                    replaceWith(HomeView::class,
                            ViewTransition.Slide(direction = ViewTransition.Direction.RIGHT, duration = Duration(500.0)))
                }
            }

            right = label (customer.name +" 's Carz"){

            }
        }

        hbox {
            spacing = 10.0
            vgrow = Priority.ALWAYS
            vbox {
                hgrow = Priority.ALWAYS
                addClass(HomeStyles.leftside)
                tableview(carList) {
                    vgrow = Priority.ALWAYS
                    addClass(Styles.tableSmart)
                    readonlyColumn("#", Car::id)
                    readonlyColumn("Model", Car::model).remainingWidth()
                    readonlyColumn("Make", Car::make).remainingWidth()
                    readonlyColumn("Registration", Car::licensePlate).remainingWidth()
                    smartResize()

                    contextmenu {
                        item("Edit").action {
                            carModel = selectedItem!!.toPropertyModel()
                            modelTextField.text = selectedItem!!.model
                            makeTextField.text = selectedItem!!.make
                            licensePlateTextField.text = selectedItem!!.licensePlate
                            println(selectedItem!!.id)
                            println(carModel.model.value)
                            //mainController.forceNavClose(MainView.theDrawer)
                            /* customerModel = selectedItem!!.toPropertyModel()
                             val carscope = Scope()
                             setInScope(customerModel, carscope)
                             val carview = find<CarView>(carscope)
                             replaceWith(carview,
                                     ViewTransition.Slide(direction = ViewTransition.Direction.LEFT, duration = Duration(400.0)))*/

                        }
                        item("Change Status").action {
                            //mainController.forceNavClose(MainView.theDrawer)
                            selectedItem?.apply { println("Changing Status for $model") }
                        }
                    }

                    onUserSelect {
                        car ->

                        /*nameTextField.text = customer.name
                        contactTextField.text = customer.contact
                        customerModel.id = SimpleIntegerProperty(customer.id)*/
                        //customerModel = customer.toPropertyModel()
                        //selectedTableRow = selectionModel.focusedIndex
                        selectedTableRow = selectionModel.focusedIndex
                    }
                }
            }
            vbox {
                hgrow = Priority.ALWAYS
                addClass(HomeStyles.rightside)

                form {
                    fieldset {
                        labelPosition = Orientation.VERTICAL
                        /*field ("ID"){
                            //textfield (customerModel.id).required()
                        }*/
                        field ("Model"){
                            modelTextField  = textfield(carModel.model)
                        }

                        field("Make") {
                            makeTextField = textfield(carModel.make)
                        }

                        field("License Plate") {
                            licensePlateTextField = textfield(carModel.licensePlate)
                        }
                        hbox {
                            spacing = 5.0
                            button ("Save"){
                                setOnAction {
                                    if(carModel.commit()){
                                        saveCar(carModel)
                                    }
                                }

                            }

                            button("clear"){
                                setOnAction {

                                }
                            }
                            button("Job orders"){
                                setOnAction {
                                    replaceWith(jobOrderView,
                                            ViewTransition.Slide(direction = ViewTransition.Direction.LEFT, duration = Duration(400.0)))
                                }
                            }

                        }
                    }
                }
            }

        }

    }

    fun Button.saveCar(carModel: CarModel){
        if(carModel.id.value != 0){

        }
        println(carModel.model.value)
        //val car = carModel.toData()
        //println(carModel.id.value as Int)
        //carViewController.saveCar(car)
        //carList.add(car)
        clearForm()
    }

    fun clearForm(){
        modelTextField.text = ""
        makeTextField.text = ""
        licensePlateTextField.text = ""
    }
}
