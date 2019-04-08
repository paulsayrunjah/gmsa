package sayrunjah.gmsa.app

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val mainview by cssclass()
        val topView by cssclass()
        val mainContainer by cssclass()
        val otherContent by cssclass()
        val menuIcon by cssclass()
        val navdrawer by cssclass()
        val navheader by cssclass()
        val drawerButtons by cssclass()
        val tableSmart by cssclass()
        val contextItem by cssclass()

        fun menuIcon() = FontAwesomeIconView(FontAwesomeIcon.BARS).apply {
            glyphSize = 22
            addClass(menuIcon)
        }
    }

    init {
        mainview{
            prefWidth = AppSettings.APP_WIDTH
            prefHeight = AppSettings.APP_HEIGTH
            backgroundColor += AppSettings.BACKGROUND_COLOR
        }

        topView{
            backgroundColor += Color.WHITE
            prefHeight = 50.px
            padding = box(10.px)

        }

        mainContainer{
            navdrawer {
                maxWidth = AppSettings.APP_DRAWER_WIDTH.px
                backgroundColor += AppSettings.PRIMARY_COLOR

                navheader {
                    prefWidth = AppSettings.APP_DRAWER_WIDTH.px
                    prefHeight = 200.px
                }
            }
            otherContent{
                prefWidth = 800.px
                //backgroundColor += Color.GREEN
            }
        }

        drawerButtons{
            prefWidth = AppSettings.APP_DRAWER_WIDTH.px
            prefHeight = 48.px
            backgroundColor += Color.ALICEBLUE
        }


        menuIcon {
            fill = AppSettings.PRIMARY_COLOR
            backgroundColor += Color.WHITE
            and(hover) {
                fill = AppSettings.BACKGROUND_COLOR
            }
        }

            tableView {
                backgroundColor += c("#cccccc")
                columnHeader{
                    backgroundColor += AppSettings.PRIMARY_COLOR
                    stroke = Color.TRANSPARENT
                    size = 35.0.px

                    label{
                        textFill = AppSettings.TEXT_COLOR
                        fontWeight = FontWeight.BOLD
                    }
                }

                tableColumn{
                    alignment = Pos.CENTER
                    padding = box(10.0.px, 5.0.px)
                }


            }

        contextItem{
            textFill = AppSettings.PRIMARY_COLOR
        }
    }

}