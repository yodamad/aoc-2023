package aoc.day10

import day10.Day10

class Day10Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day10Test().findPath()
        }
    }

    fun simpleSample() {
        return Day10().explore("simple_sample")
    }

    fun sample() {
        return Day10().explore("sample")
    }

    fun findPath() {
        return Day10().explore("input")
    }
}