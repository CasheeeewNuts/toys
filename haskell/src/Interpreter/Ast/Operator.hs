module Interpreter.Ast.Operator where

data Operator = Add | Sub | Mul | Div | LessThan | LessOrEqual | GreaterThan | GreaterOrEqual | Equal | NotEqual deriving (Show, Enum)