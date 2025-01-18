import org.example.Match
import org.example.Team
import org.example.calculateLeagueTable
import org.example.parseMatch
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.io.FileNotFoundException
import kotlin.test.assertEquals

class LeagueRanksTest {

    @Test
    fun `calculateLeagueTable correctly computes rankings`() {
        val input = listOf(
            "Lions 3, Snakes 3",
            "Tarantulas 1, FC Awesome 0",
            "Lions 1, FC Awesome 1",
            "Tarantulas 3, Snakes 1",
            "Lions 4, Grouches 0"
        )

        val result = calculateLeagueTable(input)

        val expected = listOf(
            Team("Tarantulas", 6),
            Team("Lions", 5),
            Team("FC Awesome", 1),
            Team("Snakes", 1),
            Team("Grouches", 0)
        )

        assertEquals(expected, result)
    }

    @Test
    fun `parseMatch parses match results correctly`() {
        val line = "Lions 3, Snakes 3"
        val expected = Match("Lions", 3, "Snakes", 3)
        assertEquals(expected, parseMatch(line))
    }

    @Test
    fun `should calculate rankings from a valid file`() {
        // Arrange: Set up the path to the test resources file
        val filePath = this::class.java.classLoader.getResource("sample_test.txt")?.path
            ?: throw IllegalArgumentException("Test resource not found")

        // Act: Read the file and calculate rankings
        val inputLines = File(filePath).readLines()
        val rankings = calculateLeagueTable(inputLines)

        // Assert: Verify the rankings

        val expected = listOf(
            Team("Tarantulas", 6),
            Team("Lions", 5),
            Team("FC Awesome", 1),
            Team("Snakes", 1),
            Team("Grouches", 0)
        )
        assertEquals(expected, rankings)
    }

    @Test
    fun `should throw exception for a missing file`() {
        // Arrange: Path to a non-existent file
        val missingFilePath = "nonexistent_file.txt"

        // Act and Assert: Ensure the correct exception is thrown
        val exception = assertThrows<FileNotFoundException> {
            File(missingFilePath).readLines()
        }

        assertTrue(exception.message?.contains(missingFilePath) == true)
    }

    @Test
    fun `should handle file with invalid format gracefully`() {
        // Arrange: Set up the path to the invalid matches file
        val filePath = this::class.java.classLoader.getResource("bad_sample.txt")?.path
            ?: throw IllegalArgumentException("Test resource not found")

        // Act: Read the file
        val inputLines = File(filePath).readLines()

        // Assert: Ensure invalid format throws an exception during parsing
        val exception = assertThrows<IllegalArgumentException> {
            inputLines.forEach { parseMatch(it) }
        }

        assertTrue(exception.message?.contains("Invalid input format") == true)
    }
}