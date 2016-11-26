import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class URMProgramTests {
    @Test
    fun ExecuteAddProgram() {
        val program = URMProgram()
        program.registers[1] = 10
        program.registers[2] = 12
        program.AddCommand(URMCommandAdd(1))
        program.AddCommand(URMCommandAdd(3))
        program.AddCommand(URMCommandJump(2,3,5))
        program.AddCommand(URMCommandJump(2,2,1))
        program.AddCommand(URMCommandZero(2))
        program.AddCommand(URMCommandZero(3))

        while(program.Step()) {}

        Assertions.assertEquals(22, program.registers[1].value)
        Assertions.assertEquals(0, program.registers[2].value)
        Assertions.assertEquals(0, program.registers[3].value)
        Assertions.assertEquals(49, program.stepsCount)
    }

    @Test
    fun ExecuteSubProgram() {
        val program = URMProgram()
        program.registers[1] = 12
        program.registers[2] = 10
        program.AddCommand(URMCommandAdd(2))
        program.AddCommand(URMCommandAdd(3))
        program.AddCommand(URMCommandJump(1,2,5))
        program.AddCommand(URMCommandJump(2,2,1))
        program.AddCommand(URMCommandCopy(3, 1))
        program.AddCommand(URMCommandZero(2))
        program.AddCommand(URMCommandZero(3))

        while(program.Step()) {}

        Assertions.assertEquals(2, program.registers[1].value)
        Assertions.assertEquals(0, program.registers[2].value)
        Assertions.assertEquals(0, program.registers[3].value)
        Assertions.assertEquals(10, program.stepsCount)
    }
}