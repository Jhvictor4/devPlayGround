package com.github.jhvictor4.pure_coroutine

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

/**
 * Select to Rescue
 */

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.downloader(
    references: ReceiveChannel<Reference>,
    locations: SendChannel<Location>,
    contents: ReceiveChannel<LocContent>,
) = launch {
    println("DOWNLOADER STARTED")
    val requested = mutableMapOf<Location, MutableList<Reference>>()
    while (!references.isClosedForReceive) {
        try {
            select {
                references.onReceive { ref ->
                    println("$ref RECEIVED")
                    val loc = ref.resolveLocation()
                    val refs = requested[loc]
                    if (refs == null) {
                        requested[loc] = mutableListOf(ref)
                        println("$loc RESOLVED")
                        locations.send(loc)
                    } else {
                        requested[loc]!!.add(ref)
                    }
                }

                contents.onReceive { locContent ->
                    val refs = requested.remove(locContent.location)!!
                    refs.forEach {
                        processContent(it, locContent.content)
                        println("$it CONTENT REGISTERED. ${locContent.content}")
                    }
                }
            }
        } catch (e: CancellationException) {
            this.cancel(e)
        }
    }
}
