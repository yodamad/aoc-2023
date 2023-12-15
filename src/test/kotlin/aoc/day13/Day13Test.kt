package aoc.day13

import Day13

class Day13Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Day13Test().sampleWithSmudges())
        }
    }

    private fun sample(): Int {
        return Day13().symmetry("sample")
    }

    private fun mirrors(): Int {
        return Day13().symmetry("mirrors")
    }

    private fun sampleWithSmudges() {
        return Day13().symmetryWithSmudges("sample")
    }
}