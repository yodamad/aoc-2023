package day14

import shared.getLines
import shared.replaceCharAt

class Day14 {
    val DAY = "day14"
    val CYCLE = 1_000_000_000 // 12_558_577
    lateinit var lines: MutableList<String>
    val grids = mutableListOf<List<String>>()
    fun organize(file: String) {
        lines = file.getLines(DAY) as MutableList<String>
        var cycleIndex = -1
        run findGrid@ {
            (1..CYCLE).forEach { global ->
                var turn = 1
                grids.add(lines.toList())
                (1..4).forEach {
                    if (it != 3) {
                        lines.forEachIndexed { lIndex, l ->
                            if ((it == 2 || it == 4) && !l.contains('O')) {
                            } else {
                                var tmpL = l
                                if (turn == 4) tmpL = l.reversed()
                                tmpL.forEachIndexed { cIndex, c ->
                                    if (c == 'O') {
                                        when (turn) {
                                            1 -> moveNorth(lIndex, cIndex)
                                            2 -> moveWest(lIndex, cIndex)
                                            4 -> tmpL = moveEast(lIndex, cIndex, tmpL)
                                        }
                                    }
                                }

                                if (turn == 4) lines[lIndex] = tmpL.reversed()
                            }
                        }
                    } else {
                        lines = lines.reversed().toMutableList()
                        lines.forEachIndexed { lIndex, l ->
                            l.forEachIndexed { cIndex, c ->
                                if (c == 'O') {
                                    when (turn) {
                                        3 -> moveNorth(lIndex, cIndex)
                                    }
                                }
                            }
                        }
                        lines = lines.reversed().toMutableList()
                    }
                    turn++
                }
                if (grids.contains(lines)) {
                    // Found a cycle
                    cycleIndex = grids.indexOf(lines)
                    println("${grids.size} grids, repeat from $cycleIndex, existing index is ${grids.indexOf(lines)}")
                    return@findGrid
                }
                turn = 1
                // println(grids)
                var localSum = 0
                lines.forEachIndexed { index, l ->
                    localSum += ((lines.size - index) * l.count { it == 'O' })
                }
                println("----------- cycle $global is $localSum")
            }
        }

        val cycleSize = grids.size - cycleIndex
        val goodIndex = cycleIndex + (CYCLE-cycleIndex).rem(cycleSize)

        println("$cycleIndex + $CYCLE % $cycleSize")
        println("$cycleIndex + ${CYCLE.rem(cycleSize)}")

        var sum = 0

        grids[goodIndex].forEachIndexed { index, l ->
            //println(l)
            sum += ((lines.size - index) * l.count { it == 'O' })
        }
        println(sum)
    }

    private fun moveNorth(lIndex: Int, cIndex: Int) {
        // println("Moving North")
        run found@ {
            (lIndex-1).downTo(0).forEach {
                if (listOf( '#', 'O').contains(lines[it][cIndex])) {
                    lines[lIndex] = lines[lIndex].replaceCharAt(cIndex, '.')
                    lines[it+1] = lines[it+1].replaceCharAt(cIndex, 'O')
                    return@found
                } else if (it == 0) {
                    lines[lIndex] = lines[lIndex].replaceCharAt(cIndex, '.')
                    lines[0] = lines[0].replaceCharAt(cIndex, 'O')
                    return@found
                }
            }
        }
    }

    private fun moveWest(lIndex: Int, cIndex: Int) {
        run found@ {
            (cIndex-1).downTo(0).forEach {
                if (listOf( '#', 'O').contains(lines[lIndex][it])) {
                    lines[lIndex] = lines[lIndex].replaceCharAt(cIndex, '.')
                    lines[lIndex] = lines[lIndex].replaceCharAt(it+1, 'O')
                    return@found
                } else if (it == 0 && lines[lIndex][it] == '.') {
                    lines[lIndex] = lines[lIndex].replaceCharAt(cIndex, '.')
                    lines[lIndex] = lines[lIndex].replaceCharAt(0, 'O')
                    return@found
                }
            }
        }
    }

    private fun moveEast(lIndex: Int, cIndex: Int, line : String): String {
        var newLine = line
        run found@ {
            (cIndex-1).downTo(0).forEach {
                if (listOf( '#', 'O').contains(line[it])) {
                    newLine = newLine.replaceCharAt(cIndex, '.')
                    newLine = newLine.replaceCharAt(it+1, 'O')
                    return@found
                } else if (it == 0 && line[it] == '.') {
                    newLine = newLine.replaceCharAt(cIndex, '.')
                    newLine = newLine.replaceCharAt(0, 'O')
                    return@found
                }
            }
        }
        return newLine
    }
}
