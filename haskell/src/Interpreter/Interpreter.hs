module Interpreter.Interpreter where

import Interpreter.Ast.Ast
import Interpreter.Ast.Operator
import GHC.Float


interpret :: Expr -> Either String Double
interpret ast =
  case ast of
    (Binary ope lhs rhs) ->
      let
        l = interpret lhs
        r = interpret rhs
        in case (l, r) of
          ((Right lv), (Right rv)) ->
            case ope of
              Add -> Right $ lv + rv
              Sub -> Right $ lv - rv
              Div -> if rv /= 0 then Right $ lv / rv else Left "0 division!"
    IntegerLiteral v -> Right $ int2Double v