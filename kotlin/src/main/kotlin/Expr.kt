import kotlin.Int
import Ast.Expr

fun add(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.ADD, lhs, rhs)
}

fun sub(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.SUB, lhs, rhs)
}

fun mul(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.MUL, lhs, rhs)
}

fun div(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.DIV, lhs, rhs)
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
    return Expr.Binary(Operator.LESS_THAN, lhs, rhs)
}

fun lessOrEqual(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.LESS_OR_EQUAL, lhs, rhs)
}

fun greaterThan(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.GREATER_THAN, lhs, rhs)
}

fun greaterOrEqual(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.GREATER_OR_EQUAL, lhs, rhs)
}

fun equal(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.EQUAL, lhs, rhs)
}

fun notEqual(lhs: Expr, rhs: Expr): Expr.Binary {
    return Expr.Binary(Operator.NOT_EQUAL, lhs, rhs)
}

fun ifExpr(condition: Expr, thenClause: Expr, elseClause: Expr?): Expr.If {
    return Expr.If(condition, thenClause, elseClause)
}

fun whileExpr(condition: Expr, body: Expr): Expr.While {
    return Expr.While(condition, body)
}

fun block(elements: List<Expr>): Expr.Block {
    return Expr.Block(elements)
}