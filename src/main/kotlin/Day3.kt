import java.io.File

fun main(args: Array<String>) {
    val input = File("day3inp.txt").readLines()
    val day = DayThree()
    day.part1(input)
    day.part2(input)
}

class DayThree {

    fun part1(input: List<String>) {
        val p = Regex("\\d+")
        val cloth = mutableMapOf<Int, MutableMap<Int, Int>>()
        input.forEach { line ->
            val (id, x, y, xEnd, yEnd) = p.findAll(line).map { it.value.toInt() }.toList()
            (0 until xEnd).forEach { i ->
                val xPos = x + i
                val mapOne = cloth.getOrDefault(xPos, mutableMapOf())
                (0 until yEnd).forEach {
                    val yPos = y + it
                    mapOne[yPos] = mapOne.getOrDefault(yPos, 0) + 1
                }
                cloth[xPos] = mapOne
            }
        }
        println("Bad fields: ${cloth.map { m -> m.value.count { it.value > 1 } }.sum()}")
    }

    fun part2(input: List<String>) {
        val p = Regex("\\d+")
        val claimList = mutableListOf<Claim>()
        val ids = mutableSetOf<Int>()
        input.forEach { line ->
            val (id, x, y, xEnd, yEnd) = p.findAll(line).map { it.value.toInt() }.toList()
            ids.add(id)
            claimList.add(Claim(id, x, x + xEnd, y, y + yEnd))
        }
        claimList.forEachIndexed { index, claim ->
            (index + 1 until claimList.size).forEach {
                val c2 = claimList[it]
                if (claim.x < c2.xEnd && claim.xEnd > c2.x && claim.y < c2.yEnd && claim.yEnd > c2.y) {
                    ids.remove(claim.id)
                    ids.remove(c2.id)
                }
            }
        }
        ids.forEach { println("Id: $it") }
    }

}

data class Claim(val id: Int, val x: Int, val xEnd: Int, val y: Int, val yEnd: Int)