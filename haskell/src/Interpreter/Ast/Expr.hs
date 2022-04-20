module Interpreter.Ast.Expr where

import Interpreter.Ast.Operator

data Expr = Binary Operator Expr Expr 
  | IntegerLiteral Int
  deriving (Show)

add :: Expr -> Expr -> Expr
add lhs rhs = Binary Add lhs rhs

sub :: Expr -> Expr -> Expr
sub lhs rhs = Binary Sub lhs rhs

mul :: Expr -> Expr -> Expr
mul lhs rhs = Binary Mul lhs rhs

div :: Expr -> Expr -> Expr
div lhs rhs = Binary Div lhs rhs

int :: Int -> Expr
int v = IntegerLiteral v