module Interpreter.Ast.Ast where

import Interpreter.Ast.Operator

data Expr = Binary Operator Expr Expr | IntegerLiteral Int deriving (Show)

add :: Expr -> Expr -> Expr
add lhs rhs = Binary Add lhs rhs

sub :: Expr -> Expr -> Expr
sub lhs rhs = Binary Sub lhs rhs

int :: Int -> Expr
int v = IntegerLiteral v