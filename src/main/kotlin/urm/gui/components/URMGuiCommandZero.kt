package urm.gui.components

import urm.core.URMCommand
import urm.core.URMCommandAdd
import urm.core.URMCommandZero
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.Fragment
import tornadofx.onChange

class URMGuiCommandZero  (override var command: URMCommand, override var guiProgram: URMGuiProgram) :
        URMGuiCommand(command, guiProgram) {
    override val root: HBox by fxml()
    val edtReg: TextField by fxid()
    val realCommand = command as URMCommandZero

    init {
        super.init()
        lateInit()

        edtReg.text = realCommand.reg.toString()
        edtReg.textProperty().onChange {
            (command as? URMCommandZero)?.reg = it.orEmpty().toInt()
        }
    }
}