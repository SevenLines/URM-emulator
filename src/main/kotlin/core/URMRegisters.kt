package core

import java.util.*

class URMRegisters {
    var registers: MutableMap<Int, URMRegister> = mutableMapOf()

    val registerListeners = mutableListOf<URMRegisterSetListener>()

    operator fun set(reg: Int, value: Int) {
        val register = registers.getOrElse(reg) { URMRegister() }
        register.property.value = value
        registers[reg] = register
        registerListeners.forEach {
            it.onRegisterSet(reg, value, register)
        }
    }

    operator fun get(reg: Int): URMRegister {
        val register = registers.getOrElse(reg) { URMRegister() }
        registers[reg] = register
        return register
    }

    fun Reset() {
        registers.clear()
    }
}