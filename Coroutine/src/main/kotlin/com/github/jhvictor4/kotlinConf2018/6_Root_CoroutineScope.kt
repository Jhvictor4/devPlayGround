package com.github.jhvictor4.kotlinConf2018

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * In read-world applications, there are always something that have lifecycle.
 * (MVVM - view model, backend - external request)
 *
 * This can be done by enforcing CoroutineScope Type to the class.
 *
 */

class SomethingWithLifeCycle: CoroutineScope {
    private val job = Job()

    /**
     * The End of It's Lifetime.
     */
    fun close() {
        job.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun doSomething() {
        processRefs(references = TODO())
    }
}

/**
 * This way above does not leak any coroutines :)
 */
