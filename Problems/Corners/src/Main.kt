fun main() {
    // Do not touch code below
    var inputArray: Array<Array<String>> = arrayOf()
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        val strings = readLine()!!.split(' ').toTypedArray()
        inputArray += strings
    }
    // write your code here
    val topLeft = inputArray[0][0]
    val firstRow = inputArray[0]
    val topRight = inputArray[0][firstRow.size - 1]

    val lastRow = inputArray[inputArray.size - 1]
    val bottomLeft = inputArray[inputArray.size - 1][0]
    val bottomRight = inputArray[inputArray.size - 1][lastRow.size - 1]

    println(
        """
        $topLeft $topRight
        $bottomLeft $bottomRight
    """.trimIndent()
    )
}
