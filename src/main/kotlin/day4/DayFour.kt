package day4

import shared.getLines
import kotlin.math.pow

class DayFour {
    val DAY = "day4"

    fun winingCard(file: String): Int {
        var sum = 0
        file.getLines(DAY).forEach {line ->
            val cards = line.split(":")[1].split("|")
            val winningNbs = cards[0].trim().split(" ").filter { it != "" }.map { it.toInt() }.toList()
            val myNbs = cards[1].trim().split(" ").filter { it != "" }.map { it.toInt() }.toList()
            val count = myNbs.count { winningNbs.contains(it) }
            sum += 2.toDouble().pow((count-1).toDouble()).toInt()
        }
        return sum
    }

    fun optimizedCards(file: String) : Int {
        val myCards = mutableMapOf<Int, Int>()

        file.getLines(DAY).forEachIndexed { index, line ->
            val cards = line.split(":")[1].split("|")
            val winningNbs = cards[0].trim().split(" ").filter { it != "" }.map { it.toInt() }.toList()
            val myNbs = cards[1].trim().split(" ").filter { it != "" }.map { it.toInt() }.toList()
            if (myCards[index] == null)
                myCards[index] = 1
            else
                myCards[index] = myCards[index]!! + 1

            val count = myNbs.count { winningNbs.contains(it) }
            (1 .. count).forEach {
                if (myCards[index+it] == null)
                    myCards[index+it] = myCards[index]!!
                else
                    myCards[index+it] = myCards[index+it]!! +  myCards[index]!!
            }
            println("index $index : $myCards")
        }

        return myCards.values.stream().reduce { acc, s -> acc + s }.get()
    }
}