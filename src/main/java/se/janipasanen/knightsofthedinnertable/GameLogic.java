package se.janipasanen.knightsofthedinnertable;

import com.google.common.collect.Collections2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GameLogic {
    void run() {
        String[] input = getInput().split(".\n");
        // Hold all the people
        HashSet<String> allPeople = new HashSet<>();
        // Hold the happiness links between everyone
        HashMap<String, Integer> happiness = new HashMap<>();
        for (String line : input) {
            String[] tokens = line.split(" ");
            // Check if it's a gain or loss in happiness
            int mult = tokens[2].equals("gain") ? 1 : -1;
            allPeople.add(tokens[0]);
            allPeople.add(tokens[10]);
            // Create key value pairs of 'name1name2' : weight
            happiness.put(tokens[0] + tokens[10], Integer.parseInt(tokens[3]) * mult);
        }
        // True: Part 1 || False: Part 2
      //  if (true) {
        //    allPeople.remove("me");
        //} else {
            allPeople.add("me");
            for (String people : allPeople) {
                happiness.put(people + "me", 0);
                happiness.put("me" + people, 0);
            }
      //  }
        int max = 0;

        // Get permumations / The names in different positions in the list to test all possible combinations of positions.
        for (List<String> perm : Collections2.permutations(allPeople)) {
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
        System.out.println("Total change in happiness for the optimal seating arrangement of the actual guest list if including me: " + max);
    }


    String getInput() {
        return new Data().getData();
    }
}
