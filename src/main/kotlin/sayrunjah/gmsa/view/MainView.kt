package sayrunjah.gmsa.view

import javafx.geometry.Pos
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.util.Duration
import sayrunjah.gmsa.app.AppSettings
import sayrunjah.gmsa.app.Styles
import sayrunjah.gmsa.controller.MainController
import sayrunjah.gmsa.model.Customer
import tornadofx.*
val navDrawer = BorderPane()
class MainView : View(AppSettings.APP_TITLE) {
    val mainController: MainController by inject()
    val mainPane = VBox()
    val homeView : HomeView by inject()

    companion object {
        val theDrawer = navDrawer
    }
    init {
        /*mainPane+= homeView*/
        //Customer().createCustomerTable()

        mainPane.apply {
            this.replaceChildren(homeView)
        }
    }

    override val root = vbox {
        addClass(Styles.mainview)
        borderpane {
            addClass(Styles.topView)
            right = button(graphic = Styles.menuIcon()){
                addClass(Styles.menuIcon)
                action {
                    mainController.prepareSlideMenuAnimation(navDrawer)
                }
            }
        }

        hbox {
            vgrow = Priority.ALWAYS
            stackpane {
                hgrow = Priority.ALWAYS
                addClass(Styles.mainContainer)
                alignment = Pos.TOP_LEFT

                this += mainPane.apply {
                        addClass(Styles.otherContent)
                }

                this += navDrawer.apply {
                    addClass(Styles.navdrawer)
                    mainController.prepareSlideMenuAnimation(this)
                        top = vbox {
                            addClass(Styles.navheader)
                            background = Background(BackgroundImage(Image("index.jpeg", 250.0,200.0, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                    BackgroundSize.DEFAULT))
                        }

                        center = vbox {
                            spacing = 5.0
                            button ("Home"){
                                addClass(Styles.drawerButtons)
                                action {
                                    val homeView = HomeView()
                                    mainController.goToView(mainPane, homeView)
                                    mainController.prepareSlideMenuAnimation(navDrawer)
                                }
                            }
                            button ("Settings"){
                                addClass(Styles.drawerButtons)
                                action {

                                    mainController.prepareSlideMenuAnimation(navDrawer)
                                }
                            }
                            button ("About us"){
                                addClass(Styles.drawerButtons)
                                action {

                                    mainController.prepareSlideMenuAnimation(navDrawer)

                                }
                            }
                        }

                        bottom = hbox {
                            alignment = Pos.CENTER
                            label ("SayrunJah INC"){
                                textFill = Color.WHITE
                            }
                        }

                }
            }
        }
    }
}