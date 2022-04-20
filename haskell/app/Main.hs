module Main where

import Lib
import Interpreter.Interpreter
import Interpreter.Ast.Ast

ast = add (int 1) (add (int 1) (int 3))

main :: IO ()
main = do
  print $ interpret ast
  print ast
