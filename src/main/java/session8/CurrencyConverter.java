package session8;

public class CurrencyConverter {
    private static double rate;

    public static double getRate() {
        return rate;
    }

    public static void setRate(double rate) {
        if (rate <= 0) {
            throw new IllegalArgumentException("Rate must be greater than 0");
        }
        CurrencyConverter.rate = rate;
    }

    public static String toUSD(double vnd){
        if (vnd < 0) {
            throw new IllegalArgumentException("VND amount must not be negative");
        }
        if (rate <= 0) {
            throw new IllegalStateException("Rate must be set before converting");
        }
        return formatUSD(vnd / rate);
    }

    public static String formatUSD(double usd){
        return String.format("$%.2f", usd);
    }
}
