package com.github.jhvictor4.kotlinConf2019

import io.reactivex.Flowable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import java.util.concurrent.TimeUnit

fun main() {
    val flowable = Flowable.range(1, 3)
    val flow = flowOf(1, 2, 3)

    // RxJava
    flowable.startWith(1)
    flowable.startWith(listOf(1, 2, 3))
    flowable.delaySubscription(10, TimeUnit.SECONDS)
    // flowable.onEach(..)
    // flowable.onErrorReturn(..)
    // generate { .. }

    // Kotlin Flow
    flow.onStart { emit(1) }
    flow.onStart { emitAll(flow) }
    flow.onStart { delay(100) }
    flow.onEach { delay(100) }
    flow.catch { emit(1) }
    // flow<Int> { .. }

    /**
     * With Kotlin Flow, you don't need multiple operations to do similar jobs.
     * It's the best way to work with reactive programming :)
     *
     * Kotlin Flow has Simple design, which leads to better performance, also.
     */
}
