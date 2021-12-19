interface Ast {
    sealed interface Expr {
        data class Binary(val operator: Operator, val lhs: Expr, val rhs: Expr) : Expr
        data class IntegerLiteral(val value: Int) : Expr
        data class Assignment(val identifier: String, val expr: Expr) : Expr
        data class Identifier(val name: String) : Expr
        data class If(val condition: Expr, val thenClause: Expr, val elseClause: Expr?) : Expr
        data class While(val condition: Expr, val body: Expr) : Expr
        data class Block(val elements: List<Expr>) : Expr
        data class FunctionCall(val name: String, val args: List<Expr>) : Expr
    }

    sealed interface TopLevel {
        data class FunctionDefinition(val name: String, val args: List<String>, val body: Expr) : TopLevel
    }

    data class Program(val definitions: List<TopLevel>)
}