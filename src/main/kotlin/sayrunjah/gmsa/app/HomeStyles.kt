package sayrunjah.gmsa.app

import javafx.scene.paint.Color
import tornadofx.*

class HomeStyles : Stylesheet(){
        companion object {
            val leftside by cssclass()
            val rightside by cssclass()
        }

        init {
            leftside{
                prefWidth = 600.px
                backgroundColor += Color.WHITE
            }

            rightside{
                prefWidth = 200.px
                backgroundColor += Color.WHITE
            }
        }
}