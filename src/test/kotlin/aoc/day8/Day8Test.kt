package aoc.day8

import day8.Day8

class Day8Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day8Test().ghostNav()
        }
    }

    fun simpleNav() {
        return Day8().navigate("sample")
    }

    fun nav() {
        return Day8().navigate("map")
    }

    fun simpleGhostNav() {
        return Day8().navigateAsGhost("sampleGhost")
    }

    fun ghostNav() {
        return Day8().navigateAsGhost("map")
    }
}