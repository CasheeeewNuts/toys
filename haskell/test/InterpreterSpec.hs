module InterpreterSpec where

import Prelude hiding (div)
import Data.Either
import Test.Hspec
import Interpreter.Ast.Expr as E
import Interpreter.Interpreter

spec :: Spec
spec = describe "Interpreter" $ do
  describe "add" $ do
    it "1 + 1 = 2" $ do
      let ast = add (int 1) (int 1)
      (interpret ast) `shouldBe` Right 2
  describe "sub" $ do
    it "1 - 1 = 0" $ do
      let ast = sub (int 1) (int 1)
      (interpret ast) `shouldBe` Right 0
  describe "mul" $ do
    it "2 * 2 = 4" $ do
      let ast = mul (int 2) (int 2)
      (interpret ast) `shouldBe` Right 4
  describe "div" $ do
    it "4 / 2 = 2" $ do
      let ast = div (int 4) (int 2)
      (interpret ast) `shouldBe` Right 2
    it "4 / 0 -> 0 division error" $ do
      let ast = div (int 4) (int 0)
      isLeft (interpret ast) `shouldBe` True