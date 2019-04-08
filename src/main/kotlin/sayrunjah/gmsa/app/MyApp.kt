package sayrunjah.gmsa.app

import sayrunjah.gmsa.view.MainView
import tornadofx.*

class MyApp: App(MainView::class, Styles::class){
    init {
        importStylesheet(HomeStyles::class)
    }
}