import kotlin.Int

enum class Operator {
    Add,
    Sub,
    Mul,
    Div,
    LessThan,
    LessOrEqual,
    GreaterThan,
    GreaterOrEqual,
    Equal,
    NotEqual
}

sealed class Expr {
    data class Binary(val operator: Operator, val lhs: Expr, val rhs: Expr) : Expr()
    data class IntegerLiteral(val value: Int) : Expr()
    data class Assignment(val identifier: String, val expr: Expr) : Expr()
    data class Identifier(val name: String) : Expr()
    data class If(val condition: Expr, val thenClause: Expr, val elseClause: Expr?) : Expr()
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

fun lessThan(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.LessThan, lhs, rhs)
}

fun lessOrEqual(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.LessOrEqual, lhs, rhs)
}

fun greaterThan(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.GreaterThan, lhs, rhs)
}

fun greaterOrEqual(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.GreaterOrEqual, lhs, rhs)
}

fun equal(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.Equal, lhs, rhs)
}

fun notEqual(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.NotEqual, lhs, rhs)
}

fun ifExpr(condition: Expr, thenClause: Expr, elseClause: Expr?): Expr.If {
    return Expr.If(condition, thenClause, elseClause)
}