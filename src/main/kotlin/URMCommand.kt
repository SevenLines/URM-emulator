open class URMCommand(var program: URMProgram) {
    open fun Execute() {
        program.currentCommandIndex++;
    }
}