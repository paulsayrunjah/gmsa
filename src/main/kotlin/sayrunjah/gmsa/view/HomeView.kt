    package sayrunjah .gmsa.view

import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.Priority
import javafx.util.Duration
import org.controlsfx.control.Notifications
import sayrunjah.gmsa.app.AppSettings
import sayrunjah.gmsa.app.HomeStyles
import sayrunjah.gmsa.app.Styles
import sayrunjah.gmsa.controller.HomeController
import sayrunjah.gmsa.controller.MainController
import sayrunjah.gmsa.model.Customer
import sayrunjah.gmsa.model.CustomrtModel
import sayrunjah.gmsa.util.DbService
import tornadofx.*

    class HomeView : View("") {

        var customerModel = CustomrtModel(Customer())
        var nameTextField: TextField by singleAssign()
        var contactTextField: TextField by singleAssign()
        var selectedTableRow : Int? = null
        var selectedCustomer: Customer? = null
        val mainController: MainController by inject()
        val homeController: HomeController by inject()

    var personList1 = mutableListOf(
            Customer(1,"Samantha Stuart 1", "0752370787"),
            Customer(2,"Samantha Stuart 2", "0752370787"),
            Customer(3,"Samantha Stuart 3", "0752370787")
    ).observable()

        var personList = homeController.getCustomerList()

    override val root = vbox {
        style {
            padding = box(10.0.px)
        }
        vgrow = Priority.ALWAYS
        hbox {
            spacing = 10.0
            vgrow = Priority.ALWAYS
            vbox {
                hgrow = Priority.ALWAYS
                addClass(HomeStyles.leftside)
                tableview(personList) {
                    vgrow = Priority.ALWAYS
                    addClass(Styles.tableSmart)
                    //readonlyColumn("#", Customer::id)
                    readonlyColumn("Name", Customer::name).remainingWidth()
                    readonlyColumn("Contact", Customer::contact).remainingWidth()
                    smartResize()

                    contextmenu {
                        item("Edit").action {
                            mainController.forceNavClose(MainView.theDrawer)
                            nameTextField.text = selectedItem!!.name
                            contactTextField.text = selectedItem!!.contact
                            customerModel.id = SimpleIntegerProperty(selectedItem!!.id)
                            selectedTableRow = selectionModel.focusedIndex
                           /* customerModel = selectedItem!!.toPropertyModel()
                            val carscope = Scope()
                            setInScope(customerModel, carscope)
                            val carview = find<CarView>(carscope)
                            replaceWith(carview,
                                    ViewTransition.Slide(direction = ViewTransition.Direction.LEFT, duration = Duration(400.0)))*/

                        }
                        item("Delete").action {
                            mainController.forceNavClose(MainView.theDrawer)
                            homeController.deleteCustomer(selectedItem!!)
                            personList.removeAt(selectionModel.focusedIndex)
                        }
                    }

                    onUserSelect {
                        customer ->


                        val customerModel = customer.toPropertyModel()
                        val carscope = Scope()
                        setInScope(customerModel, carscope)
                        val carview = find<CarView>(carscope)
                        replaceWith(carview,
                                ViewTransition.Slide(direction = ViewTransition.Direction.LEFT, duration = Duration(400.0)))
                        mainController.forceNavClose(MainView.theDrawer)
                        /*nameTextField.text = customer.name
                        contactTextField.text = customer.contact
                        customerModel.id = SimpleIntegerProperty(customer.id)*/
                        //customerModel = customer.toPropertyModel()
                        //selectedTableRow = selectionModel.focusedIndex
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
                        field ("NAME"){
                            nameTextField = textfield (customerModel.name)
                        }

                        field("Contact") {
                            contactTextField = textfield(customerModel.contact)
                        }
                        hbox {
                            spacing = 5.0
                            button ("Save"){
                                setOnAction {
                                    if (customerModel.commit()){
                                        saveCustomer(customerModel)
                                       // println(selectedTableRow?.let { it1 -> personList[it1].name })
                                    }
                                }

                                disableProperty().bind(customerModel.name.isBlank())
                            }


                            button("clear"){
                                setOnAction {
                                   clearForm()
                                }
                            }
                        }


                    }
                }
            }

        }
    }

    fun Button.saveCustomer(customerModel: CustomrtModel){
        var message = "Customer Saved!"
        runAsync {
            if(customerModel.id.value != 0){
                var customer = Customer()
                customer.id = customerModel.id.value as Int
                customer.name = customerModel.name.value
                customer.contact = customerModel.contact.value
                personList[selectedTableRow!!] = customer
                homeController.updateCustomer(customer)
                message = "Customer Updated!"
            }else{
                val customer = Customer(customerModel.name.value, customerModel.contact.value)
                homeController.saveCustomer(customer)
                personList.add(customer)
            }
        } ui {
            success -> Notifications.create()
                .title(message)
                .text(customerModel.name.value)
                .owner(this)
                .position(Pos.TOP_LEFT)
                .showInformation()

            clearForm()
        }

    }

    fun clearForm(){
        customerModel.id = SimpleIntegerProperty(0)
        customerModel = CustomrtModel(Customer())
        nameTextField.text = ""
        contactTextField.text = ""
    }
}
