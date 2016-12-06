package urm.gui.components

import urm.core.URMCommand
import urm.core.URMCommandCopy
import urm.core.URMCommandJump
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.Fragment
import tornadofx.onChange

class URMGuiCommandJump  (override var command: URMCommand, override var guiProgram: URMGuiProgram) :
        URMGuiCommand(command, guiProgram) {
    override val root: HBox by fxml()
    val edtReg1: TextField by fxid()
    val edtReg2: TextField by fxid()
    val edtCommandIndex: TextField by fxid()
    val realCommand = command as URMCommandJump

    init {
        super.init()
        lateInit()

        edtReg1.text = realCommand.reg1.toString()
        edtReg2.text = realCommand.reg2.toString()
        edtCommandIndex.text = realCommand.commandIndex.toString()

        edtReg1.textProperty().onChange {
            (command as? URMCommandJump)?.reg1 = it.orEmpty().toInt()
        }

        edtReg2.textProperty().onChange {
            (command as? URMCommandJump)?.reg2 = it.orEmpty().toInt()
        }

        edtCommandIndex.textProperty().onChange {
            (command as? URMCommandJump)?.commandIndex = it.orEmpty().toInt()
        }
    }
}