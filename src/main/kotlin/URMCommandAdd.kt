class URMCommandAdd(var reg: Int, program: URMProgram? = null) : URMCommand(program) {
    override fun Execute() {
        super.Execute()
        if (program != null) {
            program!!.registers[reg].value++
        }
    }
}