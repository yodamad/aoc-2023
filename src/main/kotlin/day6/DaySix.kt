package day6

import shared.getLines

class DaySix {
    val DAY = "day6"

    fun race(file:String): Int {
        val times = file.getLines(DAY)[0].split(":")[1].trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
        val records = file.getLines(DAY)[1].split(":")[1].trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }

        println(times)
        println(records)

        // x * (n-x)
        var result = 1
        times.forEachIndexed { idx, time ->
            result *= (0 .. time).map { it * (time - it) }.count { records[idx] < it }
        }
        return result
    }

    fun singleRace(file:String): Int {
        val time = file.getLines(DAY)[0].split(":")[1].trim().replace(" ", "").toLong()
        val record = file.getLines(DAY)[1].split(":")[1].trim().replace(" ", "").toLong()

        // x * (n-x)
        return (0 .. time).map { it * (time - it) }.count { record < it }
    }
}