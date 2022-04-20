class Interpreter {
    private var environment: MutableMap<String, Int> = mutableMapOf()
    companion object {
        const val TRUE = 1
        const val FALSE = 0
    }

    fun interpret(ast: Expr): Int {
        return when (ast) {
            is Expr.Binary -> {
                val lhs = interpret(ast.lhs)
                val rhs = interpret(ast.rhs)

                when (ast.operator) {
                    Operator.Add -> lhs + rhs
                    Operator.Sub -> lhs - rhs
                    Operator.Mul -> lhs * rhs
                    Operator.Div -> lhs / rhs
                    Operator.LessThan -> if (lhs < rhs) TRUE else FALSE
                    Operator.LessOrEqual -> if (lhs <= rhs) TRUE else FALSE
                    Operator.GreaterThan -> if (lhs > rhs) TRUE else FALSE
                    Operator.GreaterOrEqual -> if (lhs >= rhs) TRUE else FALSE
                    Operator.Equal -> if (lhs == rhs) TRUE else FALSE
                    Operator.NotEqual -> if (lhs != rhs) TRUE else FALSE
                }
            }
            is Expr.IntegerLiteral -> ast.value
            is Expr.Assignment -> {
                val value = interpret(ast.expr)

                environment[ast.identifier] = value

                return value
            }
            is Expr.Identifier -> {
                val result = environment[ast.name] ?: throw RuntimeException("undefined variable!")

                result
            }
            is Expr.If -> {
                val condition = interpret(ast.condition)

                return if (condition != FALSE) {
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

                    if (condition != FALSE) {
                        interpret(ast.body)
                    } else {
                        break
                    }
                }

                1
            }
        }
    }
}