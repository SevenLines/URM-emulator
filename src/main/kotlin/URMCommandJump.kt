class URMCommandJump(program: URMProgram, var reg1: Int, var reg2: Int, var commandIndex: Int) : URMCommand(program) {
    override fun Execute() {
        super.Execute()
        if (program.registers[reg1].value == program.registers[reg2].value) {
            program.currentCommandIndex = commandIndex
        }
    }
}