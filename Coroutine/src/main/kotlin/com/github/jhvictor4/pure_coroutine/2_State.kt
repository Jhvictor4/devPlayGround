package com.github.jhvictor4.pure_coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * https://www.youtube.com/watch?v=a3agLJQ6vt8&t=1849s
 *
 * timeline since 9:00
 */

/**
 * what can we do if we want to prevent same resources to be resolved twice?
 */

/**
 * As we've been told a lot, in OOP (or programming with threads)
 * Let's write a class
 */
class Downloader {
    // fixme - bad practice
    //  SHARED MUTABLE STATE IS DANGEROUS!
    private val requested = mutableSetOf<Location>()

    fun downloadRef(ref: Reference) {
        val location = ref.resolveLocation()
        if (requested.add(location)) {
            // schedule download
        }
    }
}

/**
 * BTW, coroutines don't need shared mutable state
 *
 * instead of having "Synchronization Primitives" (like above),
 * we can use "Communication Primitives".
 *
 * and instead of writing classes, we can use Coroutines.
 */
fun communication(refs: List<Reference>) = runBlocking {
    launch {
        // the state below is CONFINED to the coroutine.
        val requested = mutableSetOf<Location>()

        // But, here "Refs" below is also a synchronization-primitives
        // So, what to use?
        refs.forEach { TODO("schedule the download, guaranteeing parallelism") }
    }
}

/**
 * Channel to rescue
 *
 * p.s. code style below is a nice convention
 */
fun CoroutineScope.downloader(
    references: ReceiveChannel<Reference>,
) = launch {
    val requested = mutableSetOf<Location>()

    for (ref in references) {
        val loc = ref.resolveLocation()
        if (requested.add(loc)) {
            launch { TODO("scheduling here guarantees parallelism") }
        }
    }

    // FP style
    references
        .consumeAsFlow()
        .collect {
            val loc = it.resolveLocation()
            if (requested.add(loc)) {
                launch { TODO("scheduling here guarantees parallelism") }
            }
        }
}

/**
 * Practice above has a problem:
 *  We Should Limit Concurrency.
 */
