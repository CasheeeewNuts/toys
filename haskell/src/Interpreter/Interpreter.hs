module Interpreter.Interpreter where

import Interpreter.Ast.Ast
import Interpreter.Ast.Operator


interpret :: Expr -> Maybe Int
interpret ast =
  case ast of
    (Binary ope lhs rhs) -> case ope of
      Add -> Just 1
      Sub -> Just 2
    IntegerLiteral v -> Just v