package urm.core

open class URMProgram {
    var commands: MutableList<URMCommand> = mutableListOf()
        get() {
            return field
        }

    var registers: URMRegisters = URMRegisters()

    var currentCommandIndex: Int = 0
        set(value) {
            // set to false old value
            commands.forEach {
                it.isActive.value = false
            }
            // set to true new value
            if (commands.count() > value)
                if (commands[value].isActive.value != true)
                    commands[value].isActive.value = true

            field = value
        }

    var currentCommand: URMCommand? = null
        get() {
            return commands.getOrNull(currentCommandIndex)
        }

    var stepsCount: Int = 0
        get() = field
        private set(value) {
            field = value
        }

    fun AddCommand(command: URMCommand, index: Int = -1) {
        if (index != -1) {
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

    fun RemoveCommand(command: URMCommand) {
        command.program = null
        commands.remove(command)
    }

    fun Step(): Boolean {
        if (!HasComplete) {
            this[currentCommandIndex].Execute()
            stepsCount += 1
        }
        return !HasComplete
    }

    fun Reset(with_registers: Boolean = false) {
        currentCommandIndex = 0
        stepsCount = 0
        if (with_registers) {
            registers.Reset()
        }
    }

    val HasComplete: Boolean
        get() = currentCommandIndex >= commands.count() || currentCommandIndex < 0

    operator fun get(index: Int): URMCommand {
        return commands[index]
    }
}