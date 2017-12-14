package urm.gui.components

import javafx.event.EventHandler
import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.control.ScrollPane
import urm.core.*
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.shape.Polyline
import tornadofx.Fragment
import tornadofx.add
import tornadofx.removeFromParent
import javafx.scene.shape.QuadCurve


class URMGuiProgram(var program: URMProgram?) : Fragment() {
    override val root: AnchorPane by fxml()
    val commandsList: VBox by fxid()
    val scrollWrapper: ScrollPane by fxid()

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
        commandsList.prefWidthProperty().bind(scrollWrapper.widthProperty())
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
                Reset()
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
            Reset()
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
//        if (program?.currentCommand == urmGuiCommand.command) {
        Reset()
//        }
    }

    fun scrollToCommand(node: Node) {
        val viewport = this.scrollWrapper.viewportBounds
//        var contentHeight =
    }

    fun commandMouseEntered(urmGuiCommand: URMGuiCommand) {
        if (urmGuiCommand.javaClass == URMGuiCommandJump::class.java) {
            val startY = urmGuiCommand.root.layoutY + urmGuiCommand.root.height / 2
            val index = (urmGuiCommand.command as URMCommandJump).commandIndex - 1

            if (index < commandsList.children.count()) {
                val commandTo = commandsList.children[index] as HBox;
                val endY = commandTo.layoutY + commandTo.height / 2

//                path.points.removeAll { true }
//                path.points.addAll(
//                        urmGuiCommand.root.layoutX, startY,
//                        urmGuiCommand.root.width, startY,
//                        urmGuiCommand.root.width, endY,
//                        urmGuiCommand.root.layoutX, endY
//                )
//
//                path.isVisible = true
            }
        } else {
        }
    }
}
