package oop.horsematching;

/**
 *  Data class for a horses name and its difficulty level
 */
public class Horse {

    /**
     * Constructor for Horse
     * @param name name of horse
     * @param difficulty difficulty level to ride
     */
    public Horse(String name, int difficulty) {

        this.name = name;
        this.difficulty = difficulty;
    }

    /**
     * difficulty level to ride
     */
    private int difficulty;

    /**
     * name of horse
     */
    private String name;


    /**
     * getter for difficulty
     * @return difficulty difficulty level to ride
     */
    public int getDifficulty() {

        return difficulty;
    }

    /**
     * setter for difficulty
     * @param difficulty difficulty level to ride
     */
    public void setDifficulty(int difficulty) {

        this.difficulty = difficulty;
    }

    /**
     * getter for name
     * @return name name of horse
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name
     * @param name name of horse
     */
    public void setName(String name) {
        this.name = name;
    }
}