package aoc.day9

import day9.Day9

class Day9Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Day9Test().reversedNumbers())
        }
    }

    fun sample(): Int {
        return Day9().findMissing("sample")
    }

    fun numbers(): Int {
        return Day9().findMissing("numbers")
    }

    fun reversedSample(): Int {
        return Day9().findStartingMissing("sample")
    }

    fun reversedNumbers(): Int {
        return Day9().findStartingMissing("numbers")
    }
}