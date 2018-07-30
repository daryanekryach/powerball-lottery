package powerball;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Model for Ticket, allows to set chosen by user numbers for white balls and powerball or use QuickPick function.
 * Stores matching combo (if any) for combination drawn in lottery and money prize that has been won.
 */
public class Ticket {
    private int[] whiteBalls;
    private int powerball;
    private String matchCombo;
    private int moneyPrize;

    /**
     * Method that allows to perform QuickPick option by randomly picking white balls numbers and powerball.
     */
    public void quickPick() {
        whiteBalls = ThreadLocalRandom.current()
                .ints(1, 69).distinct().limit(5).toArray();
        powerball = new Random().nextInt(26) + 1;
    }

    /**
     * Overridden method to save all ticket information to String.
     *
     * @return ticket info.
     */
    @Override
    public String toString() {
        StringBuilder ticket = new StringBuilder();
        ticket.append("Your ticket: ");
        for (int ball : whiteBalls) {
            ticket.append(ball).append(" ");
        }
        ticket.append(powerball);
        ticket.append("\nMatch combo: ").append(matchCombo);
        ticket.append("\nPrize: ").append(moneyPrize);
        return ticket.toString();
    }

    //region GETTERS AND SETTERS REGION
    public int[] getWhiteBalls() {
        return whiteBalls;
    }

    public void setWhiteBalls(int[] whiteBalls) {
        for (int number : whiteBalls) {
            if (number >= 1 && number <= 69) continue;
            else throw new IllegalArgumentException("Picked number " + number + " does not satisfy lottery rules." +
                    " Choose another number from 1 to 69.");
        }
        for (int j = 0; j < whiteBalls.length; j++) {
            for (int k = j + 1; k < whiteBalls.length; k++) {
                if (whiteBalls[j] == whiteBalls[k]) {
                    throw new IllegalArgumentException("No duplicates allowed.");
                }
            }
            this.whiteBalls = whiteBalls;
        }
    }

    public int getPowerball() {
        return powerball;
    }

    public void setPowerball(int powerball) {
        if (powerball >= 1 && powerball <= 26)
            this.powerball = powerball;
        else throw new IllegalArgumentException("Picked number does not satisfy lottery rules." +
                " Choose another number from 1 to 26 ");
    }

    public String getMatchCombo() {
        return matchCombo;
    }

    public void setMatchCombo(String matchCombo) {
        this.matchCombo = matchCombo;
    }

    public int getMoneyPrize() {
        return moneyPrize;
    }

    public void setMoneyPrize(int moneyPrize) {
        this.moneyPrize = moneyPrize;
    }
    //endregion

}
