package com.github.jhvictor4.spring

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

interface Business {
    fun doTheBusiness() {
        println("I'm at work ~~")
    }
}

class BusinessImpl: Business

class ProxyHandler(
    private val target: Business
): InvocationHandler {
    override fun invoke(proxy: Any, method: Method, args: Array<out Any>?) {
        println("HI")
        method.invoke(target)
        println("BYE")
    }
}

fun main() {
    val target = BusinessImpl()

    val proxy = Proxy.newProxyInstance(Business::class.java.classLoader, arrayOf(Business::class.java), ProxyHandler(target))
    (proxy as Business).doTheBusiness()
}
