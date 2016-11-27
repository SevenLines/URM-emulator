import gui.URMGui
import javafx.stage.Stage
import tornadofx.App
import tornadofx.FX
import tornadofx.UIComponent
import kotlin.reflect.KClass

class URMGuiApp : App() {
    override val primaryView = URMGui::class

    override fun start(stage: Stage) {
        super.start(stage)
    }
}