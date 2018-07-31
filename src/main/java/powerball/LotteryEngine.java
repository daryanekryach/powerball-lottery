package powerball;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Lottery engine that simulates PowerBall lottery.
 */
public class LotteryEngine {
    private int[] nonPowerball;
    private int powerball;

    private Map<String, Integer> winInfo;
    private Map<String, Integer> comboPrize;
    private ArrayList<Ticket> registeredTickets;

    private String[] winCombos = {"5+1", "5+0", "4+1", "4+0", "3+1", "3+0", "2+1", "1+1", "0+1"};
    private int[] moneyPrize = {168000000, 1000000, 50000, 100, 100, 7, 7, 4, 4};

    public LotteryEngine() {
        initializeLotteryInfo();
    }

    /**
     * Initializes information on winning combinations and money prizes.
     */
    private void initializeLotteryInfo() {
        registeredTickets = new ArrayList<>();
        winInfo = new LinkedHashMap<>();
        comboPrize = new LinkedHashMap<>();

        for (int i = 0; i < winCombos.length; i++) {
            winInfo.put(winCombos[i], 0);
            comboPrize.put(winCombos[i], moneyPrize[i]);
        }
    }

    /**
     * Performs ticket registration in lottery
     *
     * @param ticket ticker to register
     */
    public void registerTicket(Ticket ticket) {
        registeredTickets.add(ticket);
    }

    /**
     * Performs process of drawing white balls and powerball.
     */
    public void performDraw() {
        nonPowerball = ThreadLocalRandom.current()
                .ints(1, 69).distinct().limit(5).toArray();
        powerball = new Random().nextInt(26) + 1;
    }

    /**
     * Checks registered tickets for winning combinations and assigns money prize for ticket, if any.
     */
    public void assignWins() {
        List<Integer> whiteBallsList = Arrays.stream(nonPowerball).boxed().collect(Collectors.toList());
        int nonPowerballMatch;
        boolean powerballMatch;
        for (Ticket ticket : registeredTickets) {
            nonPowerballMatch = 0;
            powerballMatch = false;

            for (int number : ticket.getWhiteBalls()) {
                if (whiteBallsList.contains(number))
                    nonPowerballMatch++;
            }

            if (ticket.getPowerball() == powerball)
                powerballMatch = true;

            String combo = nonPowerballMatch + "+" + (powerballMatch ? 1 : 0);
            winInfo.computeIfPresent(combo, (k, v) -> v + 1);

            ticket.setMatchCombo(combo);
            ticket.setMoneyPrize(comboPrize.getOrDefault(combo, 0));
        }
    }

    /**
     * Prints lottery statistics: total amount of registered tickets, combination wins' both expected
     * and observed results for probability and frequencies.
     */
    public void printLotteryStatistics() {
        System.out.println("Number of registered tickets: " + registeredTickets.size());

        Map<String, Double> probability = Statistics.calculateProbability(winCombos);

        System.out.format("%5s %11s %19s  %13s %13s %13s \n", "Combination", "Prize", "Probability th.", "Probability exp.",
                "Expected fr.", "Observed fr.");
        winInfo.forEach((key, value) -> System.out.format("%6s %18s %13f %17f %16f %10d \n",
                key, comboPrize.get(key) + "$", probability.get(key), (double) value / registeredTickets.size(),
                probability.get(key) * registeredTickets.size(), value));
    }

    /**
     * Returns number of registered tickets.
     *
     * @return number of registered tickets
     */
    public int getRegisteredTicketsCount() {
        return registeredTickets.size();
    }

    /**
     * Returns information on tickets win for current lottery.
     *
     * @return win information as map
     */
    public Map<String, Integer> getWinInfo() {
        return winInfo;
    }
}