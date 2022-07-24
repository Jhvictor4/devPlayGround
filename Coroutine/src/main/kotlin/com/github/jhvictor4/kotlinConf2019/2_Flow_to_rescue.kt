package com.github.jhvictor4.kotlinConf2019

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun fooFlow(): Flow<Response> = flow {
    emit(compute("A"))
    emit(compute("B"))
    emit(compute("C"))
}

fun main3() = runBlocking {
    val flow = fooFlow()
    flow.collect(::println)
}

/**
 * Flow is cold.
 * - After it returns, it does nothing until it's collected.
 *
 * Flow is declarative.
 * - when we declare, we don't actually do something, but we declare what's going to happen.
 */
fun strings(): Flow<String> = flow {
    emit("A")
    emit("B")
    emit("C")
}

fun compute(): Flow<Response> {
//    return strings().map { compute(it) }
    return flowOf("A", "B", "C").map { compute(it) }
}

/**
 * Flow does not need suspend modifier - because it is declarative!
 */

/**
 * 3. Flow is Reactive
 *
 * We don't have to wait until everything is done. if flow emits something, it gets operated!
 *
 * Flow is built upon "Reactive Streams Specification"
 * -> It works well with "Project Reactor" or "RxJava"
 */


