import kotlin.Int

enum class Operator {
    Add,
    Sub,
    Mul,
    Div
}

sealed class Expr {
    data class Binary(val operator: Operator, val lhs: Expr, val rhs: Expr) : Expr()
    data class IntegerLiteral(val value: Int) : Expr()
    data class Assignment(val identifier: String, val expr: Expr) : Expr()
    data class Identifier(val name: String) : Expr()
}

fun add(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.Add, lhs, rhs)
}

fun sub(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.Sub, lhs, rhs)
}

fun mul(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.Mul, lhs, rhs)
}

fun div(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.Div, lhs, rhs)
}

fun int(value: Int): Expr.IntegerLiteral {
    return Expr.IntegerLiteral(value)
}

fun assign(identifier: String, expr: Expr): Expr.Assignment {
    return Expr.Assignment(identifier, expr)
}

fun identify(identifier: String): Expr.Identifier {
    return Expr.Identifier(identifier)
}