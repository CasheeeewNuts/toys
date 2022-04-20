import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

import Interpreter.Companion.TRUE
import Interpreter.Companion.FALSE
import org.junit.jupiter.api.Nested


class InterpreterTest {
    @Nested
    inner class TestFourArithmeticOperations {
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

    @Nested
    inner class TestVariableAssignAndIdentify {
        private val interpreter = Interpreter()

        @Test
        fun testAssign1ToVariable() {
            val interpreter = Interpreter()
            val one = 1
            val variableName = "var1"

            val assignment = assign(variableName, int(one))
            val result = interpreter.interpret(assignment)

            assertEquals(one, result)

            val ident = identify(variableName)
            assertEquals(one, interpreter.interpret(ident))
        }

        @Test
        fun testIdentifyUndefinedVariableThenThrownRuntimeException() {
            val variableName = "var1"
            val ident = identify(variableName)

            assertThrows<RuntimeException> { interpreter.interpret(ident) }
        }
    }

    @Nested
    inner class TestComparisonOperator {
        private val interpreter = Interpreter()

        @Test
        fun testLessThan() {
            val greater = int(2)
            val lesser = int(1)

            val trueStatement = lessThan(
                lesser,
                greater
            )

            val falseStatement = lessThan(
                greater,
                lesser
            )

            val falseStatement2 = lessThan(
                greater,
                greater
            )

            assertEquals(TRUE, interpreter.interpret(trueStatement))
            assertEquals(FALSE, interpreter.interpret(falseStatement))
            assertEquals(FALSE, interpreter.interpret(falseStatement2))
        }

        @Test
        fun testLessOrEqual() {

            val greater = int(2)
            val lesser = int(1)

            val trueStatement = lessOrEqual(
                lesser,
                greater
            )

            val trueStatement2 = lessOrEqual(
                greater,
                greater
            )

            val falseStatement = lessOrEqual(
                greater,
                lesser
            )

            assertEquals(TRUE, interpreter.interpret(trueStatement))
            assertEquals(TRUE, interpreter.interpret(trueStatement2))
            assertEquals(FALSE, interpreter.interpret(falseStatement))
        }

        @Test
        fun testGreaterThan() {
            val greater = int(2)
            val lesser = int(1)

            val trueStatement = greaterThan(
                greater,
                lesser
            )

            val falseStatement = greaterThan(
                lesser,
                greater
            )

            val falseStatement2 = greaterThan(
                greater,
                greater
            )

            assertEquals(TRUE, interpreter.interpret(trueStatement))
            assertEquals(FALSE, interpreter.interpret(falseStatement))
            assertEquals(FALSE, interpreter.interpret(falseStatement2))
        }

        @Test
        fun testGreaterOrEqual() {

            val greater = int(2)
            val lesser = int(1)

            val trueStatement = greaterOrEqual(
                greater,
                lesser
            )

            val trueStatement2 = greaterOrEqual(
                greater,
                greater
            )

            val falseStatement = greaterOrEqual(
                lesser,
                greater
            )

            assertEquals(TRUE, interpreter.interpret(trueStatement))
            assertEquals(TRUE, interpreter.interpret(trueStatement2))
            assertEquals(FALSE, interpreter.interpret(falseStatement))
        }

        @Test
        fun testEqual() {
            val greater = int(2)
            val lesser = int(1)

            val trueStatement = equal(
                greater,
                greater
            )

            val falseStatement = equal(
                greater,
                lesser
            )

            assertEquals(TRUE, interpreter.interpret(trueStatement))
            assertEquals(FALSE, interpreter.interpret(falseStatement))
        }

        @Test
        fun testNotEqual() {
            val greater = int(2)
            val lesser = int(1)

            val trueStatement = notEqual(
                lesser,
                greater
            )

            val falseStatement = notEqual(
                greater,
                greater
            )

            assertEquals(TRUE, interpreter.interpret(trueStatement))
            assertEquals(FALSE, interpreter.interpret(falseStatement))
        }
    }
}