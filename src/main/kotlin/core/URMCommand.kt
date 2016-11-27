package core

open class URMCommand(var program: URMProgram?) {
    open fun Execute() {
        if (program != null) {
            program!!.currentCommandIndex++;
        }
    }
}