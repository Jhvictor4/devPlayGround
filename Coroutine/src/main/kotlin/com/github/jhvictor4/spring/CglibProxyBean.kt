package com.github.jhvictor4.spring

import org.springframework.cglib.proxy.Enhancer
import org.springframework.cglib.proxy.InvocationHandler
import java.lang.reflect.Method

open class ConcreteBusiness {
    open fun work() {
        println("I'm at hard work.")
    }
}

class CBHandler(private val target: ConcreteBusiness): InvocationHandler {
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        (1..3).forEach { _ -> target.work() }
        return 1
    }
}

fun main() {
    val target = ConcreteBusiness()

    val resultProxy = Enhancer.create(
        ConcreteBusiness::class.java,
        CBHandler(target),
    )

    (resultProxy as ConcreteBusiness).work()
}
