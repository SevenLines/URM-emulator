package urm.gui.components

import urm.core.URMCommand
import urm.core.URMCommandAdd
import urm.core.URMCommandCopy
import javafx.scene.Parent
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.Fragment
import tornadofx.View
import tornadofx.onChange

class URMGuiCommandCopy (override var command: URMCommand, override var guiProgram: URMGuiProgram) :
        URMGuiCommand(command, guiProgram) {
    override val root: HBox by fxml()
    val edtReg1: TextField by fxid()
    val edtReg2: TextField by fxid()

    var realCommand: URMCommandCopy = command as URMCommandCopy

    init {
        super.init()
        lateInit()
        edtReg1.text = realCommand.reg1.toString()
        edtReg2.text = realCommand.reg2.toString()
        edtReg1.textProperty().onChange {
            (command as? URMCommandCopy)?.reg1 = it.orEmpty().toInt()
        }

        edtReg2.textProperty().onChange {
            (command as? URMCommandCopy)?.reg2 = it.orEmpty().toInt()
        }
    }
}