class URMCommandZero(program: URMProgram, var reg: Int) : URMCommand(program) {
    override fun Execute() {
        super.Execute()
        program.registers[reg].value = 0
    }
}