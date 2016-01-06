package com.smilingrob.foosehere.data;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.smilingrob.foosehere.match.Round;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Save data.
 */
public class SaveFile {

    static Gson gson = new Gson();

    /**
     * Load the round from the file.
     *
     * @param filesDir from the Android context.getFilesDir().
     * @param filename name of the save file.
     * @return The list of timers found in file.
     */
    public static Round loadRound(@NonNull File filesDir, @NonNull String filename) {
        Round round = null;
        File file = new File(filesDir, filename);
        try {
            FileReader fileReader = new FileReader(file);
            round = gson.fromJson(fileReader, Round.class);
            fileReader.close();
        } catch (FileNotFoundException ex) {
            // no problem, it's the first time they ran the app, or they just cleared cache
        } catch (IOException | JsonSyntaxException | NullPointerException ex) {
            ex.printStackTrace();
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
}
