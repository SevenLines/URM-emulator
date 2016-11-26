class URMCommandCopy(program: URMProgram, var reg1: Int, var reg2: Int) : URMCommand(program) {
    override fun Execute() {
        super.Execute()
        program.registers[reg2].value = program.registers[reg1].value
    }
}