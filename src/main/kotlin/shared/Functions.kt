package shared

import java.io.File

fun String.getLines(day: String) = File("./src/test/resources/$day/$this").readLines()

fun String.isNumeric(): Boolean = this.all { char -> char.isDigit() }