package aoc.day3

import day3.DayThree

class DayThreeTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(DayThreeTest().gear())
        }
    }

    fun sample(): Int {
        return DayThree().analyzeEngine("sample")
    }

    fun compute(): Int {
        return DayThree().analyzeEngine("input")
    }

    fun sampleGear() {
        return DayThree().findStar("sample")
    }

    fun gear() {
        return DayThree().findStar("input")
    }
}