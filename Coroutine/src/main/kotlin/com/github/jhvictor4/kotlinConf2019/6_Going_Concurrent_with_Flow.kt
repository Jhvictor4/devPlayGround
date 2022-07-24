package com.github.jhvictor4.kotlinConf2019

import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

/**
 * Flow is asynchronous, yet sequential.
 */

/**
 * We don't need complicated code.
 *
 * All we need is BUFFER!
 */
fun buffer() = runBlocking {

    /**
     * Buffer runs collector in separate coroutine & sends the result via channel
     */
    flowOf("1", "2", "3")
        .buffer()
        .collect { str -> str + 3 }

}
