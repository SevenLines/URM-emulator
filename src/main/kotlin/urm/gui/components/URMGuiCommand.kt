package urm.gui.components

import urm.core.URMCommand
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.control.TextFormatter
import javafx.scene.layout.HBox
import tornadofx.Fragment
import tornadofx.onChange
import tornadofx.toggleClass
import java.text.DecimalFormat
import java.text.ParsePosition

open class URMGuiCommand(open var command: URMCommand, open var guiProgram: URMGuiProgram) : Fragment() {
    override val root: HBox by fxml()
    val components: HBox by fxid()
    val componentsWrapper: HBox by fxid()
    val btnRemove: Button by fxid()

    var runnable: Runnable? = null

    fun lateInit() {
        val format = DecimalFormat("#")

        btnRemove.onAction = EventHandler {
            guiProgram.RemoveCommand(this)
        }

        command.isActive.onChange { value ->
            if (runnable != null) {
                runnable = null
            }
            runnable = Runnable({
                componentsWrapper.toggleClass("current", value)
            })
            Platform.runLater(runnable)
        }

        components.children.filter { it is TextField }.map { it as TextField }.forEach {
            it.textFormatter = TextFormatter<String>({
                if (it.controlNewText.isEmpty()) {
                    it.text = "1"
                    return@TextFormatter it
                }

                val parsePosition = ParsePosition(0)
                val obj = format.parse(it.controlNewText, parsePosition)

                if (obj == null || parsePosition.index < it.controlNewText.length) {
                    return@TextFormatter null
                } else {
                    return@TextFormatter it
                }
            })
        }

        root.onMouseDragged = EventHandler { e ->
            val point = this.root.localToScene(e.x, e.y)
            guiProgram.dragged(this, point)
        }
    }

}