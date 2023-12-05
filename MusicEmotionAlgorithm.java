import java.util.Map;
import java.util.Scanner;

public class MusicEmotionAlgorithm {

    private static final Map<String, Double> intervalRatios = IntervalRatios.getIntervalRatios();
    private static final Map<String, String[]> emotionMappings = EmotionMappings.getEmotionMappings();
    private static final String[] notes = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputNote = new String();

        System.out.println("Enter a musical note (e.g., C4):");
        inputNote = scanner.nextLine();

        while (!inputNote.equals("exit")) {
            System.out.println("Enter a desired emotion (happy, sad, tense, relaxed, mysterious, heroic):");
            String emotion = scanner.nextLine();

            String[] result = findNextNoteAndChord(inputNote, emotion);
            System.out.println("Next Note: " + result[0] + ", Suggested Chord: " + result[1]);

            System.out.println("Enter a musical note (e.g., C4) or 'exit' to quit:");
            inputNote = scanner.nextLine();
        }
    }

    private static String[] findNextNoteAndChord(String note, String emotion) {
        String[] intervalAndChord = emotionMappings.getOrDefault(emotion.toLowerCase(), new String[] { "", "" });
        double frequency = getFrequency(note);
        double nextFrequency = frequency * intervalRatios.getOrDefault(intervalAndChord[0], 1.0);
        String nextNote = getNoteFromFrequency(nextFrequency);
        return new String[] { nextNote, intervalAndChord[1] };
    }

    private static double getFrequency(String note) {
        int noteIndex = getNoteIndex(note.substring(0, note.length() - 1));
        int octave = Character.getNumericValue(note.charAt(note.length() - 1));
        return 440 * Math.pow(2, (noteIndex + (octave - 4) * 12) / 12.0);
    }

    private static String getNoteFromFrequency(double frequency) {
        int keyNumber = (int) Math.round(12 * logBase2(frequency / 440));
        int index = keyNumber % 12;
        int octave = 4 + (keyNumber / 12);
        return notes[index] + octave;
    }

    private static int getNoteIndex(String note) {
        for (int i = 0; i < notes.length; i++) {
            if (notes[i].equals(note)) {
                return i;
            }
        }
        return -1; // Note not found
    }

    private static double logBase2(double value) {
        return Math.log(value) / Math.log(2);
    }
}
