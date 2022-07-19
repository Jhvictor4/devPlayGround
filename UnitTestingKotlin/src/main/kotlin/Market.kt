interface IStore {
    fun hasEnoughInventory(product: Product, amount: Int): Boolean
    fun addInventory(product: Product, amount: Int)
    fun minus(targetProduct: Product, amount: Int)
}

open class Store: IStore {
    private val products = mutableMapOf<Product, Int>()

    override fun addInventory(product: Product, amount: Int) {
        products[product] = products.getOrDefault(product, 0) + amount
    }

    override fun hasEnoughInventory(product: Product, amount: Int): Boolean {
        val currentSize = products[product] ?: 0
        return currentSize >= amount
    }

    override fun minus(targetProduct: Product, amount: Int) {
        if (products[targetProduct] == null || products[targetProduct]!! < amount)  {
            throw IllegalStateException()
        }

        products[targetProduct] = products[targetProduct]!! - amount
        return
    }

    fun getCount(product: Product): Int {
        return products[product] ?: 0
    }
}

open class Customer {
    private val inventory = mutableMapOf<Product, Int>()

    fun purchase(store: IStore, product: Product, amount: Int): Boolean = kotlin.runCatching {
        require(store.hasEnoughInventory(product, amount))
        store.minus(product, amount)
        inventory[product] = (inventory[product] ?: 0) + amount
        true
    }.getOrElse { false }
}

enum class Product {
    Shampoo;
}