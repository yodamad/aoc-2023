package day7

import shared.getLines

class DaySeven {
    val DAY = "day7"

    val order = "AKQJT98765432"
    val orderWithJoker = "AKQT98765432J"

    fun play(file:String) {
        val plays = file.getLines(DAY).map { it.split(" ")[0] }
        val bids = file.getLines(DAY).map { it.split(" ")[1] }.map { it.toInt() }
        var splitted: MutableMap<Char, Int>
        val splittedPlays = mutableListOf<MutableMap<Char, Int>>()
        plays.forEach {play ->
            splitted = mutableMapOf()
            play.forEach { c ->
                if (!splitted.keys.contains(c)) {
                    splitted[c] = play.count { it == c }
                }
            }
            splittedPlays.add(splitted)
        }
        println(splittedPlays)
        val orderedPlays = splittedPlays.sortedWith { a, b ->
            when {
                (a.values.max() > b.values.max()) -> 1
                (a.values.max() < b.values.max()) -> -1
                a.values.size > 1 && a.values.sorted().drop(1).first() > b.values.sorted().drop(1).first() -> 1
                a.values.size > 1 && a.values.sorted().drop(1).first() < b.values.sorted().drop(1).first() -> -1
                else -> compareHand(plays[splittedPlays.indexOf(a)], plays[splittedPlays.indexOf(b)])
            }
        }.map { plays[splittedPlays.indexOf(it)] }

        println(orderedPlays)
        val gains = orderedPlays.mapIndexed { idx, play -> bids[plays.indexOf(play)] * (idx+1) }.sum()
        println(gains)
    }
    private fun compareHand(right: String, left: String) : Int {
        right.forEachIndexed {idx, c ->
            when {
                order.indexOf(c) < order.indexOf(left.elementAt(idx)) -> return 1
                order.indexOf(c) > order.indexOf(left.elementAt(idx)) -> return -1
                else -> 0
            }
        }
        return 0
    }

    fun playWithJoker(file:String) {
        val plays = file.getLines(DAY).map { it.split(" ")[0] }
        val bids = file.getLines(DAY).map { it.split(" ")[1] }.map { it.toInt() }

        var splitted: MutableMap<Char, Int>
        val splittedPlays = mutableListOf<MutableMap<Char, Int>>()
        plays.forEach {play ->
            splitted = mutableMapOf()
            play.forEach { c ->
                if (!splitted.keys.contains(c)) {
                    splitted[c] = play.count { it == c }
                }
            }
            splittedPlays.add(splitted)
        }
        // println(splittedPlays)

        val orderedPlays = splittedPlays.sortedWith { a, b ->
            // println("comparing $a to $b")
            when {
                maxWithJoker(a) > maxWithJoker(b) -> 1
                maxWithJoker(a) < maxWithJoker(b) -> -1
                a.filter { it.key != 'J' }.values.size > 1 && b.filter { it.key != 'J' }.values.size > 1 && a.filter { it.key != 'J' }.values.sortedDescending().drop(1).first() > b.filter { it.key != 'J' }.values.sortedDescending().drop(1).first() -> 1
                a.filter { it.key != 'J' }.values.size > 1 && b.filter { it.key != 'J' }.values.size > 1 && a.filter { it.key != 'J' }.values.sortedDescending().drop(1).first() < b.filter { it.key != 'J' }.values.sortedDescending().drop(1).first() -> -1
                else -> compareHandWithJoker(plays[splittedPlays.indexOf(a)], plays[splittedPlays.indexOf(b)])
            }
        }.map { plays[splittedPlays.indexOf(it)] }

        // println("[32T3K, KK677, T55J5, QQQJA, KTJJT]")
        // println(orderedPlays)
        val gains = orderedPlays.mapIndexed { idx, play -> bids[plays.indexOf(play)] * (idx+1) }.sum()
        // println(gains)
    }

    private fun maxWithJoker(a: MutableMap<Char, Int>): Int {
        if (a.keys.size == 1) return a.values.max()
        return a.filter { it.key != 'J' }.values.max() + a.getOrDefault('J', 0)
    }

    private fun compareHandWithJoker(right: String, left: String) : Int {
        right.forEachIndexed {idx, c ->
            when {
                orderWithJoker.indexOf(c) < orderWithJoker.indexOf(left.elementAt(idx)) -> return 1
                orderWithJoker.indexOf(c) > orderWithJoker.indexOf(left.elementAt(idx)) -> return -1
                else -> 0
            }
        }
        return 0
    }
}