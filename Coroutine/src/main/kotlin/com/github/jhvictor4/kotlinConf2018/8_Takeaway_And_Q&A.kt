package com.github.jhvictor4.kotlinConf2018


/**
 * Coroutines ARE NOT LIKE threads.
 *
 * they are qualitively different concept - leads to structure your code in a different way
 * so rethink the way you structure your code.
 *
 */

/**
 * Q1. - Is there any way so that I can skip writing boilerplate?
 *
 * A. - In real world application, there's always some kind of architecture
 * so for example, say that you have MVP arch. & presenters => just write base presenter class with some coroutine boilerplate
 * that's it!
 */

/**
 * Q2. - Is there any way to report error occurred in coroutine to the caller?
 *
 * Yes, the root cause of any exception will be thrown to the caller.
 * and it's the main difference between threads: you will never lose exceptions.
 */

/**
 * Q3. - How cheap is communicating between coroutines?
 *
 * Channel is concurrent communication primitive => concurrency is never cheap
 * If workers (in prev architecture) do something heavy => then it subverts(?) you.
 */

/**
 * Q4. - Is there any difference between using suspending function with "coroutineScope extension" vs "coroutine top level function" ?
 *
 * latter.
 *
 * coroutineScope primitives are bridge between suspending function & coroutineScope extension.
 * If you open coroutineScope primitives, it will never return until it's done.
 * So you never leak anything.
 */


/**
 * Q5. - How can UI thread be reported by the channels that something has done?
 *
 * It's easy : place channel to UI thread, and render it. there are more use-cases, so read more docs.
 */
