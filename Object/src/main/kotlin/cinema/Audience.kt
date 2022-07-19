package com.github.jhvictor4.cinema

import java.time.LocalDateTime


/**
 * 관광객은 가방을 소지할 수 있다.
 */
class Audience(private val bag: Bag) {
    fun buy(ticket: Ticket): Long {
        if (!bag.hasInvitation) {
            bag.minusAmount(ticket.fee)
        }

        bag.setTicket(ticket)
        return ticket.fee
    }
}

/**
 * 초대장
 */
class Invitation(private val `when`: LocalDateTime)

/**
 * 소지품을 담을 수 있는 가방
 * 가방에는 현금, 티켓, 초대장을 가지고 있을 수 있다.
 *
 * ticket은 발권해야 하므로 lazy initialization
 */
class Bag(
    private var amount: Long,
    private var invitation: Invitation? = null,
) {
    private lateinit var ticket: Ticket

    val hasTicket: Boolean
        get() = this::ticket.isInitialized

    val hasInvitation: Boolean
        get() = invitation != null

    fun setTicket(ticket: Ticket) {
        this.ticket = ticket
    }

    fun minusAmount(amount: Long) {
        this.amount += amount
    }

    fun plusAmount(amount: Long) {
        this.amount += amount
    }
}


