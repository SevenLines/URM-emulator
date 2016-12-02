package gui.components

import core.URMRegister
import core.URMRegisterSetListener
import core.URMRegisters
import javafx.scene.Parent
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import tornadofx.Fragment
import tornadofx.removeFromParent

class URMGuiRegisters(val registers: URMRegisters) : Fragment(), URMRegisterSetListener {
    override val root: HBox by fxml()
    var guiRegisters: MutableMap<Int, URMGuiRegister> = mutableMapOf()

    override fun onRegisterSet(index: Int, value: Int, register: URMRegister) {
        val guiRegister = guiRegisters.getOrElse(index, {
            val guiRegister = URMGuiRegister(register)
            root.children.add(guiRegister.root)
            guiRegisters[index] = guiRegister
            return@getOrElse guiRegister
        })
        guiRegister.label.text = value.toString()
        guiRegister.index.text = index.toString()
    }

    fun Reset() {
        guiRegisters.clear()
        root.children.clear()
    }

    fun AddRegister(register: URMGuiRegister) {
        root.children.add(register.root)
    }
}

