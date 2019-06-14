package oop.horsematching;

import java.util.List;

/**
 * Data class for a riders name, difficulty and wished horses
 */
public class Rider {

    /**
     * Constructor for Rider
     * @param name name of rider
     * @param difficulty difficulty level of rider
     * @param wishedHorses list of horses rider wants to ride
     */
    public Rider(String name, int difficulty, List<Horse> wishedHorses) {

        this.name = name;
        this.difficulty = difficulty;
        this.wishedHorses = wishedHorses;
    }

    /**
     * difficulty level of rider
     */
    private int difficulty;

    /**
     * list of horses rider wants to ride
     */
    private List<Horse> wishedHorses;

    /**
     * name of rider
     */
    private String name;


    /**
     * getter for difficulty
     * @return difficulty level of rider
     */
    public int getDifficulty() {

        return difficulty;
    }

    /**
     * setter for difficulty
     * @param difficulty difficulty level of rider
     */
    public void setDifficulty(int difficulty) {

        this.difficulty = difficulty;
    }

    /**
     * getter for wishedHorses
     * @return list of horses rider wants to ride
     */
    public List<Horse> getWishedHorses() {

        return wishedHorses;
    }

    /**
     * setter for wishedHorses
     * @param wishedHorses list of horses rider wants to ride
     */
    public void setWishedHorses(List<Horse> wishedHorses) {

        this.wishedHorses = wishedHorses;
    }
    
    /**
     * getter for name
     * @return name of rider
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name
     * @param name name of rider
     */
    public void setName(String name) {
        this.name = name;
    }
}