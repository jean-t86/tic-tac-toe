package tictactoe

import kotlin.math.abs

fun main() {
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

    val gameBoard = gameBoardGrid(userInput)
    printGrid(gameBoard)

    when {
        !isBoardValid(gameBoard, userInput) -> println("Impossible")
        hasWon('O', gameBoard) -> println("O wins")
        hasWon('X', gameBoard) -> println("X wins")
        isDraw(gameBoard, userInput) -> println("Draw")
        else -> println("Game not finished")
    }
}

fun gameBoardGrid(userInput: String): List<List<Char>> {
    val gameBoard: MutableList<List<Char>> = mutableListOf()
    val inputList = userInput.toList()
    gameBoard.add(inputList.subList(0, 3))
    gameBoard.add(inputList.subList(3, 6))
    gameBoard.add(inputList.subList(6, 9))

    return gameBoard
}

fun printGrid(gameBoard: List<List<Char>>) {
    println("-".repeat(9))

    for (col in 0..2) {
        print("| ")
        for (row in 0..2) {
            print(gameBoard[col][row])
            print(" ")
        }
        print("|")
        println()
    }

    println("-".repeat(9))
}

fun isDraw(gameBoard: List<List<Char>>, userInput: String): Boolean {
    return !hasWon('X', gameBoard) &&
            !hasWon('O', gameBoard) &&
            userInput.all { it == 'O' || it == 'X' }
}

fun hasWon(char: Char, gameBoard: List<List<Char>>): Boolean {
    var threeInARow = false

    rowWin@ for (col in 0..2) {
        var charInARow = 0
        for (row in 0..2) {
            // Travers board game by rows
            if (gameBoard[col][row] == char) {
                charInARow++
            }
        }
        if (charInARow == 3) {
            threeInARow = true
            break@rowWin
        }
    }

    colWin@ for (col in 0..2) {
        var charInACol = 0
        for (row in 0..2) {
            // Travers board game by columns
            if (gameBoard[row][col] == char) {
                charInACol++
            }
        }
        if (charInACol == 3) {
            threeInARow = true
            break@colWin
        }
    }

    threeInARow = threeInARow ||
            (gameBoard[0][0] == char &&
                    gameBoard[1][1] == char &&
                    gameBoard[2][2] == char)

    threeInARow = threeInARow ||
            (gameBoard[0][2] == char &&
                    gameBoard[1][1] == char &&
                    gameBoard[2][0] == char)

    return threeInARow
}

fun isBoardValid(gameBoard: List<List<Char>>, userInput: String): Boolean =
    !(hasWon('X', gameBoard) && hasWon('O', gameBoard)) &&
            abs(userInput.count { it == 'X' } - userInput.count { it == 'O' }) < 2
