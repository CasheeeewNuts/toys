use std::collections::HashMap;
use std::sync::Mutex;

enum Operator {
    ADD,
    SUB,
    MUL,
    DIV,
}

enum Expression {
    Binary(Operator, HeapedExpression, HeapedExpression),
    Int(i32),
    Assignment(String, HeapedExpression),
    Identifier(String)
}

type HeapedExpression = Box<Expression>;

fn add(lhs: HeapedExpression, rhs: HeapedExpression) -> HeapedExpression {
    Box::new(Expression::Binary(Operator::ADD, lhs, rhs))
}

fn sub(lhs: HeapedExpression, rhs: HeapedExpression) -> HeapedExpression {
    Box::new(Expression::Binary(Operator::SUB, lhs, rhs))
}

fn mul(lhs: HeapedExpression, rhs: HeapedExpression) -> HeapedExpression {
    Box::new(Expression::Binary(Operator::MUL, lhs, rhs))
}

fn div(lhs: HeapedExpression, rhs: HeapedExpression) -> HeapedExpression {
    Box::new(Expression::Binary(Operator::DIV, lhs, rhs))
}

fn assign(identifier: &str, expression: HeapedExpression) -> HeapedExpression {
    Box::new(Expression::Assignment(identifier.to_string(), expression))
}

fn ident(identifier: &str) -> HeapedExpression {
    Box::new(Expression::Identifier(identifier.to_string()))
}

fn int(value: i32) -> HeapedExpression {
    Box::new(Expression::Int(value))
}

struct Interpreter {
    env: Mutex<HashMap<String, i32>>
}

impl Interpreter {
    fn new() -> Interpreter {
        Interpreter {
            env: Mutex::new(HashMap::new())
        }
    }

    fn interpret(&self, ast: HeapedExpression) -> i32 {
        return match *ast {
            Expression::Binary(ope, lhs, rhs) => {
                let lhs = self.interpret(lhs);
                let rhs = self.interpret(rhs);

                match ope {
                    Operator::ADD => lhs + rhs,
                    Operator::SUB => lhs - rhs,
                    Operator::MUL => lhs * rhs,
                    Operator::DIV => lhs / rhs
                }
            },
            Expression::Int(int) => int,
            Expression::Identifier(identifier) => *self.env.lock().unwrap().get(&identifier).expect("undefined identifier"),
            Expression::Assignment(identifier, ast) => {
                let value = self.interpret(ast);
                let env = &mut (*self.env.lock().unwrap());
                env.insert(identifier, value);
                value
            }
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    mod plus {
        use super::*;

        #[test]
        fn test_10_plus_20_should_work() {
            let interpreter = Interpreter::new();
            let ast = add(
                int(10),
                int(20)
            );

            assert_eq!(interpreter.interpret(ast), 30)
        }

        #[test]
        fn test_10_plus_10_plus_10_should_work() {
            let interpreter = Interpreter::new();
            let ast = add(
                int(10),
                add(
                    int(10),
                    int(10)
                )
            );

            assert_eq!(interpreter.interpret(ast), 30)
        }
    }

    #[test]
    fn it_works() {
        let interpreter = Interpreter::new();
        let ast = add(
            mul(
                int(10),
                int(2)
            ),
            div(
                int(3),
                int(3)
            )
        );

        assert_eq!(interpreter.interpret(ast), 21);

        interpreter.interpret(assign("a", sub(
            int(2),
            int(1)
        )));

        let ast = add(
            ident("a"),
            assign("b", int(1))
        );
        assert_eq!(interpreter.interpret(ast), 2);
    }
}