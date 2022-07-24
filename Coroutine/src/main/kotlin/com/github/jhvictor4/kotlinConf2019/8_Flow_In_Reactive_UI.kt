package com.github.jhvictor4.kotlinConf2019

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun a() = runBlocking {

    flowOf(1, 2, 3)
        .onEach { it * it }
        .launchIn(this)

}
