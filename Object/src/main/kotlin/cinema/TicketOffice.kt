package com.github.jhvictor4.cinema

/**
 * 공연 입장을 위한 티켓
 */
class Ticket(val fee:  Long)

/**
 * 매표소
 */
class TicketOffice(
    private var amount: Long,
    private val tickets: MutableList<Ticket>,
) {
    fun getTicket(): Ticket {
        return tickets.removeFirst()
    }

    fun plusAmount(amount: Long) {
        this.amount += amount
    }

    fun minusAmount(amount: Long) {
        this.amount -= amount
    }
}

/**
 * 매표소 직원
 */
class TicketSeller(
    private val ticketOffice: TicketOffice,
) {
    fun sellTo(audience: Audience) {
        val amount = audience.buy(ticketOffice.getTicket())
        ticketOffice.plusAmount(amount)
    }
}
