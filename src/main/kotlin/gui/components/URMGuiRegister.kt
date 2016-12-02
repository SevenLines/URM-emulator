package gui.components

import core.URMRegister
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.control.TextFormatter
import javafx.scene.layout.VBox
import tornadofx.Fragment
import tornadofx.onChange
import tornadofx.text
import java.text.DecimalFormat
import java.text.ParsePosition


class URMGuiRegister(register: URMRegister) : Fragment() {
    override val root: VBox by fxml()
    val label: TextField by fxid()
    val index: Label by fxid()

    init {
        val format = DecimalFormat("#.0")

        val textFormatter = TextFormatter<String>({
            if (it.controlNewText.isEmpty()) {
                it.text = "0"
                return@TextFormatter it
            }

            val parsePosition = ParsePosition(0)
            val obj = format.parse(it.controlNewText, parsePosition)

            if (obj == null || parsePosition.index < it.controlNewText.length) {
                return@TextFormatter null
            } else {
                return@TextFormatter it
            }
        })

        label.textProperty().onChange { newValue ->
            register.property.value = newValue?.toInt()
        }
        label.textFormatter = textFormatter

        register.property.onChange { value ->
            label.text = value.toString()
        }
    }
}

