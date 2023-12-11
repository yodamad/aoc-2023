package day10

import shared.getLines

class Day10 {
    val DAY = "day10"
    val visited = mutableListOf<Pair<Int, Int>>()
    lateinit var lines : List<String>

    val SOUTH = "south"
    val NORTH = "north"
    val EAST  = "east"
    val WEST  = "west"
    val STOP  = "stop"

    fun explore(file: String) {
        lines = file.getLines(DAY)
        var startPos = -1 to -1
        run start@ {
            lines.forEachIndexed { index, s ->
                val x = s.indexOf('S')
                if (x != -1) {
                    startPos = x to index
                    return@start
                }
            }
        }
        println("Starting at $startPos")

        var inLoop = true
        val currentPos = startPos
        var path = 0
        var next: Pair<Pair<Int, Int>, String> = (-1 to -1) to ""

        if (currentPos == startPos) next = findNext(currentPos)
        while (inLoop) {

            println("${lines[next.first.second][next.first.first]} in (${next.first.first},${next.first.second}) to ${next.second}")

            when (next.second) {
                SOUTH -> next = (next.first.first to next.first.second + 1) to findNextDirection(next)
                NORTH -> next = (next.first.first to next.first.second - 1) to findNextDirection(next)
                WEST -> next  = (next.first.first - 1 to next.first.second) to findNextDirection(next)
                EAST -> next  = (next.first.first + 1 to next.first.second) to findNextDirection(next)
            }

            path++
            if (next.second == STOP) {
                path++
                inLoop = false
            }
            else {
                visited.add(next.first.first to next.first.second)
            }
        }
        println(path/2)
    }

    /*
    | is a vertical pipe connecting north and south.
    - is a horizontal pipe connecting east and west.
    L is a 90-degree bend connecting north and east.
    J is a 90-degree bend connecting north and west.
    7 is a 90-degree bend connecting south and west.
    F is a 90-degree bend connecting south and east.
     */

    private fun findNext(pos: Pair<Int, Int>): Pair<Pair<Int, Int>, String> {
        return when {
            pos.first > 0 && lines[pos.second][pos.first-1] == '-' -> (pos.first-1 to pos.second) to WEST
            pos.first > 0 && lines[pos.second][pos.first-1] == 'L' -> (pos.first-1 to pos.second) to NORTH
            pos.first > 0 && lines[pos.second][pos.first-1] == 'F' -> (pos.first-1 to pos.second) to SOUTH
            pos.first+1 < lines[0].length && lines[pos.second][pos.first+1] == '-' -> (pos.first+1 to pos.second) to EAST
            pos.first+1 < lines[0].length && lines[pos.second][pos.first+1] == '7' -> (pos.first+1 to pos.second) to SOUTH
            pos.first+1 < lines[0].length && lines[pos.second][pos.first+1] == 'J' -> (pos.first+1 to pos.second) to NORTH

            pos.second > 0 && lines[pos.second-1][pos.first] == '|' -> (pos.first to pos.second-1) to NORTH
            pos.second > 0 && lines[pos.second-1][pos.first] == '7' -> (pos.first to pos.second-1) to WEST
            pos.second > 0 && lines[pos.second-1][pos.first] == 'F' -> (pos.first to pos.second-1) to EAST
            pos.second+1 < lines.size && lines[pos.second+1][pos.first] == '|' -> (pos.first to pos.second+1) to SOUTH
            pos.second+1 < lines.size && lines[pos.second+1][pos.first] == 'L' -> (pos.first to pos.second+1) to EAST
            pos.second+1 < lines.size && lines[pos.second+1][pos.first] == 'J' -> (pos.first to pos.second+1) to WEST
            else -> throw Error()
        }
    }

    private fun findNextDirection(pos: Pair<Pair<Int, Int>, String>): String {
        return when (pos.second) {
            WEST -> {
                val newPos = lines[pos.first.second][pos.first.first-1]
                when (newPos) {
                    '-' -> WEST
                    'L' -> NORTH
                    'F' -> SOUTH
                    'S' -> STOP
                    else -> throw Error()
                }
            }

            EAST -> {
                val newPos = lines[pos.first.second][pos.first.first+1]
                when (newPos) {
                    '-' -> EAST
                    'J' -> NORTH
                    '7' -> SOUTH
                    'S' -> STOP
                    else -> throw Error()
                }
            }

            SOUTH -> {
                val newPos = lines[pos.first.second+1][pos.first.first]
                when (newPos) {
                    '|' -> SOUTH
                    'J' -> WEST
                    'L' -> EAST
                    'S' -> STOP
                    else -> throw Error()
                }
            }

            NORTH -> {
                val newPos = lines[pos.first.second-1][pos.first.first]
                when (newPos) {
                    '|' -> NORTH
                    'F' -> EAST
                    '7' -> WEST
                    'S' -> STOP
                    else -> throw Error()
                }
            }
            else -> throw Error()
        }
    }

    private fun alreadyVisit(pos: Pair<Int, Int>) = visited.any { it == pos }

    private fun isCloseTo(pos: Pair<Int, Int>): Pair<Int, Int>? =
        if (pos.first > 0 && lines[pos.second][pos.first-1] == 'S') pos.first-1 to pos.second
        else if (pos.first+1 < lines[0].length && lines[pos.second][pos.first+1] == 'S') pos.first+1 to pos.second
        else if (pos.second > 0 && lines[pos.second-1][pos.first] == 'S') pos.first to pos.second-1
        else if (pos.second+1 < lines.size && lines[pos.second+1][pos.first] == 'S') pos.first to pos.second+1
        else null
}