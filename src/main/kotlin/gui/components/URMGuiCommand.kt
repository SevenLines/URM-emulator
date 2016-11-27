package gui.components

import core.URMCommand
import javafx.scene.layout.HBox
import tornadofx.Fragment

open class URMGuiCommand(open var command: URMCommand) : Fragment() {
    override val root: HBox by fxml()

    val IsActive : Boolean
    get() {
        return command.program?.currentCommand == command
    }
}