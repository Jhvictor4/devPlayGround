package com.github.jhvictor4.cinema

class Theater(
    private val ticketSeller: TicketSeller,
) {
    fun enter(audience: Audience): Any = when {
        audience.bag.hasInvitation -> ticketSeller.ticketOffice.getTicket().also(audience.bag::setTicket)
        else -> sell(audience, ticketSeller.ticketOffice)
    }

    private fun sell(audience: Audience, ticketOffice: TicketOffice) {
        val ticket = ticketOffice.getTicket()
        audience.bag.setTicket(ticket)
        audience.bag.minusAmount(ticket.fee)
    }
}
