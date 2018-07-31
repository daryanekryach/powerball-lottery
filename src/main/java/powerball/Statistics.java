package powerball;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Helper class to perform Statistics calculations as probability, combinations and factorials.
 */
public class Statistics {
    /**
     * Performs calculation of probability for each given combination.
     *
     * @param winCombos possible combinations
     * @return map of combination and its probability
     */
    public static Map<String, Double> calculateProbability(String[] winCombos) {
        Map<String, Double> probability = new LinkedHashMap<>();
        long total = calculateCombination(69, 5) * 26;
        int powerballNumber;
        long result;
        double probabilityResult;

        for (String combo : winCombos) {
            String[] numbers = combo.split("\\+");

            if (Integer.parseInt(numbers[1]) == 1) {
                powerballNumber = 1;
            } else powerballNumber = 25;

            result = calculateCombination(5, Integer.parseInt(numbers[0]))
                    * (calculateCombination(64, 5 - Integer.parseInt(numbers[0]))
                    * powerballNumber);
            probabilityResult = (double) result / (double) total;
            probability.put(combo, probabilityResult);
        }
        return probability;
    }

    /**
     * Calculates combination nCk without repetitions.
     *
     * @param n possible numbers
     * @param k numbers used in combination
     * @return number of combinations
     */
    private static long calculateCombination(int n, int k) {
        return calculateFactorial(n).divide(
                (calculateFactorial(k).multiply(calculateFactorial(n - k)))).longValue();
    }

    /**
     * Calculates factorial of given number.
     *
     * @param n given number
     * @return factorial
     */
    private static BigInteger calculateFactorial(int n) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
}
