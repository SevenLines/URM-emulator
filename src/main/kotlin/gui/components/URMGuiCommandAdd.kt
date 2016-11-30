package gui.components

import core.URMCommand
import core.URMCommandAdd
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.Fragment
import tornadofx.onChange

class URMGuiCommandAdd (override var command: URMCommand) : URMGuiCommand(command) {

    override val root: HBox by fxml()
    val edtReg: TextField by fxid()
    val realCommand = command as URMCommandAdd


    init {
        super.init()
        lateInit()
        edtReg.text = realCommand.reg.toString()
        edtReg.textProperty().onChange {
            realCommand?.reg = it.orEmpty().toInt()
        }
    }
}