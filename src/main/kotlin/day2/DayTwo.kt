package day2

import shared.getLines
import kotlin.streams.asStream

class DayTwo {
    val DAY = "day2"

    val maxColors = arrayOf (
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    fun computeGame(file: String): Int {
        var sum = 0
        file.getLines(DAY).forEachIndexed { index, line ->
            val games = line.split(":")[1].split(";")
            val isGoodGame = games.map { game ->
                game.split(",").map { color ->
                    color.trim().split(" ")[1] to color.trim().split(" ")[0].toInt()
                }.map { r ->
                    maxColors.find { it.first == r.first }?.second!! >= r.second
                }.all { it }
            }.all { it }
            if (isGoodGame) {
                sum += index+1
            }
        }
        return sum
    }

    fun computeOptimizedGame(file: String): Int {
        var sum = 0
        file.getLines(DAY).forEachIndexed { index, line ->
            println("New line")
            val toto = line.split(":")[1]
                .split(";")
                .map { game ->
                    val mapValues = game.split(",")
                        .map { color -> color.trim().split(" ")[1] to color.trim().split(" ")[0].toInt() }
                        .fold(mutableMapOf<String, Int>()) { acc, p ->
                            acc[p.first] = p.second
                            acc
                        }
                    mapValues
                }.fold(mutableMapOf<String, Int>()) { acc, pp ->
                    pp.forEach { e ->
                        if (acc[e.key] == null) acc[e.key] = e.value
                        else acc[e.key] = acc[e.key]?.let { maxOf(it, e.value) }!!
                    }
                    acc
                }
            sum += toto.values.reduce { acc, i -> acc * i }
        }
        return sum
    }
}