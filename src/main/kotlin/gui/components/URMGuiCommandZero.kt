package gui.components

import core.URMCommand
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.Fragment

class URMGuiCommandZero (override val command: URMCommand) : URMGuiCommand, Fragment() {
    override val root: HBox by fxml()
    val edtReg: TextField by fxid()
}