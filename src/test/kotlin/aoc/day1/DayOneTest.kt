package aoc.day1

import day1.DayOne

class DayOneTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(DayOneTest().secondStep())
        }
    }

    fun sample(): Int {
        return DayOne().computeSum("sample")
    }

    fun sample2(): Int {
        return DayOne().computeNewSum("sample2")
    }

    fun firstStep(): Int {
        return DayOne().computeSum("input")
    }

    fun secondStep(): Int {
        return DayOne().computeNewSum("input")
    }
}