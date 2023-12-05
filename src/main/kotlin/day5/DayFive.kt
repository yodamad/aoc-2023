package day5

import shared.getLines

class DayFive {
    val DAY = "day5"
    val seedsMaps = mutableListOf<SeedsMap>()
    var seedsIndexes = listOf<Long>()
    val seedsLocations = mutableListOf<Long>()

    fun parseData(file: String): Long {
        file.getLines(DAY).forEachIndexed { index, line ->

            if (index == 0) {
                // seeds line
                seedsIndexes = line.split(":")[1].trim().split(" ").map { it.toLong() }
            } else {
                if (line.isEmpty()) {
                    // empty line
                } else if (line[0].isDigit()) {
                    // Ranges line
                    val elements = line.split(" ")
                    val range = SeedsRange(elements[0].toLong(), elements[1].toLong(), elements[2].toLong())
                    seedsMaps.last().ranges.add(range)
                } else {
                    // name
                    seedsMaps.add(SeedsMap(line.split(":")[0], mutableListOf()))
                }
            }
        }

        println("Seeds : $seedsIndexes")
        println("Infos : $seedsMaps")

        var current: Long

        seedsIndexes.stream().forEach { seed ->
            current = seed
            var found = false
            seedsMaps.forEach maps@ { m ->
                run ranges@ {
                    m.ranges.forEach { r ->
                        if (!found && current in r.source..< r.source + r.range) {
                            current += (r.destination - r.source)
                            found = true
                        }
                    }
                    found = false
                }
            }
            seedsLocations.add(current)
        }
        return seedsLocations.min()
    }

    fun parseMassiveData(file: String): Long {

        val seedsRanges = mutableListOf<Pair<Long, Long>>()

        file.getLines(DAY).forEachIndexed { index, line ->
            if (index == 0) {
                // seeds line
                val array = line.split(":")[1].trim().split(" ")
                array.forEachIndexed { index2, s ->
                    if (index2 %2 == 0) {
                        seedsRanges.add(array[index2].toLong() to array[index2+1].toLong())
                    }
                }
                println(seedsRanges)
            } else {
                if (line.isEmpty()) {
                    // empty line
                } else if (line[0].isDigit()) {
                    // Ranges line
                    val elements = line.split(" ")
                    val range = SeedsRange(elements[0].toLong(), elements[1].toLong(), elements[2].toLong())
                    seedsMaps.last().ranges.add(range)
                } else {
                    // name
                    seedsMaps.add(SeedsMap(line.split(":")[0], mutableListOf()))
                }
            }
        }

        println("Seeds : $seedsIndexes")
        println("Infos : $seedsMaps")

        var current: Long
        var seedsLocation = Long.MAX_VALUE

        seedsRanges.forEach { sr ->
            (sr.first ..< sr.first + sr.second).forEach { seed ->
                current = seed
                seedsMaps.forEach maps@ { m ->
                    run ranges@ {
                        m.ranges.forEach { r ->
                            if (current in r.source..< r.source + r.range) {
                                current += (r.destination - r.source)
                                return@ranges
                            }
                        }
                    }
                }
                if (seedsLocation > current) seedsLocation = current
            }
        }
        return seedsLocation
    }
}

data class SeedsMap(val name: String, val ranges: MutableList<SeedsRange>)

data class SeedsRange(val destination: Long, val source: Long, val range: Long)