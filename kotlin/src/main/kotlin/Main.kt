fun main(args: Array<String>) {
    val interpreter = Interpreter()
    val ast = add(
        add(
            int(1),
            int(2)
        ),
        int(3)
    )

    val result = interpreter.interpret(ast)
    println(result)
}