import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class)
internal class MarketTest {
    @Test
    fun `Purchase Succeeds when enough Inventory`() {
        // given
        val store = Store()
        val customer = Customer()
        store.addInventory(Product.Shampoo, 10)

        // when
        val result = customer.purchase(store, Product.Shampoo, 5)

        // then
        assertTrue(result)
        assertEquals(5, store.getCount(Product.Shampoo))
    }

    @Test
    fun `Purchase Fails when not enough Inventory`() {
        // given
        val store = Store()
        val customer = Customer()
        store.addInventory(Product.Shampoo, 10)

        // when
        val result = customer.purchase(store, Product.Shampoo, 15)

        // then
        assertFalse(result)
        assertEquals(10, store.getCount(Product.Shampoo))
    }

    @Test
    fun `London School Mocks Collaborators`() {
        // given
        val customer = Customer()
        val mockStore = mock<IStore>()

        // when
        `when`(mockStore.hasEnoughInventory(any(), any())).thenReturn(false)
        val result = customer.purchase(mockStore, Product.Shampoo, 5)

        // then
        assertFalse(result)
        verify(mockStore, never()).minus(any(), any())
    }
}