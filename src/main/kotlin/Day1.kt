import java.io.File

fun main(args: Array<String>) {
    val input = File("day1inp.txt").readLines().map { it.toInt() }
    val day = DayOne()
    day.part1(input)
    day.part2(input)
}

class DayOne {
    fun part1(input: List<Int>) {
        println(input.sum())
    }

    fun part2(input: List<Int>) {
        val freq = mutableSetOf(0)
        var prev = 0
        while (true) {
            input.forEach {
                prev += it
                if (!freq.add(prev)) {
                    println(prev)
                    return
                }
            }
        }
    }
}