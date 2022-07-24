package com.github.jhvictor4.kotlinConf2019

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

data class Response(val name: String)

fun compute(string: String): Response {
    return Response(string)
}

suspend fun foo(): List<Response> = buildList {
    add(compute("A"))
    add(compute("B"))
    add(compute("C"))
}


fun main() = runBlocking {
    val result = foo()
    result.forEach { println(it) }
}

/**
 * This way above works nice, but we have to wait until everything is ready.
 * What if we want to show each element immediately when it is ready?
 */


/**
 * Method 1. use Channel
 *
 * foo2 returns quickly. and two coroutine works simultaneously.
 */
@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.foo2(): ReceiveChannel<Response> = produce {
    send(compute("A"))
    send(compute("B"))
    send(compute("C"))
}

fun main2() = runBlocking {
    val channel = foo2()
    for (x in channel) println(x)
}

/**
 * Here's a problem : Channel IS HOT.
 *
 * If no one is listening (if we delete ⬆ 47th line above), it hangs.
 */
