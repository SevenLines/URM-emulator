package gui.components

import core.URMRegister
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import tornadofx.Fragment
import tornadofx.onChange

class URMGuiRegister(register: URMRegister) : Fragment() {
    override val root: VBox by fxml()
    val label: TextField by fxid()
    val index: Label by fxid()


    init {
        label.textProperty().onChange { newValue ->
            register.property.value = newValue?.toInt()
        }

        register.property.onChange { value ->
            label.text = value.toString()
        }
    }
}

