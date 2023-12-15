package day15

import shared.getLines

class Day15 {
    val DAY = "day15"

    fun hash(file: String) {
        var sum = 0
        file.getLines(DAY).map { l ->
            l.split(",").forEach { s ->
                var current = 0
                // println(s)
                s.forEach { c ->
                    current = ((current + c.code) * 17).rem(256)
                }
                // println(current)
                sum += current
            }
            println(sum)
        }
    }
}