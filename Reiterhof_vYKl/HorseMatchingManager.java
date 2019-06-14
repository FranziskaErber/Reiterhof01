package oop.horsematching;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Manages the matching of horses to riders, including in- and output
 */
public class HorseMatchingManager {

    /**
     * generates a new Manager Instance
     */
    public HorseMatchingManager() {
        horses = new ArrayList<Horse>();
        riders = new ArrayList<Rider>();
    }

    /**
     * available horses to match
     */
    private ArrayList<Horse> horses;

    /**
     * available riders to match
     */
    private ArrayList<Rider> riders;

    /**
     * Asks the user at witch paths the config files are stored
     */
    public void scanPaths() {

        boolean error = true;
        while (error) {
            IOHelper.Console.sendText("Please enter the path to your horses config file:");
            String horsePath = IOHelper.Console.getFilePath();
            IOHelper.Console.sendText("Please enter the path to your riders config file:");
            String riderPath = IOHelper.Console.getFilePath();
            try {
                loadData(riderPath, horsePath);
                error = false;

            } catch (IOException | MalformedConfigException e) {
                IOHelper.Console.sendText("Error reading the config files: "+e.getMessage());
            }
        }

        IOHelper.Console.sendText("Finding best match...");
        MatchResult mr = match(riders, horses);

        IOHelper.Console.sendText(mr.toString());
    }

    /**
     * Loads the files from the given paths into horseList and riderList
     *
     * @param riderPath the path to the rider config file
     * @param horsePath the path to the horse config file
     * @throws MalformedConfigException malformed config
     * @throws IOException io error
     */
    public void loadData(String riderPath, String horsePath) throws MalformedConfigException, IOException {


        {
            File sourceFile = new File(horsePath);
            FileInputStream fis = new FileInputStream(sourceFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length != 2) {
                    throw new MalformedConfigException("Malformed horse config file");
                }
                Horse horse = new Horse(attributes[0], Integer.parseInt(attributes[1]));
                horses.add(horse);
            }
            br.close();
        }

        {
            File sourceFile = new File(riderPath);
            FileInputStream fis = new FileInputStream(sourceFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                if (attributes.length < 2) {
                    throw new IOException("Malformed riders config file");
                }
                List<Horse> wishedHorses = new ArrayList<Horse>();
                for (int i = 2; i < attributes.length; i++) {
                    boolean found = false;
                    for (Horse h : horses) {
                        if (h.getName().equals(attributes[i])) {
                            wishedHorses.add(h);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        throw new IOException("Malformed riders config file: horsename not found(" + attributes[i] + ")");
                    }
                }
                Rider rider = new Rider(attributes[0], Integer.parseInt(attributes[1]), wishedHorses);
                riders.add(rider);
            }
            br.close();
        }
    }

    /**
     * @param riderList riders to match
     * @param horseList available horses
     * @return the best match and a score of it
     */
    public MatchResult match(List<Rider> riderList, List<Horse> horseList) {

        //System.out.println("riderSize: "+riderList.size()+" horseSize: "+horseList.size());
        MatchResult result = new MatchResult();
        for (Rider rider : riderList) {
            for (Horse horse : horseList) {

                if (rider.getDifficulty() >= horse.getDifficulty()) {

                    List<Rider> new_riderList = new ArrayList<>(riderList.size());
                    new_riderList.addAll(riderList);
                    new_riderList.remove(rider);

                    List<Horse> new_horseList = new ArrayList<>(horseList.size());
                    new_horseList.addAll(horseList);
                    new_horseList.remove(horse);

                    //System.out.println("riderDiff: "+(new_riderList.size()-riderList.size())+" horseSize: "+(new_horseList.size()-horseList.size()));
                    MatchResult new_result = match(new_riderList, new_horseList);

                    new_result.getMap().put(rider, horse);
                    new_result.increaseScore();
                    if (rider.getWishedHorses().contains(horse)) {
                        new_result.increaseScore();
                    }

                    if (new_result.getScore() > result.getScore()) {
                        result = new_result;
                    }
                }
            }

        }
        return result;
    }
}