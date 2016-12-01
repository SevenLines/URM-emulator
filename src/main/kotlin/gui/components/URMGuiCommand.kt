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

open class URMGuiCommand(open var command: URMCommand, open var guiProgram: URMGuiProgram) : Fragment() {
    override val root: HBox by fxml()
    val components: HBox by fxid()
    val componentsWrapper: HBox by fxid()
    val btnRemove: Button by fxid()

    fun lateInit() {
        btnRemove.onAction = EventHandler {
            guiProgram.RemoveCommand(this)
        }
        command.isActive.onChange { value ->
            componentsWrapper.toggleClass("current", value)
        }
        components.children.filter { it is TextField }.map {it as TextField}.forEach {
            it.textFormatter = TextFormatter(NumberStringConverter())
        }
    }

}