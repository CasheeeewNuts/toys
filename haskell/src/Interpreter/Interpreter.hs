{-# LANGUAGE Strict #-}

module Interpreter.Interpreter where

import Interpreter.Ast.Expr
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
              Mul -> Right $ lv * rv
              Div -> if rv /= 0 then Right $ lv / rv else Left "0 division!"
              LessThan -> Right $ if lv < rv then true else false
              LessOrEqual -> Right $ if lv <= rv then true else false
              GreaterThan -> Right $ if lv > rv then true else false
              GreaterOrEqual -> Right $ if lv >= rv then true else false
              Equal -> Right $ if lv == rv then true else false
              NotEqual -> Right $ if lv /= rv then true else false
          ((Left err), _) -> Left err
          (_, (Left err)) -> Left err
        where
          true = 1
          false = 0
    IntegerLiteral v -> Right $ int2Double v