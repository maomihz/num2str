package maomihz;

public class NumConverter {
    // Define the constants
    private static final String[] basis = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve"};
    private static final String[] tenth = { "twen", "thir", "four", "fif", "six", "seven", "eigh", "nine" };
    private static final String[] higher = { "thousand", "million", "billion", "trillion", "quadrillion", "quintillion", "sextillion", "septillion", "octillion", "nonillion", "decillion", "undecillion", "duodecillion", "tredecillion", "quattuordecillion", "quindecillion", "sexdecillion", "septendecillion", "octodecillion", "novemdecillion", "vigintillion" };


    // This static function for easy conversion
    public static String getWord(String str) {
        return new NumConverter(str).toString();
    }
    public static String getWord(int num) {
        return new NumConverter(num).toString();
    }


    // Private fields
    private String value;

    // Constructors
    public NumConverter() {
        this(0);
    }
    public NumConverter(int val) {
        value = Integer.toString(val);
    }
    public NumConverter(String val) {
        value = val.trim();
    }


    public String toString() {
        // First thing is to remove all leading zeros
        if (value.length() == 0) {
            return "zero";
        }
        if (value.startsWith("-")) {
            return "negative " + getWord(value.substring(1));
        }
        if (value.startsWith("0")) {
            return getWord(value.substring(1));
        }

        // Handle 0 ~ 99
        if (value.length() < 4) {
            int val = Integer.parseInt(value);
            // 0 ~ 12
            if (val <= 12) {
                return basis[val];
            }

            // 13 ~ 19
            if (val <= 19) {
                return tenth[val % 10 - 2] + "teen";
            }

            // 20 ~ 99
            if (val < 100) {
                if (val % 10 == 0) {
                    // Special case 40
                    if (val == 40) return "forty";
                    return tenth[val / 10 - 2] + "ty";
                }
                return getWord(val / 10 * 10) + "-" + getWord(val % 10);
            }

            // 100 ~ 999
            if (val % 100 == 0) {
                return getWord(val / 100) + " hundred";
            }
            return getWord(val / 100) + " hundred " + getWord(val % 100);
        }



        // Large numbers
        // First to split the first three characters from the rest
        int initial_length = (value.length() - 1) % 3 + 1;
        String first = value.substring(0, initial_length);
        String rest = value.substring(initial_length);
        // Calculate the order based on the length (Similar to log10)
        int order = (value.length() - 1) / 3 - 1;

        StringBuilder result = new StringBuilder();
        result.append(getWord(first) + " " + higher[order]);
        String result_rest = getWord(rest);
        if (!result_rest.equals("zero")) {
            result.append(", " + result_rest);
        }
        return result.toString();
    }
}
