package tictactoe

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

    printGrid(userInput)

    val hasWonOnTheHorizontal = hasWonOnTheHorizontal(userInput)
    val hasWonOnTheVertical = hasWonOnTheVertical(userInput)
    val hasWonOnTheDiagonal = hasWonOnTheDiagonal(userInput)
    when {
        hasWonOnTheHorizontal.first -> {
            println("${hasWonOnTheHorizontal.second} wins")
        }
        hasWonOnTheVertical(userInput).first -> {
            println("${hasWonOnTheVertical.second} wins")
        }
        hasWonOnTheDiagonal(userInput).first -> {
            println("${hasWonOnTheDiagonal.second} wins")
        }
        isGridFull(userInput) -> {
            println("Draw")
        }
        !hasWonOnTheHorizontal.first || !hasWonOnTheVertical.first -> {
            println("Impossible")
        }
        isGameNotFinished(userInput) -> {
            println("Game not finished")
        }
    }
}

fun printGrid(userInput: String) {
    val grid =
        """
            ---------
            | ${userInput[0]} ${userInput[1]} ${userInput[2]} |
            | ${userInput[3]} ${userInput[4]} ${userInput[5]} |
            | ${userInput[6]} ${userInput[7]} ${userInput[8]} |
            ---------
        """.trimIndent()
    println(grid)
}

fun hasWonOnTheHorizontal(userInput: String): Pair<Boolean, Char?> {
    val firstRow = userInput[0] == userInput[1] && userInput[1] == userInput[2]
    val secondRow = userInput[3] == userInput[4] && userInput[4] == userInput[5]
    val thirdRow = userInput[6] == userInput[7] && userInput[7] == userInput[8]

    var playerWins = firstRow || secondRow || thirdRow
    val twoPlayersWin = (firstRow && secondRow) ||
            (secondRow && thirdRow) ||
            (firstRow && thirdRow)

    var winningPlayer: Char? = null
    if (!twoPlayersWin) {
        if (playerWins) {
            winningPlayer = when {
                firstRow -> userInput[0]
                secondRow -> userInput[3]
                thirdRow -> userInput[6]
                else -> null
            }
        }
    } else {
        playerWins = false
        winningPlayer = null
    }

    return Pair(playerWins, winningPlayer)
}

fun hasWonOnTheVertical(userInput: String): Pair<Boolean, Char?> {
    val firstCol = userInput[0] == userInput[3] && userInput[3] == userInput[6]
    val secondCol = userInput[1] == userInput[4] && userInput[4] == userInput[7]
    val thirdCol = userInput[2] == userInput[5] && userInput[5] == userInput[8]

    var playerWins = firstCol || secondCol || thirdCol
    val twoPlayersWin = (firstCol && secondCol) ||
            (secondCol && thirdCol) ||
            (firstCol && thirdCol)

    var winningPlayer: Char? = null
    if (!twoPlayersWin) {
        if (playerWins) {
            winningPlayer = when {
                firstCol -> userInput[0]
                secondCol -> userInput[1]
                thirdCol -> userInput[2]
                else -> null
            }
        }
    } else {
        playerWins = false
        winningPlayer = null
    }

    return Pair(playerWins, winningPlayer)
}

fun hasWonOnTheDiagonal(userInput: String): Pair<Boolean, Char?> {
    val firstDiagonal = userInput[0] == userInput[4] && userInput[4] == userInput[8]
    val secondDiagonal = userInput[2] == userInput[4] && userInput[4] == userInput[6]

    val playerWins = firstDiagonal || secondDiagonal

    var winningPlayer: Char? = null
    if (playerWins) {
        winningPlayer = when {
            firstDiagonal -> userInput[0]
            secondDiagonal -> userInput[2]
            else -> null
        }
    }

    return Pair(playerWins, winningPlayer)
}

fun isGridFull(userInput: String) =
    userInput.all { char -> char != '_' }

fun isGameNotFinished(userInput: String) =
    userInput.any { char -> char == '_' }
