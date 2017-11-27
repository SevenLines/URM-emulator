package urm.gui.components

import javafx.event.EventHandler
import javafx.geometry.Point2D
import urm.core.*
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import tornadofx.Fragment
import tornadofx.add
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

    init {

    }

    fun Step(): Boolean {
        return program?.Step()!!
    }

    fun Reset(with_registers: Boolean = false) {
        program?.Reset(with_registers)
        if (with_registers) {
            (1..100 step 1).forEach {
                program?.registers!![it] = 0
            }
        }
    }

    fun AddCommand(command: URMCommand, index: Int = -1) {
        command.program = program
        program?.AddCommand(command, index)
        if (program != null) {
            val commandComponent = GetComponentForCommand(command)
            if (commandComponent != null) {
                commandsList.add(commandComponent.root)
                if (program!!.commands.size <= 1) {
                    Reset()
                }
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

    fun dragged(urmGuiCommand: URMGuiCommand, point: Point2D) {
        val command = commandsList.children.firstOrNull {
            it.localToScene(it.boundsInLocal).contains(point)
        }
        if (command != null && command != urmGuiCommand.root) {
            val oldIndex = commandsList.children.indexOf(urmGuiCommand.root)
            val newIndex = commandsList.children.indexOf(command)
            commandsList.children.remove(command)
            commandsList.children.add(oldIndex, command)
            program?.MoveCommand(urmGuiCommand.command, newIndex)
        }
    }
}
