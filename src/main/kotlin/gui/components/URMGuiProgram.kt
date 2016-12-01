package gui.components

import core.*
import javafx.scene.Parent
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import tornadofx.Fragment
import tornadofx.add
import tornadofx.addChildIfPossible
import tornadofx.removeFromParent


class URMGuiProgram(var program: URMProgram?) : Fragment() {
    override val root: AnchorPane by fxml()
    val commandsList: VBox by fxid()

    private fun GetComponentForCommand(command: URMCommand): URMGuiCommand? {
        if (command.javaClass == URMCommandAdd::class.java) {
            return URMGuiCommandAdd(command, this)
        } else if (command.javaClass == URMCommandCopy::class.java) {
            return URMGuiCommandCopy(command, this)
        } else if (command.javaClass == URMCommandJump::class.java) {
            return URMGuiCommandJump(command, this)
        } else if (command.javaClass == URMCommandZero::class.java) {
            return URMGuiCommandZero(command, this)
        }
        return null
    }

    fun Step() {
        program?.Step()
    }

    fun Reset() {
        program?.Reset()
    }

    fun AddCommand(command: URMCommand, index: Int = -1) {
        command.program = program
        program?.AddCommand(command, index)
        if (program != null) {
            var commandComponent = GetComponentForCommand(command)
            if (commandComponent !=null) {
                commandsList.add(commandComponent.root)
            }
        }
    }

    fun RemoveCommand(index: Int) {
        if (program != null) {
            commandsList.children.removeAt(index)
            program?.RemoveCommand(index)
        }
    }

    fun RemoveCommand(command: URMGuiCommand) {
        if (program != null) {
            command.removeFromParent()
            program?.RemoveCommand(command.command)
        }
    }
}
