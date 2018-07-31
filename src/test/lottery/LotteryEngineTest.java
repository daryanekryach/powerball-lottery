package lottery;

import org.junit.BeforeClass;
import org.junit.Test;
import powerball.LotteryEngine;
import powerball.Ticket;

import static org.junit.Assert.assertEquals;

public class LotteryEngineTest {
    private static LotteryEngine lottery;

    @BeforeClass
    public static void setUp() {
        lottery = new LotteryEngine();
    }

    @Test
    public void lotteryEngineTest() {
        int ticketsAmount = 800000;
        for (int i = 0; i < ticketsAmount; i++) {
            Ticket ticket = new Ticket();
            ticket.quickPick();
            lottery.registerTicket(ticket);
        }
        lottery.performDraw();
        lottery.assignWins();
        lottery.printLotteryStatistics();

        assertEquals(ticketsAmount, lottery.getRegisteredTicketsCount());
    }
}
