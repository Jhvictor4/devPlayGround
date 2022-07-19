
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MainTest {
    private val mainClass = Main()

    @Test
    fun testA() {
        assertTrue(mainClass.isStringLong("132321"))
        assertFalse(mainClass.isStringLong("13231"))
    }
}