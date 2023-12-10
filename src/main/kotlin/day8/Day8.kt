package day8

import shared.getLines

class Day8 {
    val DAY = "day8"
    lateinit var way : String
    lateinit var map : List<Pair<String, Pair<String, String>>>
    var steps = 0


    fun navigate(file: String) {
        way = file.getLines(DAY).first()

        map = file.getLines(DAY).drop(2).map { l ->
            val node = l.split("=")[0].trimEnd()

            val regex = """\((\w{3}), (\w{3})\)""".toRegex(RegexOption.IGNORE_CASE)
            val matchResult = regex.find(l.split("=")[1].trim())!!
            val (left, right) = matchResult.destructured

            node to (left to right)
        }.toList()

        var currentNode = map.first { it.first == "AAA" }
        while (currentNode.first != "ZZZ") {
            currentNode = findWay(currentNode)
        }

        println(steps)
    }

    private fun findWay(currentNode: Pair<String, Pair<String, String>>): Pair<String, Pair<String, String>> {
        var node = currentNode
        way.forEach { c ->
            println("$node to $c")
            when {
                c == 'L' -> node = map.first { it.first == node.second.first }
                c == 'R' -> node = map.first { it.first == node.second.second }
                else -> println("You're lost")
            }
            println("new node is $node")
            steps++
            if (node.first.endsWith('Z')) return node
        }
        return node
    }

    fun navigateAsGhost(file: String) {
        way = file.getLines(DAY).first()

        map = file.getLines(DAY).drop(2).map { l ->
            val node = l.split("=")[0].trimEnd()

            val regex = """\((\w{3}), (\w{3})\)""".toRegex(RegexOption.IGNORE_CASE)
            val matchResult = regex.find(l.split("=")[1].trim())!!
            val (left, right) = matchResult.destructured

            node to (left to right)
        }.toList()

        val startingNodes = map.filter { it.first.endsWith('A') }

        val paths = mutableListOf<Int>()

        startingNodes.forEach { currentNode ->
            var node = currentNode
            while (!node.first.endsWith("Z")) {
                node = findWay(node)
            }
            paths.add(steps)
            steps = 0
        }

        println(findLCMOfListOfNumbers(paths))
    }

    fun findLCM(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm % a == 0L && lcm % b == 0L) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }
    private fun findLCMOfListOfNumbers(numbers: List<Int>): Long {
        var result = numbers[0].toLong()
        for (i in 1 until numbers.size) {
            result = findLCM(result, numbers[i].toLong())
        }
        return result
    }
}