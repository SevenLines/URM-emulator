package core

class URMCommandZero(var reg: Int, program: URMProgram? = null) : URMCommand(program) {
    override fun Execute() {
        super.Execute()
        program!!.registers[reg] = 0
    }
}