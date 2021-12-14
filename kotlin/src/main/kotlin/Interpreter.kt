class Interpreter {
    fun interpret(ast: Expr): Int {
        return when (ast) {
            is Expr.Binary -> {
                val lhs = interpret(ast.lhs)
                val rhs = interpret(ast.rhs)

                when (ast.operator) {
                    Operator.Add -> lhs + rhs
                    Operator.Sub -> lhs - rhs
                }
            }
            is Expr.IntegerLiteral -> ast.value
        }
    }
}