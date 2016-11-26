class URMCommandAdd(program: URMProgram, var reg: Int) : URMCommand(program) {
    override fun Execute() {
        super.Execute()
        program.registers[reg].value++
    }
}