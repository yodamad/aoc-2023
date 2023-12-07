package aoc.day6

import day6.DaySix

class DaySixTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val current = System.currentTimeMillis()
            println(DaySixTest().singleRecord())
            println("${System.currentTimeMillis() - current}ms")
        }
    }

    fun sample(): Int {
        return DaySix().race("sample")
    }

    fun getRecords(): Int {
        return DaySix().race("infos")
    }

    fun singleSample(): Int {
        return DaySix().singleRace("sample")
    }

    fun singleRecord(): Int {
        return DaySix().singleRace("infos")
    }
}