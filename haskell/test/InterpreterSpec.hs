module InterpreterSpec where

import Test.Hspec
import Interpreter.Ast.Expr
import Interpreter.Interpreter

spec :: Spec
spec = describe "Interpreter" $ do
  it "1 + 1 = 2" $ do
    let ast = add (int 1) (int 1)
    let result = interpret ast
    (result == Right 2) `shouldBe` True