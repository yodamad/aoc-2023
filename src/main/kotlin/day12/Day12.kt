package day12

import shared.getLines
import java.lang.StringBuilder

class Day12 {
    val DAY = "day12"

    fun parse(file: String) {
        var sizes: List<Int> = emptyList()
        var dots = ""
        var sum = 0

        file.getLines(DAY).forEach {
            sizes = it.split(" ")[1].split(",").map(String::toInt)
            dots = it.split(" ")[0]
            sum += analyze(dots, sizes, 0)

        }

        println(sum)
    }

    fun parseUnfold(file: String) {
        var sizes: MutableList<Int> = mutableListOf()
        var dots = ""
        var sum = 0

        file.getLines(DAY).forEach {l ->
            (0..5).forEach { _ ->
                sizes.addAll(l.split(" ")[1].split(",").map(String::toInt))
                dots += l.split(" ")[0] + "?"
            }
            sum += analyze(dots, sizes, 0)
            println("Done line $l = $sum")
        }

        println(sum)
    }

    private fun analyze(dots: String, sizes: List<Int>, currentIdx: Int): Int {
        if (currentIdx == dots.length && isValidLine(dots, sizes)) {
            return 1
        } else if (currentIdx == dots.length) return 0
        return when (dots[currentIdx]) {
            '?' -> analyze(updateDots(dots, currentIdx, '#'), sizes, currentIdx+1) + analyze(updateDots(dots, currentIdx, '.'), sizes, currentIdx+1)
            '.' -> if (isInvalidLine(dots.substring(0, currentIdx), sizes)) 0 else analyze(dots, sizes, currentIdx+1)
            else -> analyze(dots, sizes, currentIdx+1)
        }
    }

    private fun updateDots(dots: String, index: Int, c: Char) =
        StringBuilder(dots).also { it.setCharAt(index, c) }.toString()

    private fun isValidLine(dots: String, sizes: List<Int>) =
        dots.split(".").filter { it.isNotEmpty() }.map { it.length } == sizes

    private fun isInvalidLine(dots: String, sizes: List<Int>): Boolean {
        val dotsNb = dots.split(".").filter { it.isEmpty() }.map(String::length)
        return dotsNb == sizes.take(dotsNb.size)
    }

}