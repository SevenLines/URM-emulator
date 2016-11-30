package gui.components

import core.URMCommand
import javafx.scene.control.TextField
import javafx.scene.control.TextFormatter
import javafx.scene.layout.HBox
import javafx.util.converter.NumberStringConverter
import tornadofx.Fragment
import tornadofx.addClass
import tornadofx.onChange
import tornadofx.toggleClass

open class URMGuiCommand(open var command: URMCommand) : Fragment() {
    override val root: HBox by fxml()
    val components: HBox by fxid()

    fun lateInit() {
        command.isActive.onChange { value ->
            components.toggleClass("current", value)
        }
        components.children.filter { it is TextField }.map {it as TextField}.forEach {
            it.textFormatter = TextFormatter(NumberStringConverter())
        }
    }

}