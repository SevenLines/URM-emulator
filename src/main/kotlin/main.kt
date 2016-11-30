import gui.URMGui
import javafx.stage.Stage
import tornadofx.App
import tornadofx.FX
import tornadofx.UIComponent
import tornadofx.importStylesheet
import kotlin.reflect.KClass

class URMGuiApp : App() {
    override val primaryView = URMGui::class

    init {
//        importStylesheet("/gui/style.css")
    }

    override fun start(stage: Stage) {
        super.start(stage)
    }
}