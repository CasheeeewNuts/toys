module Main where

import Lib
import Interpreter.Interpreter
import Interpreter.Ast.Expr

ast = add (Interpreter.Ast.Expr.div (int 1) (int 0)) (int 1)

main :: IO ()
main = do
  print $ interpret ast