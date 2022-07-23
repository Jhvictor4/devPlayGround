package com.github.jhvictor4.pure_coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch

/**
 * without limits, you will result in problems.
 */

fun CoroutineScope.downloader(
    references: ReceiveChannel<Reference>,
    locations: SendChannel<Location>,
) = launch {
    val requested = mutableSetOf<Location>()
    for (ref in references) {
        ref.resolveLocation()
            .takeUnless { requested.add(it) }
            ?.let { locations.send(it) }
    }
}

/**
 * Each Request, which means "each location" below, fans-out.
 * In other words, it gets received by only a single worker.
 *
 * PROBLEMS HERE : we need to figure out what reference to store the processed Content.
 *
 */
fun CoroutineScope.worker(
    locations: ReceiveChannel<Location>,
) = launch {
    for (loc in locations) {
        val content = downloadContent(loc)
        // processContent(ref, content) << This cannot be done since we don't know the reference.
    }
}

fun processContent(ref: Reference, content: Content) {
    ref.content = content
    println("$ref CONTENT REGISTERED. $content")
}

/**
 * video timeline since 23:00
 *
 * So what do we do?
 *
 * We can send the content back to the Downloader!
 */
data class LocContent(val location: Location, val content: Content)


fun CoroutineScope.worker(
    locations: ReceiveChannel<Location>,
    contents: SendChannel<LocContent>,
) = launch {
    for (loc in locations) {
        val content = downloadContent(loc)
        contents.send(LocContent(loc, content))
    }
}

/**
 * Well, then we should add LocContent channel to downloader.
 * But Downloader has shared-m-state, which makes it difficult to iterate two channels in parallel.
 */
//fun CoroutineScope.downloader(
//    references: ReceiveChannel<Reference>,
//    locations: SendChannel<Location>,
////    contents: ReceiveChannel<LocContent>,
//) = launch {
//    val requested = mutableSetOf<Location>()
//    for (ref in references) {
//        ref.resolveLocation()
//            .takeUnless { requested.add(it) }
//            ?.let { locations.send(it) }
//    }
//}
