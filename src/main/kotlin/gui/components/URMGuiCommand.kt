package gui.components

import core.URMCommand
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.control.TextFormatter
import javafx.scene.layout.HBox
import javafx.util.converter.NumberStringConverter
import tornadofx.Fragment
import tornadofx.addClass
import tornadofx.onChange
import tornadofx.toggleClass
import java.text.DecimalFormat
import java.text.ParsePosition

open class URMGuiCommand(open var command: URMCommand, open var guiProgram: URMGuiProgram) : Fragment() {
    override val root: HBox by fxml()
    val components: HBox by fxid()
    val componentsWrapper: HBox by fxid()
    val btnRemove: Button by fxid()

    fun lateInit() {
        val format = DecimalFormat("#.0")
        val textFormater = TextFormatter<String>({
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

        btnRemove.onAction = EventHandler {
            guiProgram.RemoveCommand(this)
        }
        command.isActive.onChange { value ->
            componentsWrapper.toggleClass("current", value)
        }

        components.children.filter { it is TextField }.map {it as TextField}.forEach {
            it.textFormatter = textFormater
        }
    }

}