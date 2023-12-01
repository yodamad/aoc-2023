package day1

import shared.getLines

class DayOne {
    val DAY = "day1"
    val nbMatches = arrayOf(
        "one" to "1", "two" to "2", "three" to "3", "four" to "4", "five" to "5",
        "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9"
    )
    fun computeSum(file: String) : Int {
        val lines = file.getLines(DAY)
        var sum = 0

        lines.stream().forEach {
            l -> run {
                val digits = l.toList().stream().filter { c -> c.isDigit() }.toList()
                val first = digits.first().digitToInt()
                val last = digits.last().digitToInt()
                val nb = (first.toString() + last.toString()).toInt()
                sum += nb
            }
        }
        return sum
    }

    fun computeNewSum(file: String) : Int {
        var sum = 0

        val lines = file.getLines(DAY)
        lines.forEach {
            l -> sum += (findFirst(l).toString() + findLast(l).toString()).toInt()
        }

        return sum
    }

    fun findFirst(line: String) : Int {
        var currentIndex = 0
        var first = 0
        while (first == 0) {
            // println(line.subSequence(0, currentIndex+1))
            if (line[currentIndex].isDigit())
                first = line[currentIndex].digitToInt()
            else if (nbMatches.any { p -> line.subSequence(0, currentIndex+1).endsWith(p.first) })
                first = nbMatches.first { p -> line.subSequence(0, currentIndex+1).endsWith(p.first) }.second.toInt()
            else
                currentIndex++
        }
        return first
    }

    fun findLast(line: String) : Int {
        var currentIndex = 0
        var last = 0
        val lineSize = line.length

        while (last == 0) {
            // println(line.subSequence(0, currentIndex+1))
            if (line[lineSize - currentIndex - 1].isDigit())
                last = line[lineSize - currentIndex - 1].digitToInt()
            else if (nbMatches.any { p -> line.subSequence(lineSize - currentIndex - 1, lineSize).startsWith(p.first) })
                last = nbMatches.first { p -> line.subSequence(lineSize - currentIndex - 1, lineSize).startsWith(p.first) }.second.toInt()
            else
                currentIndex++
        }
        return last
    }
}