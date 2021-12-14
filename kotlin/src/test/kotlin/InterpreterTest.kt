import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class InterpreterTest {
    private val interpreter = Interpreter()

    @Test
    fun testAddIntAndInt() {
        val one = 1
        val two = 2

        val ast = add(
            int(one),
            int(two)
        )

        assertEquals(one + two, interpreter.interpret(ast))
    }

    @Test
    fun testSubIntFromInt() {
        val one = 1
        val two = 2

        val ast = sub(
            int(two),
            int(one)
        )

        assertEquals(two - one, interpreter.interpret(ast))
    }
}