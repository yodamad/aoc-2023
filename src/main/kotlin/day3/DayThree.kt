package day3

import shared.getLines

class DayThree {
    val DAY = "day3"

    // Part 1
    val symbolsPositions = mutableListOf<Pair<Int, Int>>()
    fun analyzeEngine(file: String): Int {
        val lines = file.getLines(DAY)

        findAllSymbols(file)

        var sum = 0
        lines.forEachIndexed { rIndex, line ->
            var currentNb = ""
            line.toList().forEachIndexed { cIndex, c ->
                if (!c.isDigit()) {
                    if (currentNb != "") {
                        if (findSymbol(cIndex, rIndex, currentNb.length)) {
                            // println("Adding $currentNb")
                            sum += currentNb.toInt()
                        } else {
                            //println("Skipping $currentNb")
                        }
                    }
                    currentNb = ""
                } else currentNb += c
            }
            if (currentNb != "") {
                println("Checking EOL $currentNb")
                if (findSymbol(line.length,rIndex,currentNb.length)) {
                     println("Adding $currentNb")
                     sum += currentNb.toInt()
                 }
            }
        }
        return sum
    }

    private fun findAllSymbols(file: String) {
        file.getLines(DAY).forEachIndexed { lIndex, line ->
            symbolsPositions.addAll(line
                .asSequence()
                .mapIndexed { cIndex, s -> s to cIndex }
                .filter { it.first != '.' && !it.first.isDigit() }
                .map { it.second to lIndex }
                .toList())
        }
        // println(symbolsPositions)
    }

    private fun findSymbol(x: Int, y: Int, l: Int): Boolean {
        val availablePositions = mutableListOf(
            x-l-1 to y,
            x-l-1 to y-1,
            x-l-1 to y+1,
            x to y,
            x to y-1,
            x to y+1
        )
        (0 .. l).forEach {
            availablePositions.add(x-it to y-1)
            availablePositions.add(x-it to y+1)
        }
        return availablePositions.stream().map { symbolsPositions.contains(it) }.anyMatch { it }
    }

    // Part 2

    fun findStar(file: String) {
        var sum = 0
        file.getLines(DAY).forEachIndexed { lIndex, line ->
            sum += line.asSequence()
                .mapIndexed { cIndex, s -> s to cIndex }
                .filter { it.first == '*' }
                .map { findGear(file, it.second, lIndex) }
                .sum()
        }
        println("Total is $sum")
    }

    private fun findGear(file: String, x: Int, y: Int): Int {
        var currentNb = ""
        val nbs = mutableListOf<Int>()
        val lines = file.getLines(DAY)
        // xx9.
        if ((x-1) >= 0 && lines[y][x-1].isDigit()) {
            var cursor = 1
            currentNb += lines[y][x-1]
            while ((x-1-cursor) >= 0 && lines[y][x-1-cursor].isDigit()) {
                currentNb = lines[y][x-1-cursor] + currentNb
                cursor++
            }
            println("Add $currentNb")
            nbs.add(currentNb.toInt())
        }
        currentNb = ""
        // .9xx
        if ((x+1) <= lines[y].length && lines[y][x+1].isDigit()) {
            var cursor = 1
            currentNb += lines[y][x+1]
            while ((x+1+cursor) < lines[y].length && lines[y][x+1+cursor].isDigit()) {
                currentNb += lines[y][x+1+cursor]
                cursor++
            }
            println("Add $currentNb")
            nbs.add(currentNb.toInt())
        }
        currentNb = ""
        // 9
        // .
        if ((y-1) >= 0 && lines[y-1][x].isDigit()) {
            var cursor = 1
            currentNb += lines[y-1][x]
            while ((x-cursor) >= 0 && lines[y-1][x-cursor].isDigit()) {
                currentNb = lines[y-1][x-cursor] + currentNb
                cursor++
            }
            cursor = 1
            while ((x+cursor) <= lines[y-1].length && lines[y-1][x+cursor].isDigit()) {
                currentNb += lines[y-1][x+cursor]
                cursor++
            }
            println("Add $currentNb")
            nbs.add(currentNb.toInt())
        } else {
            // 9.
            // ?.
            if ((y-1) >= 0 && (x-1) >= 0 && lines[y-1][x-1].isDigit()) {
                var cursor = 1
                currentNb += lines[y-1][x-1]
                while ((x-1-cursor) >= 0 && lines[y-1][x-1-cursor].isDigit()) {
                    println("current nb is $currentNb")
                    currentNb = lines[y-1][x-1-cursor] + currentNb
                    cursor++
                }
                println("Add $currentNb")
                nbs.add(currentNb.toInt())
                currentNb = ""
            }
            // .9
            // .?
            if ((y-1) >= 0 && (x+1) <= lines[y-1].length && lines[y-1][x+1].isDigit()) {
                var cursor = 1
                currentNb += lines[y-1][x+1]
                while ((x+1+cursor) < lines[y-1].length && lines[y-1][x+1+cursor].isDigit()) {
                    currentNb += lines[y-1][x+1+cursor]
                    cursor++
                }
                println("Add $currentNb")
                nbs.add(currentNb.toInt())
            }
        }
        currentNb = ""
        // .
        // 9
        if ((y+1) <= lines.size && lines[y+1][x].isDigit()) {
            var cursor = 1
            currentNb += lines[y+1][x]
            while ((x-1-cursor) >= 0 && lines[y+1][x-cursor].isDigit()) {
                currentNb = lines[y+1][x-cursor] + currentNb
                cursor++
            }
            cursor = 1
            while ((x+1+cursor) <= lines[y+1].length && lines[y+1][x+cursor].isDigit()) {
                currentNb += lines[y+1][x+cursor]
                cursor++
            }
            println("Add $currentNb")
            nbs.add(currentNb.toInt())
        } else {
            // ?.
            // 9.
            if ((y+1) <= lines.size && (x-1) >=0 && lines[y+1][x-1].isDigit()) {
                var cursor = 1
                currentNb += lines[y+1][x-1]
                while ((x-1-cursor) >= 0 && lines[y+1][x-1-cursor].isDigit()) {
                    currentNb = lines[y+1][x-1-cursor] + currentNb
                    cursor++
                }
                println("Add $currentNb")
                nbs.add(currentNb.toInt())
                currentNb = ""
            }
            // .?
            // .9
            if ((y+1) <= lines.size && (x+1) <= lines[y+1].length && lines[y+1][x+1].isDigit()) {
                var cursor = 1
                currentNb += lines[y+1][x+1]
                while ((x+1+cursor) < lines[y+1].length && lines[y+1][x+1+cursor].isDigit()) {
                    currentNb += lines[y+1][x+1+cursor]
                    cursor++
                }
                println("Add $currentNb")
                nbs.add(currentNb.toInt())
            }
        }
        return if (nbs.size >= 2) {
            println("Multiplying $nbs")
            nbs.reduce { acc, e -> acc * e }
        } else 0
    }
}