import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*

interface IDB {
    fun getNumberOfUsers(): Int
}

class Controller(
    private val db: IDB
) {
    fun createReport(): Report {
        return Report(db.getNumberOfUsers())
    }
}

data class Report(val id: Int = 0)

@ExtendWith(MockitoExtension::class)
internal class StubTest {
    @Test
    fun `Do not verify Stub method call`() {
        // given
        val stub = mock<IDB>()
        whenever(stub.getNumberOfUsers()).thenReturn(10)

        // when
        val sut = Controller(stub)
        val result = sut.createReport()

        // then
        assertEquals(Report(10), result)
        verify(stub, times(1)).getNumberOfUsers() // BAD LINE!!
    }
}