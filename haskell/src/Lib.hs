module Lib
    ( someFunc
    ) where

someFunc :: IO ()
someFunc = putStrLn "someFunc"

data Tree = Leaf Int | Node Tree Int Tree deriving (Show)