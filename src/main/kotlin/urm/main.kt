package urm

import javafx.stage.Stage
import tornadofx.App
import urm.gui.URMGui

class URMGuiApp : App() {
    override val primaryView = URMGui::class

    init {
//        importStylesheet("/gui/style.css")
    }

    override fun start(stage: Stage) {
        super.start(stage)
    }
}