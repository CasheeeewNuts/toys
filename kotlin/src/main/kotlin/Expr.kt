import kotlin.Int

enum class Operator {
    Add,
    Sub,
}

sealed class Expr {
    data class Binary(val operator: Operator, val lhs: Expr, val rhs: Expr) : Expr()
    data class IntegerLiteral(val value: Int) : Expr()
}

fun add(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.Add, lhs, rhs)
}

fun sub(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.Sub, lhs, rhs)
}

fun int(value: Int): Expr.IntegerLiteral {
    return Expr.IntegerLiteral(value)
}