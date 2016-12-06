package urm.gui.components

import urm.core.URMCommand
import urm.core.URMCommandAdd
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.onChange

class URMGuiCommandAdd (override var command: URMCommand, override var guiProgram: URMGuiProgram) :
        URMGuiCommand(command, guiProgram) {

    override val root: HBox by fxml()
    val edtReg: TextField by fxid()
    val realCommand = command as URMCommandAdd


    init {
        super.init()
        lateInit()
        edtReg.text = realCommand.reg.toString()
        edtReg.textProperty().onChange {
            realCommand.reg = it.orEmpty().toInt()
        }
    }
}