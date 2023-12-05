import java.util.Map;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MusicEmotionAlgorithm {

    private static final Map<String, Double> intervalRatios = IntervalRatios.getIntervalRatios();
    private static final Map<String, String[]> emotionMappings = EmotionMappings.getEmotionMappings();
    private static final String[] notes = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };
    private static final String[] emotions = { "Happy", "Sad", "Tense", "Relaxed", "Mysterious", "Heroic" };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputNote = new String();

        try {
            System.out.println("Enter a musical note (e.g., C) or 'exit' to quit:");
            inputNote = scanner.nextLine();
            while (!inputNote.equalsIgnoreCase("exit")) {

                if (!isValidNote(inputNote)) {
                    System.out.println("Incorrect Note input.");
                    scanner.next();
                    continue;
                }
                System.out.println("Enter a desired emotion (Happy, Sad, Tense, Relaxed, Mysterious, Heroic):");
                String emotion = scanner.nextLine();

                if (!isValidEmotion(emotion)) {
                    System.out.println("Incorrect Emotion input.");
                    scanner.next();
                    continue;
                }

                String[] result = findNextNoteAndChord(inputNote, emotion);
                System.out.println("Next Note: " + result[0] + ", Suggested Chord: " + result[1]);
                inputNote.equals(result[0]);

            }
        } catch (InputMismatchException ime) {
            System.err.println("Input error: Please enter the correct format.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
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
        int octave;
        octave = Character.getNumericValue(note.charAt(note.length() - 1));
        if (octave < 0 || octave >= 8) {
            octave = 5;
        }
        return 440 * Math.pow(2, (noteIndex + (octave - 4) * 12) / 12.0);
    }

    private static String getNoteFromFrequency(double frequency) {
        int keyNumber = (int) Math.round(12 * logBase2(frequency / 440));
        int index = keyNumber % 12;
        return notes[index];
    }

    private static int getNoteIndex(String note) {
        for (int i = 0; i < notes.length; i++) {
            if (notes[i].equals(note)) {
                return i;
            }
        }
        return -1; // Note not found
    }

    private static boolean isValidNote(String note) {
        for (String validNote : notes) {
            if (validNote.equals(note)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidEmotion(String emotion) {
        for (String validEmotion : emotions) {
            if (validEmotion.equals(emotion)) {
                return true;
            }
        }
        return false;
    }

    private static double logBase2(double value) {
        return Math.log(value) / Math.log(2);
    }
}
