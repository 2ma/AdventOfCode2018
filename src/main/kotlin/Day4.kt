import java.io.File

fun main(args: Array<String>) {
    val input = File("day4inp.txt").readLines()
    val day = Day4()
    day.part1(input)
    //day.part2(input)
}

class Day4 {

    fun part1(input: List<String>) {
        val gIdPattern = "(?<=#)\\d+".toRegex()
        val timePattern = "(?<=:)\\d+".toRegex()
        var currentGuardId: Int = -1
        var sleep: Int = -1
        val schedule: MutableMap<Int, MutableMap<Int, Int>> = mutableMapOf()
        input.sorted().forEach { timeStamp ->
            println(timeStamp)
            when {
                timeStamp.contains("Guard") -> {
                    currentGuardId = gIdPattern.find(timeStamp, 0)?.value?.toInt() ?: -1
                }
                timeStamp.contains("sleep") -> {
                    sleep = timePattern.find(timeStamp, 0)?.value?.toInt() ?: -1
                }
                timeStamp.contains("wakes") -> {
                    val guardSchedule = schedule.getOrDefault(currentGuardId, mutableMapOf())
                    val awake = timePattern.find(timeStamp, 0)?.value?.toInt() ?: -1
                    (sleep until awake).forEach {
                        guardSchedule[it] = guardSchedule.getOrDefault(it, 1) + 1
                    }
                    schedule[currentGuardId] = guardSchedule
                }
            }
        }

        val sleepyGuardId: Int =
            schedule.map { sched -> Pair(sched.key, sched.value.values.sum()) }.maxBy { it.second }?.first ?: -1
        val sleepyGuardSchedule = schedule[sleepyGuardId]
        val minute = sleepyGuardSchedule?.maxBy { it.value }?.key ?: -1
        println("Id times minute: ${sleepyGuardId * minute}")

        //PART 2
        var part2Solution: Pair<Int, Int>? = null
        var part2Minute = -1
        schedule.forEach { gId, sched ->
            sched.forEach { min, value ->
                if (value > part2Minute) {
                    part2Minute = value
                    part2Solution = Pair(gId, min)
                }
            }
        }
        part2Solution?.run {
            println("Part2 solution: ${first * second}")
        }
    }

    fun part2(input: List<String>) {
        //see part1 end
    }
}