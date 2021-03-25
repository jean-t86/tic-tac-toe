import kotlin.math.abs

fun main() {
    val xy1 = readLine()!!.split(" ").map {
        abs(it.toInt())
    }
    val xy2 = readLine()!!.split(" ").map {
        abs(it.toInt())
    }
    val (x1, y1) = xy1
    val (x2, y2) = xy2

    if (canKillDiagonally(x1, y1, x2, y2) || canKillStraightLine(x1, y1, x2, y2)) {
        println("YES")
    } else {
        println("NO")
    }
}

fun canKillDiagonally(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    val y = abs(y2 - y1).toDouble()
    val x = abs(x2 - x1)
    return y / x == 1.0
}

fun canKillStraightLine(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =
    x1 == x2 || y1 == y2
