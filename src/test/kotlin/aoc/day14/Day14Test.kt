package aoc.day14

import day14.Day14

class Day14Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day14Test().map()
        }
    }

    fun sample() { Day14().organize("sample") }
    fun map() { Day14().organize("map") }
}