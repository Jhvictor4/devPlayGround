package com.github.jhvictor4.kotlinConf2019

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * subscribe function below is HOT => we cannot just lose it. we need to clear it after usage.
 */
fun rxApproach() {
    lateinit var  observable: Observable<String>
    val composite = CompositeDisposable()

    composite.add(
        observable.subscribe { str -> println(str) }
    )

    composite.clear()
}

/**
 * In Kotlin Flow:
 *
 * we subscribe by launching JOB => which is always bound to certain coroutineScope.
 * And, as you already know, the job will end safe.
 */
fun flowApproach() {
    lateinit var scope: CoroutineScope

    flowOf(1, 2, 3)
        .onEach { int -> println(int * int) }
        .launchIn(scope)

    scope.cancel()
}
