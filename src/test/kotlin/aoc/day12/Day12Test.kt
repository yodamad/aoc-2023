package aoc.day12

import day12.Day12

class Day12Test {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day12Test().sampleUnfold()
        }
    }

    fun sample() {
        Day12().parse("sample")
    }

    fun sampleUnfold() {
        Day12().parseUnfold("sample")
    }

    fun dots() {
        Day12().parse("dots")
    }
}