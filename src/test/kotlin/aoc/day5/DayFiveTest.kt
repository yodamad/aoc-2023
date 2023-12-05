package aoc.day5

import day5.DayFive


class DayFiveTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(DayFiveTest().seedsMassive())
        }
    }

    fun sample(): Long {
        return DayFive().parseData("sample")
    }

    fun seeds(): Long {
        return DayFive().parseData("seeds")
    }

    fun sampleMassive(): Long {
        return DayFive().parseMassiveData("sample")
    }

    fun seedsMassive(): Long {
        return DayFive().parseMassiveData("seeds")
    }
}