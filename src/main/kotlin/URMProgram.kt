class URMProgram {
    private var commands : MutableList<URMCommand> = mutableListOf()
    var registers : URMRegisters = URMRegisters()

    var currentCommandIndex: Int = 0
        get() = field
        set(value) {
            field = value
        }
    var stepsCount: Int = 0
        get() = field
        private set(value) {
            field = value
        }

    fun AddCommand(command: URMCommand, index: Int = 0) {
        if (index != 0) {
            commands.add(index, command)
        } else {
            commands.add(command)
        }
        command.program = this
    }

    fun MoveCommand(command: URMCommand, index: Int) {
        commands.remove(command)
        AddCommand(command, index)
    }

    fun RemoveCommand(index: Int) {
        commands[index].program = null
        commands.removeAt(index)
    }

    fun Step() : Boolean {
        if (!HasComplete) {
            this[currentCommandIndex].Execute()
            stepsCount += 1
        }
        return !HasComplete
    }

    fun Reset() {
        currentCommandIndex = 0
        stepsCount = 0
    }

    val HasComplete : Boolean
        get() = currentCommandIndex >= commands.count() || currentCommandIndex < 0

    operator fun get(index: Int) : URMCommand {
        return commands[index]
    }
}