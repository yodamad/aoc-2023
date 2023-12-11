package day11

import shared.getLines
import kotlin.math.abs

class Day11 {
    val DAY = "day11"
    val coeff = 1_000_000

    fun updateMap(file: String) {
        val start = System.currentTimeMillis()
        val lines = file.getLines(DAY) as MutableList<String>

        // Check lines
        val linesIndexes = mutableListOf<Int>()
        lines.forEachIndexed { index, s ->
            if (s.toCharArray().all { it == '.' }) linesIndexes.add(index)
        }
        //println("emply lines are $linesIndexes")
        /* linesIndexes.forEachIndexed { idx, l ->
            lines.add(l+idx, (0..<lines[l].length).joinToString(separator = "", transform = { c -> "." }))
        } */

        // Check columns
        val colIndexes = mutableListOf<Int>()
        (0..<lines[0].length).forEach {
            if (lines.all { line -> line[it] == '.' }) colIndexes.add(it)
        }
        // println("emply columns are $colIndexes")

        /*lines.forEach {
            println(it)
        }*/

        val nbs = mutableListOf<Pair<Int, Int>>()
        lines.forEachIndexed { idxY, s ->
            s.forEachIndexed { idxX, c ->
                if (c == '#') nbs.add(idxX to idxY)
            }
        }
        //println("planets are $nbs")

        var sum = 0L
        while (nbs.isNotEmpty()) {
            val current = nbs.removeAt(0)

            nbs.forEach {
                var path = abs(current.first - it.first) + abs(current.second - it.second)
                path += ((minOf(current.first, it.first) .. maxOf(current.first, it.first)).intersect(colIndexes).size * (coeff-1))
                path += ((minOf(current.second, it.second) .. maxOf(current.second, it.second)).intersect(linesIndexes).size * (coeff-1))
                //println("path is $path")
                sum += path
            }
        }
        println(System.currentTimeMillis() - start)
        println(sum)
    }
}