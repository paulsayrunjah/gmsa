package sayrunjah.gmsa.controller

import javafx.animation.TranslateTransition
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.util.Duration
import sayrunjah.gmsa.app.AppSettings
import tornadofx.*
import tornadofx.controlsfx.clearableTextfield

class MainController : Controller(){

    var theNavOpen: Boolean = false

    fun goToView(rootContainer: VBox, view: View){
        rootContainer.clear()
        rootContainer += view
    }

    fun prepareSlideMenuAnimation(drawer: BorderPane) : Boolean {
        var isNavOpen: Boolean
        val openNav = TranslateTransition(Duration(350.0), drawer)
        openNav.toX = 0.0
        val closeNav = TranslateTransition(Duration(350.0), drawer)

        if (drawer.translateX !== 0.0) {
            openNav.play()
            isNavOpen = true
        } else {
            closeNav.toX = -AppSettings.APP_DRAWER_WIDTH
            closeNav.play()
            isNavOpen = false
        }
        return isNavOpen
    }

    fun forceNavClose(drawer: BorderPane){
        val closeNav = TranslateTransition(Duration(350.0), drawer)
        if(drawer.translateX == 0.0){
            closeNav.toX = -AppSettings.APP_DRAWER_WIDTH
            closeNav.play()
        }
    }


}