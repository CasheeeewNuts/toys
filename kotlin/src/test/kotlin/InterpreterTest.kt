import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class InterpreterTest {
    private val interpreter = Interpreter()

    @Test
    fun testAdd1To1() {
        val one = 1
        val two = 2

        val ast = add(
            int(one),
            int(two)
        )

        assertEquals(one + two, interpreter.interpret(ast))
    }

    @Test
    fun testSub1From2() {
        val one = 1
        val two = 2

        val ast = sub(
            int(two),
            int(one)
        )

        assertEquals(two - one, interpreter.interpret(ast))
    }

    @Test
    fun testMul2By2() {
        val two = 2

        val ast = mul(
            int(two),
            int(two)
        )

        assertEquals(two * two, interpreter.interpret(ast))
    }

    @Test
    fun testDiv2Into6() {
        val two = 2
        val six = 6

        val ast = div(
            int(six),
            int(two)
        )

        assertEquals(six / two, interpreter.interpret(ast))
    }
}