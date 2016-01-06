package com.smilingrob.foosehere.data;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.smilingrob.foosehere.match.Match;
import com.smilingrob.foosehere.match.Player;
import com.smilingrob.foosehere.match.Round;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Save data.
 */
public class RoundFile {

    static Gson gson = new Gson();

    /**
     * Load the round from the file.
     *
     * @param filesDir from the Android context.getFilesDir().
     * @param filename name of the save file.
     * @return The list of timers found in file.
     */
    public static Round loadRound(@NonNull File filesDir, @NonNull String filename) {
        Round round;
        File file = new File(filesDir, filename);
        try {
            FileReader fileReader = new FileReader(file);
            round = gson.fromJson(fileReader, Round.class);
            fileReader.close();
        } catch (FileNotFoundException ex) {
            // no problem, it's the first time they ran the app, or they just cleared cache
            return null;
        } catch (IOException | JsonSyntaxException | NullPointerException ex) {
            ex.printStackTrace();
            return null;
        }

        // re-link all the match players with the players
        for (Match match : round.getMatches()) {
            List<Player> team1 = replacePlayerObjects(round.getPlayers(), match.getTeamOne());
            match.setTeamOne(team1);

            List<Player> team2 = replacePlayerObjects(round.getPlayers(), match.getTeamOne());
            match.setTeamTwo(team2);
        }

        return round;
    }

    /**
     * Save the round to a file.
     *
     * @param filesDir from the Android context.getFilesDir().
     * @param filename name of the round file.
     * @param round    round to save.
     */
    public static void saveRound(@NonNull File filesDir, @NonNull String filename, @NonNull Round round) {
        File file = new File(filesDir, filename);
        try {
            FileWriter fileWriter = new FileWriter(file);
            String json = gson.toJson(round);
            fileWriter.write(json);
            fileWriter.close();
        } catch (FileNotFoundException ex) {
            // no problem, it's the first time they ran the app, or they just cleared cache
        } catch (IOException | JsonSyntaxException | NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Replace the players in the destination that match the players in the source, with the source player objects.
     *
     * @param source      full player list.
     * @param destination team list.
     * @return new list of destination players with source objects.
     */
    static List<Player> replacePlayerObjects(List<Player> source, List<Player> destination) {
        if (source != null) {
            ArrayList<Player> newDest = new ArrayList<>();
            for (Player realPlayer : source) {
                for (Player destPlayer : destination) {
                    if (destPlayer.equals(realPlayer)) {
                        newDest.add(realPlayer);
                    }
                }
            }
            return newDest;
        } else {
            return destination;
        }
    }
}
