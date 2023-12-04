package aoc.day4

import day4.DayFour

class DayFourTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(DayFourTest().optim())
        }
    }

    fun sample(): Int {
        return DayFour().winingCard("sample")
    }
    fun wining(): Int {
        return DayFour().winingCard("cards")
    }

    fun sampleOptim(): Int {
        return DayFour().optimizedCards("sample")
    }

    fun optim(): Int {
        return DayFour().optimizedCards("cards")
    }
}