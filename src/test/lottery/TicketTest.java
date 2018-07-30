package lottery;

import org.junit.Test;
import powerball.Ticket;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class TicketTest {

    @Test
    public void easyPickTest() {
        Ticket ticket = new Ticket();
        ticket.quickPick();

        assertNotNull(ticket);
        assertNotEquals(0, ticket.getPowerball());
        assertNotNull(ticket.getWhiteBalls());
    }

    @Test
    public void setWhiteBallsTest() throws NoSuchFieldException, IllegalAccessException {
        final Ticket ticket = new Ticket();
        int[] combinations = {12, 45, 14, 8, 55};
        ticket.setWhiteBalls(combinations);
        final Field field = ticket.getClass().getDeclaredField("whiteBalls");
        field.setAccessible(true);
        assertEquals(field.get(ticket), combinations);
    }

    @Test
    public void getWhiteBallsTest() throws NoSuchFieldException, IllegalAccessException {
        final Ticket ticket = new Ticket();
        final Field field = ticket.getClass().getDeclaredField("whiteBalls");
        field.setAccessible(true);
        int[] combinations = {12, 45, 14, 8, 55};
        field.set(ticket, combinations);
        assertEquals(combinations, ticket.getWhiteBalls());
    }

    @Test
    public void setPowerballTest() throws NoSuchFieldException, IllegalAccessException {
        final Ticket ticket = new Ticket();
        int powerball = 22;
        ticket.setPowerball(powerball);
        final Field field = ticket.getClass().getDeclaredField("powerball");
        field.setAccessible(true);
        assertEquals(field.get(ticket), powerball);
    }

    @Test
    public void getPowerballTest() throws NoSuchFieldException, IllegalAccessException {
        final Ticket ticket = new Ticket();
        final Field field = ticket.getClass().getDeclaredField("powerball");
        field.setAccessible(true);
        int powerball = 22;
        field.set(ticket, powerball);
        assertEquals(powerball, ticket.getPowerball());
    }

    @Test
    public void setMatchComboTest() throws NoSuchFieldException, IllegalAccessException {
        final Ticket ticket = new Ticket();
        String combo = "3+0";
        ticket.setMatchCombo(combo);
        final Field field = ticket.getClass().getDeclaredField("matchCombo");
        field.setAccessible(true);
        assertEquals(field.get(ticket), combo);
    }

    @Test
    public void getMatchComboTest() throws NoSuchFieldException, IllegalAccessException {
        final Ticket ticket = new Ticket();
        final Field field = ticket.getClass().getDeclaredField("matchCombo");
        field.setAccessible(true);
        String combo = "3+0";
        field.set(ticket, combo);
        assertEquals(combo, ticket.getMatchCombo());
    }

    @Test
    public void setMoneyPrizeTest() throws NoSuchFieldException, IllegalAccessException {
        final Ticket ticket = new Ticket();
        int prize = 7;
        ticket.setMoneyPrize(prize);
        final Field field = ticket.getClass().getDeclaredField("moneyPrize");
        field.setAccessible(true);
        assertEquals(field.get(ticket), prize);
    }

    @Test
    public void getMoneyPrizeTest() throws NoSuchFieldException, IllegalAccessException {
        final Ticket ticket = new Ticket();
        final Field field = ticket.getClass().getDeclaredField("moneyPrize");
        field.setAccessible(true);
        int prize = 7;
        field.set(ticket, prize);
        assertEquals(prize, ticket.getMoneyPrize());
    }
}
