import java.util.HashMap;
import java.util.Map;

public class EmotionMappings {
    private static final Map<String, String[]> emotionMappings = new HashMap<>();

    static {
        emotionMappings.put("happy", new String[] { "major third", "major" });
        emotionMappings.put("sad", new String[] { "minor third", "minor" });
        emotionMappings.put("tense", new String[] { "tritone", "diminished" });
        emotionMappings.put("relaxed", new String[] { "perfect fifth", "major" });
        emotionMappings.put("mysterious", new String[] { "minor sixth", "minor" });
        emotionMappings.put("heroic", new String[] { "major sixth", "major" });
    }

    public static Map<String, String[]> getEmotionMappings() {
        return emotionMappings;
    }
}
