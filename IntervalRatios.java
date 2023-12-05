import java.util.HashMap;
import java.util.Map;

public class IntervalRatios {
    private static final Map<String, Double> intervalRatios = new HashMap<>();

    static {
        intervalRatios.put("minor third", Math.pow(2, 3.0 / 12));
        intervalRatios.put("major third", Math.pow(2, 4.0 / 12));
        intervalRatios.put("perfect fifth", Math.pow(2, 7.0 / 12));
        intervalRatios.put("tritone", Math.pow(2, 6.0 / 12));
        intervalRatios.put("minor sixth", Math.pow(2, 8.0 / 12));
        intervalRatios.put("major sixth", Math.pow(2, 9.0 / 12));
    }

    public static Map<String, Double> getIntervalRatios() {
        return intervalRatios;
    }
}
