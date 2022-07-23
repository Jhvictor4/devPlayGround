package com.github.jhvictor4.pure_coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * https://www.youtube.com/watch?v=a3agLJQ6vt8&t=1849s
 */

data class Reference(val url: String) {
    lateinit var content: Content
    fun resolveLocation(): Location = Location(url)
}
data class Location(val name: String)
data class Content(val value: String)

fun processContent(c: Content) {
    return
}

/**
 * function below incurs memory leak if one of the element throws exception
 */
fun vulnerableProcessRefs(refs: List<Reference>) = refs.forEach {
   GlobalScope.launch { processContent(downloadContent(it.resolveLocation())) }
}

/**
 * function below guarantees "Structured Concurrency"
 * unlike threads, coroutines have scopes that can be bound to parent coroutines
 * now, if one of the elements crashes, parent scope safely terminates un-launched children.
 * so no leak occurs :)
 */
suspend fun processRefs(refs: List<Reference>) = coroutineScope {
    refs.forEach { launch { processContent(downloadContent(it.resolveLocation())) } }
}
