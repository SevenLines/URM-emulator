class URMCommandCopy(var reg1: Int, var reg2: Int, program: URMProgram? = null) : URMCommand(program) {
    override fun Execute() {
        super.Execute()
        program!!.registers[reg2].value = program!!.registers[reg1].value
    }
}