# Music-Emotion-Assistant
A Music Emotion assistant for new music composers and producers assist with the creation of melodies and chord progressions.

The program suggests the next musical note and an appropriate chord that aligns with the specified emotion. This application is built on the intriguing concept that certain musical intervals and chords can evoke specific emotional responses.

The core functionality of the program is encapsulated in the MusicEmotionAlgorithm class. It leverages predefined mappings between emotions and musical intervals, as well as between emotions and chord types, stored in separate classes (EmotionMappings and IntervalRatios). These mappings are based on common musical theory principles that associate specific feelings with certain harmonic structures.

When run, the program prompts users to enter a musical note (like "B3") and a desired emotion from a set list (such as "happy," "sad," "tense," etc.). Based on this input, it calculates the frequency of the next note that would typically evoke the chosen emotion and also suggests a chord type that typically complements the emotional context.

