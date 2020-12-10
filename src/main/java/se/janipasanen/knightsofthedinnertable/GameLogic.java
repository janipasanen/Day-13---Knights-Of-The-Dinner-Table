package se.janipasanen.knightsofthedinnertable;

import com.google.common.collect.Collections2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GameLogic {
    void run() {
        String[] input = getInput().split(".\n");
        HashSet<String> people = new HashSet<>(); // Hold all the people
        HashMap<String, Integer> happiness = new HashMap<>(); // Hold the happiness links between everyone
        for (String line : input) {
            String[] tokens = line.split(" ");
            int mult = tokens[2].equals("gain") ? 1 : -1; // Check if it's a gain or loss in happiness
            people.add(tokens[0]);
            people.add(tokens[10]);
            happiness.put(tokens[0] + tokens[10], Integer.parseInt(tokens[3]) * mult); // Create key value pairs of 'name1name2' : weight
        }
        // True: Part 1 || False: Part 2
        if (true) {
            people.remove("me");
        } else {
            people.add("me");
            for (String peep : people) {
                happiness.put(peep + "me", 0);
                happiness.put("me" + peep, 0);
            }
        }
        int max = 0;

        for (List<String> perm : Collections2.permutations(people)) { // Get all permumations
            int total = 0;
            for (int i = 0; i < perm.size() - 1; i++) {
                total += happiness.get(perm.get(i) + perm.get(i + 1)) + happiness.get(perm.get(i + 1) + perm.get(i));
            }
            // Account for ends of table.
            total += happiness.get(perm.get(0) + perm.get(perm.size() - 1)) + happiness.get(perm.get(perm.size() - 1) + perm.get(0));
            if (total > max) {
                max = total;
            }
        }
        System.out.println("Max happy: " + max);
    }


    String getInput() {
        return new Data().getData();
    }
}
