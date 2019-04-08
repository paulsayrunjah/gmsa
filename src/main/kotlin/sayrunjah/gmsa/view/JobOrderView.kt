package sayrunjah.gmsa.view

import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.layout.Priority
import javafx.util.Duration
import sayrunjah.gmsa.app.HomeStyles
import sayrunjah.gmsa.app.Styles
import sayrunjah.gmsa.model.JobOrder
import tornadofx.*
import tornadofx.Stylesheet.Companion.title


class JobOrderView : View("") {
    var jobOrderLIst  = mutableListOf(
            JobOrder(1, "Breaks need fixing", "2019/07/12"),
            JobOrder(2, "Breaks need fixing", "2019/07/12"),
            JobOrder(3, "Breaks need fixing", "2019/07/12")
    ).observable()
    val carView: CarView by inject()
    override val root = vbox {
        vgrow = Priority.ALWAYS
        button("Hello Job View"){
            setOnAction {
                replaceWith(carView,
                        ViewTransition.Slide(direction = ViewTransition.Direction.RIGHT, duration = Duration(400.0)))
            }
        }

        hbox {
            spacing = 10.0
            vgrow = Priority.ALWAYS

            vbox {
                hgrow = Priority.ALWAYS
                vgrow = Priority.ALWAYS
                addClass(HomeStyles.leftside)
                tableview(jobOrderLIst) {
                    vgrow = Priority.ALWAYS
                    addClass(Styles.tableSmart)
                    //readonlyColumn("#", Customer::id)
                    readonlyColumn("Name", JobOrder::desc).remainingWidth()
                    readonlyColumn("Contact", JobOrder::date).remainingWidth()
                    smartResize()
                }
            }

            squeezebox {
                hgrow = Priority.ALWAYS
                vgrow = Priority.ALWAYS
                addClass(HomeStyles.rightside)
                fold("Job Order", expanded = true, closeable = false) {
                    form {
                        fieldset("Customer Details") {
                            field("Name") { textfield() }
                            field("Password") { textfield() }
                        }
                    }
                }
                fold("Assessment", expanded = true,closeable = false) {
                    stackpane {
                        label("Nothing here")
                    }
                }
                fold("Invoice", expanded = true,closeable = false) {
                    stackpane {
                        label("Nothing here")
                    }
                }
                fold("Report", expanded = true,closeable = false) {
                    stackpane {
                        label("Nothing here")
                    }
                }
            }

        }


    }
}
