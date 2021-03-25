package tictactoe

fun main() {
    // write your code here
}

fun printGrid(userInput: String) {
    for (row in 1..3) {
        for (col in 1..3) {
            if (col % 2 == 0) {
                print("X ")
            } else {
                print("O ")
            }
        }
        println()
    }
}
