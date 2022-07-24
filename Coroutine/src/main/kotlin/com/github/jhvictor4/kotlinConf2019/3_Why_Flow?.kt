package com.github.jhvictor4.kotlinConf2019

import io.reactivex.Flowable
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

/**
 * Why flow? instead of using some libraries like RxJava, Project Reactor, ...?
 */

/**
 * In RxJava, there are always two ways of converting values in Flowable<T>
 * - Synchronous way, Async way..
 *
 *     1. map : only Synchronous mapper code can be used
 *     2. flatMapSingle: Asynchronous
 *
 *     1. filter
 *     2. ???? (Not Exists)
 */
fun ifYouUseRxJava() {
    val a: Flowable<Int> = Flowable.range(1, 100)
    val flowable: Flowable<Int> = a.map { anyInt: Int -> anyInt + 2 }
//    val flowable2 = a.flatMapSingle { anyInt: Int -> TODO() }
}

/**
 * But Not on Flow :)
 */
fun ifYouUseFlow() {
    flowOf("A", "B")
        .map { TODO("this lambda allows suspension") }
        .filter { TODO("this lambda allows suspension") }
}
