import shared.getLines

class Day13 {
    val DAY = "day13"

    fun symmetry(file:String): Int {
        val lines = file.getLines(DAY)

        //val patterns = lines.windowed(lines.indexOfFirst { it.isEmpty() }, lines.indexOfFirst { it.isEmpty() } + 1)

        val patterns = mutableListOf<List<String>>()
        var current = mutableListOf<String>()
        lines.forEach { s ->
            if (s.isNotEmpty()) current.add(s)
            else {
                patterns.add(current.toList())
                current = mutableListOf()
            }
        }
        // Don't forget last !
        patterns.add(current.toList())

        println(patterns)

        return patterns.sumOf { pattern ->
            100 * (findRowPattern(pattern) + 1) + (findColPattern(pattern) + 1)
        }
    }

    private fun findColPattern(pattern: List<String>) =
        List(pattern.first().length - 1) { index ->
            var found = 0
            pattern.forEach { s ->
                if (s.isNotEmpty() && found != -1 && index < pattern.first().length && s[index] == s[index+1]) found = index
                else found = -1
            }
            found
        }.filter { it >= 0 }
            .firstOrNull { c ->
                var isValid = true
                var idx = 0
                while (isValid && c-idx >= 0 && c+idx+1 < pattern.first().length) {
                    pattern.forEach { s ->
                        isValid = isValid && s[c - idx] == s[c + idx + 1]
                    }
                    idx++
                }
                isValid
            } ?: -1

    private fun findRowPattern(pattern: List<String>) =
        List(pattern.size) { index ->
            if (index < pattern.size - 1 && pattern[index] == pattern[index + 1]) index
            else -1
        }.filter { it != -1 }
            .filter { r ->
                var isValid = true
                pattern.forEachIndexed { idx, s ->
                    if (r - idx > -1 && r + idx + 1 < pattern.size) {
                        isValid = isValid && (pattern[r - idx] == pattern[r + idx + 1])
                    }
                }
                isValid
            }.maxOrNull() ?: -1

    fun symmetryWithSmudges(file: String) {
        val lines = file.getLines(DAY)

        //val patterns = lines.windowed(lines.indexOfFirst { it.isEmpty() }, lines.indexOfFirst { it.isEmpty() } + 1)

        val patterns = mutableListOf<List<String>>()
        var current = mutableListOf<String>()
        lines.forEach { s ->
            if (s.isNotEmpty()) current.add(s)
            else {
                patterns.add(current.toList())
                current = mutableListOf()
            }
        }
        // Don't forget last !
        patterns.add(current.toList())

        println(patterns)
        patterns.forEach { println(findColPatternwithSmudge(it)) }
        patterns.forEach { println(findRowPatternWithSmudges(it)) }
    }

    private fun findColPatternwithSmudge(pattern: List<String>) =
        List(pattern.first().length - 1) { index ->
            var mismatch = 0
            pattern.forEach { s ->
                if (s.isNotEmpty() && mismatch >= -1 && index < pattern.first().length && s[index] == s[index+1])
                else mismatch -= 1
            }
            if (mismatch == -1) index else -1
        }

    /*.filter { it >= 0 }
    .firstOrNull { c ->
        var isValid = true
        var idx = 0
        while (isValid && c-idx >= 0 && c+idx+1 < pattern.first().length) {
            pattern.forEach { s ->
                isValid = isValid && s[c - idx] == s[c + idx + 1]
            }
            idx++
        }
        isValid
    } ?: -1*/

    private fun findRowPatternWithSmudges(pattern: List<String>): MutableList<Int> {
        val indexes = mutableListOf<Int>()
        pattern.forEachIndexed { index, s ->
            var mismatch = 0
            List(pattern.first().length - 1) { c ->
                if (mismatch >= -1 && index < pattern.first().length && pattern[index][c] == pattern[index + 1][c])
                else mismatch -= 1
            }
            if (mismatch == -1) indexes.add(index)
        }
        return indexes
    }
}