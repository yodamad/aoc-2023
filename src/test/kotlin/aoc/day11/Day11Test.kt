package aoc.day11

import day11.Day11

class Day11Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day11Test().galaxy()
        }
    }

    fun sample() {
        return Day11().updateMap("sample")
    }

    fun galaxy() {
        return Day11().updateMap("galaxy")
    }
}