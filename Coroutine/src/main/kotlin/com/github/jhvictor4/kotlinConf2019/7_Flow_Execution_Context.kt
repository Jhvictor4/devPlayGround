package com.github.jhvictor4.kotlinConf2019

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * Where all these flow execution runs?
 */

/**
 * Flow Execution Context is always the same context of the caller.
 */

/**
 * What if I don't want to!?
 * (I want to run execution on another thread (mostly Not UI))
 *
 * => Use FlowOn!!
 */
fun main() = runBlocking {
    flow().collect { println("RESULTED $it") }
}

private fun flow() =
    flowOf(1, 2, 3)
        .map { compute(it) }
//        .flowOn(Dispatchers.IO)

private suspend fun compute(int: Int): Int = runBlocking {
    println(Thread.currentThread())
    delay(1000)
    int * int
}
