import Ast.Expr

class Interpreter {
    private var variableEnvironment: Environment<Int> = Environment.new()
    private val functionEnvironment: Environment<Ast.TopLevel.FunctionDefinition> = Environment.new()

    object Boolean {
        const val TRUE = 1
        const val FALSE = 0
    }

    class Environment<T>(private val bindings: MutableMap<String, T>, val next: Environment<T>? = null) : MutableMap<String, T> by bindings {
        companion object {
            fun <T> new(next: Environment<T>? = null): Environment<T> {
                return Environment(mutableMapOf(), next)
            }
        }

        fun findBinding(name: String): MutableMap<String, T>? {
            return when {
                bindings[name] != null -> bindings
                next != null -> next.findBinding(name)
                else -> null
            }
        }
    }

    fun interpret(ast: Expr): Int {
        return when (ast) {
            is Expr.Binary -> {
                val lhs = interpret(ast.lhs)
                val rhs = interpret(ast.rhs)

                when (ast.operator) {
                    Operator.ADD -> lhs + rhs
                    Operator.SUB -> lhs - rhs
                    Operator.MUL -> lhs * rhs
                    Operator.DIV -> lhs / rhs
                    Operator.LESS_THAN -> if (lhs < rhs) Boolean.TRUE else Boolean.FALSE
                    Operator.LESS_OR_EQUAL -> if (lhs <= rhs) Boolean.TRUE else Boolean.FALSE
                    Operator.GREATER_THAN -> if (lhs > rhs) Boolean.TRUE else Boolean.FALSE
                    Operator.GREATER_OR_EQUAL -> if (lhs >= rhs) Boolean.TRUE else Boolean.FALSE
                    Operator.EQUAL -> if (lhs == rhs) Boolean.TRUE else Boolean.FALSE
                    Operator.NOT_EQUAL -> if (lhs != rhs) Boolean.TRUE else Boolean.FALSE
                }
            }
            is Expr.IntegerLiteral -> ast.value
            is Expr.Assignment -> {
                val value = interpret(ast.expr)
                val binding = variableEnvironment.findBinding(ast.identifier)

                if (binding != null) {
                    binding[ast.identifier] = value
                } else {
                    variableEnvironment[ast.identifier] = value
                }

                return value
            }
            is Expr.Identifier -> {
                return variableEnvironment[ast.name] ?: throw RuntimeException("undefined variable!")
            }
            is Expr.If -> {
                val condition = interpret(ast.condition)

                return if (condition != Boolean.FALSE) {
                    interpret(ast.thenClause)
                } else if (ast.elseClause != null){
                    interpret(ast.elseClause)
                } else {
                    1
                }
            }
            is Expr.While -> {
                while (true) {
                    val condition = interpret(ast.condition)

                    if (condition != Boolean.FALSE) {
                        interpret(ast.body)
                    } else {
                        break
                    }
                }

                1
            }
            is Expr.Block -> {
                var result = 0

                for (e in ast.elements) {
                    result = interpret(e)
                }

                result
            }
            is Expr.FunctionCall -> {
                val definition = functionEnvironment[ast.name] ?: throw RuntimeException("Function ${ast.name} is not defined")

                val actualParams = ast.args
                val formalParams = definition.args
                val body = definition.body
                val values = actualParams.map(this::interpret)
                val backup = variableEnvironment

                variableEnvironment = Environment.new(variableEnvironment)

                formalParams.forEachIndexed {
                        i, formalParamName -> variableEnvironment[formalParamName] = values[i]
                }

                val result = interpret(body)
                variableEnvironment = backup

                return result
            }
        }
    }

    fun callMain(program: Ast.Program): Int {
        val topLevels = program.definitions

        for (toplevel in topLevels) {
            when (toplevel) {
                is Ast.TopLevel.FunctionDefinition -> {
                    functionEnvironment[toplevel.name] = toplevel
                }
                is Ast.TopLevel.GlobalVariableDefinition -> {
                    variableEnvironment[toplevel.name] = interpret(toplevel.expr)
                }
            }
        }

        val mainFunction = functionEnvironment["main"]

        if (mainFunction != null) {
            return interpret(mainFunction.body)
        } else {
            throw RuntimeException("This program doesn't have main() function")
        }
    }
}