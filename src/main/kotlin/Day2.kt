import java.io.File

fun main(args: Array<String>) {
    val input = File("day2inp.txt").readLines()
    val day = DayTwo()
    day.part1(input)
    day.part1ExtraKotlin(input)
    day.part2(input)
}

class DayTwo {

    fun part1(input: List<String>) {
        var two = 0
        var three = 0
        input.forEach { word ->
            val ch = word.groupingBy { it }.eachCount()
            if (ch.containsValue(2)) {
                two++
            }
            if (ch.containsValue(3)) {
                three++
            }
        }
        println("Checksum: ${two * three}")
    }

    fun part1ExtraKotlin(input: List<String>) {
        val counts = input.map { word ->
            word.groupingBy { it }.eachCount().values
        }
        println("Checksum: ${counts.count { 2 in it } * counts.count { 3 in it }}")
    }

    fun part2(input: List<String>) {
        input.forEachIndexed { index, wordOne ->
            (index + 1 until input.size).forEach {
                val wordTwo = input[it]
                val word = wordOne.filterIndexed { index, c ->
                    c == wordTwo[index]
                }
                if (word.length == wordOne.length - 1) {
                    println("The word is: $word")
                    return
                }
            }
        }
    }
}