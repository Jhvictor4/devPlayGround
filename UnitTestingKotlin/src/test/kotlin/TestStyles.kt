
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

internal class TestStyles {

    class PriceEngine { fun calculate(products: List<Product>): Long = products.size * 1000L }

    /**
     * SUT 반환값만을 검증한다
     */
    @Test
    fun returnValueTest() {
        // given
        val sut = PriceEngine()

        // when
        val result = sut.calculate(listOf(Product.Shampoo))

        // then
        assertEquals(1000L, result)
    }

    class Order {
        private val _products = mutableListOf<Product>()
        val products: List<Product> = _products

        fun addProject(product: Product) {
            _products.add(product)
        }
    }

    /**
     * SUT 멤버 변수의 상태를 검증한다
     */
    @Test
    fun stateTest() {
        // given
        val sut = Order()

        // when
        sut.addProject(Product.Shampoo)

        // then
        assertEquals(1, sut.products.size)
        assertEquals(Product.Shampoo, sut.products.first())
    }

    interface EmailGateWay { fun sendMail() }
    class Controller(val email: EmailGateWay) {
        fun greetUser(param: String) {
            email.sendMail()
        }
    }


    /**
     * Mock을 사용하여 SUT에서 올바른 협력자를 호출했는지 테스트한다.
     */
    @Test
    fun mockTest() {
        // given
        val mockMail = mock<EmailGateWay>()
        val sut = Controller(mockMail)

        // when
        sut.greetUser("asdf")

        // then
        verify(mockMail, times(1)).sendMail()
    }
}
