class Interpreter {
    var environment: MutableMap<String, Int> = mutableMapOf()

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
        }
    }
}