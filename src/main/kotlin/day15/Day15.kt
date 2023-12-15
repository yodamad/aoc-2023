package day15

import shared.getLines

class Day15 {
    val DAY = "day15"

    fun hash(file: String) {
        file.getLines(DAY).map { l ->
            val sum = l.split(",").sumOf { hashKey(it) }
            println(sum)
        }
    }

    private fun hashKey(s: String): Int {
        var current = 0
        s.forEach { c ->
            current = ((current + c.code) * 17).rem(256)
        }
        return current
    }

    fun lenses(file: String) {
        val lenses = mutableMapOf<Int, MutableList<Pair<String, Int>>>()
        file.getLines(DAY).first().split(",").forEach { lens ->
            if (lens.endsWith("-")) {
                val key = lens.removeSuffix("-")
                val hashKey = hashKey(key)
                val list = lenses.getOrDefault(hashKey, emptyList())
                if (list.isNotEmpty()) (list as MutableList).removeIf { it.first == key }
            } else {
                val key = lens.split("=")[0]
                val hashKey = hashKey(key)
                val focal = lens.split("=")[1].toInt()

                if (!lenses.keys.contains(hashKey)) lenses[hashKey] = mutableListOf(key to focal)
                else if (lenses[hashKey]!!.any { it.first == key}) {
                    val slot = lenses[hashKey]!!
                    slot[slot.indexOfFirst { it.first == key }] = key to focal
                } else {
                    lenses[hashKey]!!.add(key to focal)
                }
            }
        }
        println(lenses)
        var sum = 0
        lenses.forEach { entry ->
            entry.value.forEachIndexed { index, pair ->
                sum += (entry.key+1) * (index+1) * pair.second
            }
        }
        println(sum)
    }
}