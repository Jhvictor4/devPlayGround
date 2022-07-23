package com.github.jhvictor4.pure_coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

/**
 * Select to Rescue
 */

fun CoroutineScope.downloader(
    references: ReceiveChannel<Reference>,
    locations: SendChannel<Location>,
    contents: ReceiveChannel<LocContent>,
) = launch {
    val requested = mutableMapOf<Location, MutableList<Reference>>()
    while (true) {
        select {
            references.onReceive { ref ->
                val loc = ref.resolveLocation()
                val refs = requested[loc]
                if (refs == null) {
                    requested[loc] = mutableListOf(ref)
                    locations.send(loc)
                } else {
                    requested[loc]!!.add(ref)
                }
            }
            contents.onReceive { locContent ->
                val refs = requested.remove(locContent.location)!!
                refs.forEach { processContent(it, locContent.content) }
            }
        }
    }
}
