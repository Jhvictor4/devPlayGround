package com.github.jhvictor4.kotlinConf2018

import kotlinx.coroutines.CoroutineScope

/**
 * What is difference between suspend function & coroutineScope?
 */

/**
 * Suspend function:
 *
 * does something long
 * we wait for it to complete without blocking.
 *
 * it never returns until it's done.
 */
suspend fun doSomething() {}

/**
 * CoroutineScope.extensionFunction:
 *
 * it quickly returns
 * it does not wait for them to finish
 */
fun CoroutineScope.extensionFunction() {}
