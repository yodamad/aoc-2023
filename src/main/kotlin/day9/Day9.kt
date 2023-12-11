package day9

import shared.getLines

class Day9 {

    val DAY = "day9"

    fun findMissing(file: String) =
        file.getLines(DAY).map { line -> line.split(" ").map { it.toInt() } }.sumOf { line ->
            var nbs = line
            var sum = 0
            val update = mutableListOf(9999)
            val last = mutableListOf<Int>()
            while (update.any { it != 0 }) {
                update.clear()
                nbs.forEachIndexed { index, nb ->
                    if (index != nbs.size - 1) {
                        update.add(nbs[index + 1] - nb)
                    } else {
                        last.add(nb)
                    }
                }
                nbs = update.toList()
            }

            // println(last)
            var current = 0

            last.reversed().forEachIndexed { index, lastNb ->
                current += lastNb
                // println("current = $current")
                if (index == last.size - 1) {
                    sum += current
                }
            }
            sum
        }

    fun findStartingMissing(file: String) =
        file.getLines(DAY).map { line -> line.split(" ").map { it.toInt() } }.sumOf { line ->
            var nbs = line
            var sum = 0
            val update = mutableListOf(9999)
            val first = mutableListOf<Int>()
            while (update.any { it != 0 }) {
                update.clear()
                nbs.forEachIndexed { index, nb ->

                    if (index == 0) first.add(nb)

                    if (index != nbs.size - 1) {
                        update.add(nbs[index + 1] - nb)
                    } else {

                    }
                }
                nbs = update.toList()
            }

            println(first)
            var current = 0

            first.reversed().forEachIndexed { index, lastNb ->
                current = lastNb - current
                println("current = $current")
                if (index == first.size - 1) {
                    sum += current
                }
            }
            sum
        }
}