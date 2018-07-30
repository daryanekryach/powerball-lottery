package lottery;

import org.junit.BeforeClass;
import org.junit.Test;
import powerball.LotteryEngine;
import powerball.Ticket;

import static org.junit.Assert.assertEquals;

public class LotteryEngineTest {
    private LotteryEngine lottery;

    @BeforeClass
    public void setUp() {
        lottery = new LotteryEngine();
    }

    @Test
    public void registerTickets() {
        int ticketsAmount = 550000;
        for (int i = 0; i < ticketsAmount; i++) {
            Ticket ticket = new Ticket();
            ticket.quickPick();
            lottery.registerTicket(ticket);
        }
        assertEquals(ticketsAmount, lottery.getRegisteredTicketsCount());
    }

    @Test
    public void collectWins(){

    }
}
