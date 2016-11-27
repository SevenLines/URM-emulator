package gui.components

import core.URMCommand
import core.URMCommandAdd
import core.URMCommandZero
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.Fragment
import tornadofx.onChange

class URMGuiCommandZero (override var command: URMCommand) : URMGuiCommand(command) {
    override val root: HBox by fxml()
    val edtReg: TextField by fxid()
    val realCommand = command as URMCommandZero

    init {
        edtReg.text = realCommand.reg.toString()
        edtReg.textProperty().onChange {
            (command as? URMCommandZero)?.reg = it.orEmpty().toInt()
        }
    }
}