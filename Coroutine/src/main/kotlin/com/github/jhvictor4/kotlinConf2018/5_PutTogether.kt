package com.github.jhvictor4.kotlinConf2018

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

suspend fun processContent(ref: Reference, content: Content) {
    delay(500)
    ref.content = content
}

suspend fun downloadContent(loc: Location): Content {
    delay(500)
    return Content(loc.name)
}

// code below is all wrapped up

const val N_WORKERS = 3

fun CoroutineScope.processRefs(references: ReceiveChannel<Reference>) {
    val locations = Channel<Location>(5)
    val contents = Channel<LocContent>(5)
    repeat(N_WORKERS) { worker(locations, contents) }
    downloader(references, locations, contents)
}

/**
 * cancel 하면 한참 있다가 에러 나면서 닫힌다. gracefully 하게 닫을 수 없을까?
 * cancelChildren 하면 leak 없을까?
 */
@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main() = withContext(Dispatchers.Default) {
    println("JOB STARTED")

    val refs = produce {
        for (x in 1..100) {
            delay(100)
            send(Reference("$x"))
        }
    }

    processRefs(refs)

    val input = readln()
    if (input == " ") {
        println("JOB STOPPED")
        coroutineContext.cancelChildren()
    }
}
