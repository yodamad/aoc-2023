package aoc.day15

import day15.Day15

class Day15Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day15Test().lens()
        }
    }

    fun sample_hash() { Day15().hash("sample_hash")}

    fun sample() { Day15().hash("sample")}

    fun part1() { Day15().hash("input")}

    fun lensSample() { Day15().lenses("sample") }

    fun lens() { Day15().lenses("input") }
}