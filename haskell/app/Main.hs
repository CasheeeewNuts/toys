module Main where

import Lib
import Interpreter.Interpreter
import Interpreter.Ast.Ast

ast = add (int 1) (Interpreter.Ast.Ast.div (int 1) (int 3))

main :: IO ()
main = do
  print $ interpret ast