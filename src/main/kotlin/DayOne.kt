import java.io.File

fun main(args: Array<String>) {
    val list = File("day1inp.txt").readLines().map { it.toInt() }
    part1(list)
    part2(list)
}

fun part1(list: List<Int>) {
    println(list.sum())
}

fun part2(list: List<Int>) {
    val freq = mutableSetOf(0)
    var prev = 0
    while (true) {
        list.forEach {
            prev += it
            if (!freq.add(prev)) {
                println(prev)
                return
            }
        }
    }
}