package org.example

import java.io.File


data class Team(val name: String, var points: Int = 0)

data class Match(val team1: String, val score1: Int, val team2: String, val score2: Int)


fun main(args: Array<String>) {
    val input = if (args.isNotEmpty()) File(args[0]).readLines() else readInput()
    val teams = calculateLeagueTable(input)
    printRankingTable(teams)
}

private fun readInput(): List<String> {
    println("Please enter results for the Animals League (end input with an empty line):")
    return generateSequence(::readLine).takeWhile { it.isNotEmpty() }.toList()
}

fun calculateLeagueTable(input: List<String>): List<Team> {
    val teamMap = mutableMapOf<String, Team>()

    input.forEach { line ->
        val (team1, score1, team2, score2) = parseMatch(line)
        val t1 = teamMap.getOrPut(team1) { Team(team1) }
        val t2 = teamMap.getOrPut(team2) { Team(team2) }

        when {
            score1 > score2 -> t1.points += 3
            score1 < score2 -> t2.points += 3
            else -> {
                t1.points += 1
                t2.points += 1
            }
        }
    }

    return teamMap.values.sortedWith(compareByDescending<Team> { it.points }.thenBy { it.name })
}

fun parseMatch(line: String): Match {
    val matchRegex = Regex("(.+) (\\d+), (.+) (\\d+)")
    val match = matchRegex.matchEntire(line.trim())
        ?: throw IllegalArgumentException("Invalid input format: $line")

    val (team1, score1, team2, score2) = match.destructured
    return Match(team1, score1.toInt(), team2, score2.toInt())
}


private fun printRankingTable(teams: List<Team>) {
    var rank = 0
    var previousPoints = -1
    teams.forEachIndexed { index, team ->
        if (team.points != previousPoints) {
            rank = index + 1
            previousPoints = team.points
        }
        val pointLabel = if (team.points == 1) "pt" else "pts"
        println("$rank. ${team.name}, ${team.points} $pointLabel")
    }
}
