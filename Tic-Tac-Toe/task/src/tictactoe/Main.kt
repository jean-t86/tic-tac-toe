package tictactoe

fun main() {
    // write your code here
    //region Get user input
    print("Enter cells: ")
    val userInput = readLine()
    if (userInput == null || userInput.length != 9) {
        throw IllegalArgumentException("Input should be exactly 9 characters of either X, O, or _")
    }
    if (!userInput.all { it == 'X' || it == 'O' || it == '_' }) {
        throw IllegalArgumentException("Input should only consist of Xs, Ox, or _ (underscores")
    }
    //endregion

    val t2d: MutableList<List<Char>> = mutableListOf()
    val inputList = userInput.toList()
    t2d.add(inputList.subList(0, 3))
    t2d.add(inputList.subList(3, 6))
    t2d.add(inputList.subList(6, 9))


    printGrid(t2d)
}

fun printGrid(inputList: List<List<Char>>) {
    println("-".repeat(9))

    for (col in 0..2) {
        print("| ")
        for (row in 0..2) {
            print(inputList[col][row])
            print(" ")
        }
        print("|")
        println()
    }

    println("-".repeat(9))
}
