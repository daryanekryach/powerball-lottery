package lottery;

import org.junit.Test;
import powerball.LotteryEngine;
import powerball.Ticket;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LotteryTest {
    private final LotteryEngine lottery = new LotteryEngine();

    @Test
    public void getRegisteredTicketsCountTest() throws NoSuchFieldException, IllegalAccessException {
        final Field field = lottery.getClass().getDeclaredField("registeredTickets");
        field.setAccessible(true);
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(new Ticket());
        field.set(lottery, tickets);
        assertEquals(1, lottery.getRegisteredTicketsCount());
    }

    @Test
    public void getWinInfoTest() throws NoSuchFieldException, IllegalAccessException {
        final Field field = lottery.getClass().getDeclaredField("winInfo");
        field.setAccessible(true);
        assertNotNull(lottery.getWinInfo());
    }

    @Test
    public void registerTicketTest() {
        int initialTicketCount = lottery.getRegisteredTicketsCount();
        lottery.registerTicket(new Ticket());
        assertEquals(initialTicketCount + 1, lottery.getRegisteredTicketsCount());
    }
}
