package tictactoe

import kotlin.math.abs

fun main() {
//    print("Enter cells: ")
//    val userInput = readLine()
//    if (userInput == null || userInput.length != 9) {
//        throw IllegalArgumentException("Input should be exactly 9 characters of either X, O, or _")
//    }
//    if (!userInput.all { it == 'X' || it == 'O' || it == '_' }) {
//        throw IllegalArgumentException("Input should only consist of Xs, Ox, or _ (underscores")
//    }
    val emptyGrid = "_________"
    val gameBoard = gameBoardGrid(emptyGrid)
    printGrid(gameBoard)

    var gameplay = false
    var xTurnToPlay = true
    do {
        val player = if (xTurnToPlay) 'X' else 'O'
        print("Enter the coordinates: ")
        val coords = readLine()!!.trim().split(" ")

        if (!isValidInputForCoordinates(coords, gameBoard)) {
            continue
        }

        val intCoords = coords.map { it.toInt() }
        val x = intCoords.first()
        val y = intCoords.last()
        gameBoard[x - 1][y - 1] = player
        printGrid(gameBoard)

        when {
//        !isBoardValid(gameBoard, userInput) -> println("Impossible")
            hasWon('O', gameBoard) -> {
                println("O wins")
                gameplay = true
            }
            hasWon('X', gameBoard) -> {
                println("X wins")
                gameplay = true
            }
            isDraw(gameBoard) -> {
                println("Draw")
                gameplay = true
            }
//        else -> println("Game not finished")
        }
        xTurnToPlay = !xTurnToPlay
    } while (!gameplay)
}

fun isValidInputForCoordinates(coords: List<String>, gameBoard: MutableList<MutableList<Char>>): Boolean {
    if (coords.any { it.toIntOrNull() == null }) {
        println("You should enter numbers!")
        return false
    }

    val intCoords = coords.map { it.toInt() }
    val x = intCoords.first()
    val y = intCoords.last()
    if (x !in 1..3 || y !in 1..3) {
        println("Coordinates should be from 1 to 3!")
        return false
    }

    if (gameBoard[x - 1][y - 1] == 'X' ||
        gameBoard[x - 1][y - 1] == 'O'
    ) {
        println("This cell is occupied! Choose another one!")
        return false
    }

    return true
}

fun gameBoardGrid(userInput: String): MutableList<MutableList<Char>> {
    val gameBoard: MutableList<MutableList<Char>> = mutableListOf()
    val inputList = userInput.toMutableList()
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

fun isDraw(gameBoard: List<List<Char>>): Boolean {
    return !hasWon('X', gameBoard) &&
            !hasWon('O', gameBoard) &&
            gameBoard.flatten().all { it == 'O' || it == 'X' }
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
