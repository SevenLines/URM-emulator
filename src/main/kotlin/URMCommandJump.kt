class URMCommandJump(var reg1: Int, var reg2: Int, var commandIndex: Int, program: URMProgram? = null) : URMCommand(program) {
    override fun Execute() {
        super.Execute()
        if (program!!.registers[reg1].value == program!!.registers[reg2].value) {
            program!!.currentCommandIndex = commandIndex - 1
        }
    }
}