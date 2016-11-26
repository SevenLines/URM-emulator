class URMRegisters {
    var registers: MutableMap<Int, URMRegister> = mutableMapOf()

    operator fun set(reg: Int, value: Int) {
        val register = registers.getOrElse(reg) {URMRegister()}
        register.value = value
        registers[reg] = register
    }

    operator fun get(reg: Int): URMRegister {
        return registers.getOrElse(reg) {URMRegister()}
    }
}