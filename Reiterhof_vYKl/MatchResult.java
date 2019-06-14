package oop.horsematching;

import java.util.HashMap;
import java.util.Map;

/**
 *  Data class that holds the result of a matching and its score
 *  it can be recursively passed up/down
 */
public class MatchResult {

    /**
     * Creates an empty match with score 0
     */
    public MatchResult() {

        map = new HashMap<>();
        score = 0;
    }

    /**
     *  The score of a match (better when higher)
     */
    private int score;

    /**
     *  Map that stores the matched pairs
     */
    private Map<Rider, Horse> map;


    /**
     * @return the score of this match
     */
    public int getScore() {

        return score;
    }

    /**
     * @param score the score to set to
     */
    public void setScore(int score) {

        this.score = score;
    }

    /**
     * increases the score by one
     */
    public void increaseScore() {

        score++;
    }

    /**
     * @return the matched pairs
     */
    public Map<Rider, Horse> getMap() {

        return map;
    }

    /**
     * @param map the new map
     */
    public void setMap(Map<Rider, Horse> map) {

        this.map = map;
    }

    /**
     * Formats the match so it can be printed out
     *
     * @return the formatted output
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("===Best match:===\n");
        for (Map.Entry<Rider, Horse> match : map.entrySet()) {
            builder.append(String.format(" %10s -> %-10s\n", match.getKey().getName(), match.getValue().getName()));
        }
        builder.append(String.format("Score: %d", score));
        return builder.toString();
    }
}