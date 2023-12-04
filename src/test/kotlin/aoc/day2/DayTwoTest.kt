package aoc.day2

import day2.DayTwo

class DayTwoTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(DayTwoTest().optimized())
        }
    }

    fun sample(): Int {
        return DayTwo().computeGame("sample")
    }

    fun findIt(): Int {
        return DayTwo().computeGame("input1")
    }

    fun sampleOptimized(): Int {
        return DayTwo().computeOptimizedGame("sample")
    }

    fun optimized(): Int {
        return DayTwo().computeOptimizedGame("input1")
    }
}