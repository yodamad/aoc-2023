package aoc.day7

import day7.DaySeven

class DaySevenTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val d = System.currentTimeMillis()
            println(DaySevenTest().bidWithJoker())
            println("${System.currentTimeMillis() - d}ms")
        }
    }

    fun sample() {
        return DaySeven().play("sample")
    }

    fun bid() {
        return DaySeven().play("bets")
    }

    fun sampleWithJoker() {
        return DaySeven().playWithJoker("sample")
    }

    fun bidWithJoker() {
        return DaySeven().playWithJoker("bets")
    }
}