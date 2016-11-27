package gui.components

import core.URMCommand
import javafx.scene.Parent
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.Fragment
import tornadofx.View

class URMGuiCommandCopy(override val command: URMCommand) : URMGuiCommand, Fragment() {
    override val root: HBox by fxml()
    val edtReg1: TextField by fxid()
    val edtReg2: TextField by fxid()
}