package gui.components

import core.*
import javafx.scene.Parent
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import tornadofx.Fragment
import tornadofx.add
import tornadofx.addChildIfPossible


class URMGuiProgram(var program: URMProgram?) : Fragment() {
    override val root: VBox by fxml()

    private fun GetComponentForCommand(command: URMCommand): URMGuiCommand? {
        if (command.javaClass == URMCommandAdd::class.java) {
            return URMGuiCommandAdd(command)
        } else if (command.javaClass == URMCommandCopy::class.java) {
            return URMGuiCommandCopy(command)
        } else if (command.javaClass == URMCommandJump::class.java) {
            return URMGuiCommandJump(command)
        } else if (command.javaClass == URMCommandZero::class.java) {
            return URMGuiCommandZero(command)
        }
        return null
    }

    fun Step() {
        program?.Step()
    }

    fun Reset() {
        program?.Reset()
    }

    fun AddCommand(command: URMCommand, index: Int = 0) {
        command.program = program
        program?.commands?.add(index, command)
        if (program != null) {
            var commandComponent = GetComponentForCommand(command)
            if (commandComponent !=null) {
                root.add(commandComponent.root)
            }
        }
    }

    fun RemoveCommand(index: Int) {
        if (program != null) {
            root.children.removeAt(index)
            program?.RemoveCommand(index)
        }
    }
}
