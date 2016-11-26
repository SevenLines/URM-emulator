class URMProgram {
    private var commands : MutableList<URMCommand> = mutableListOf()
    var registers : URMRegisters = URMRegisters()

    var currentCommandIndex: Int = 0
        get() = field
        set(value) {
            field = value
        }

    fun AddCommand(command: URMCommand, index: Int = 0) {
        if (index != 0) {
            commands.add(index, command)
        }
        commands.add(command)
    }

    fun MoveCommand(command: URMCommand, index: Int) {
        commands.remove(command)
        AddCommand(command, index)
    }

    fun RemoveCommand(index: Int) {
        commands.removeAt(index)
    }

    fun Step() {
        if (!HasComplete) {
            this[currentCommandIndex].Execute()
        }
    }

    fun Reset() {
        currentCommandIndex = 0
    }

    val HasComplete : Boolean
        get() = currentCommandIndex >= commands.count()

    operator fun get(index: Int) : URMCommand {
        return commands[index]
    }
}