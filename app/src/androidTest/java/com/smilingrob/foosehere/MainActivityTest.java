package com.smilingrob.foosehere;

import android.test.ActivityInstrumentationTestCase2;

import com.smilingrob.foosehere.data.RoundFile;
import com.smilingrob.foosehere.match.Match;
import com.smilingrob.foosehere.match.Player;
import com.smilingrob.foosehere.match.Round;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Test Activity logic.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    /**
     * Setup ActivityInstrumentationTestCase2.
     */
    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testLoadFile() throws Exception {
        // GIVEN an activity
        MainActivity mainActivity = getActivity();

        // AND the match data is empty
        mainActivity.round = null;
        mainActivity.roundText = null;

        // AND a valid save file
        Player player = new Player("A");
        Match match = new Match(Arrays.asList(player, player), Arrays.asList(player, player));
        List<Match> matchList = Collections.singletonList(match);
        Round emptyRound = new Round(matchList, Collections.singletonList(player));
        RoundFile.saveRound(mainActivity.getFilesDir(), MainActivity.ROUND_SAVE_FILENAME, emptyRound);

        // WHEN the round is loaded
        mainActivity.loadFromFile();

        // THEN it shouldn't crash
        //noinspection unchecked
        assertTrue(true);
    }

    public void testLoadFileEmpty() throws Exception {

        // GIVEN an activity
        MainActivity mainActivity = getActivity();

        // AND the match data is empty
        mainActivity.round = null;
        mainActivity.roundText = null;
        Round emptyRound = new Round(null, null);
        RoundFile.saveRound(mainActivity.getFilesDir(), MainActivity.ROUND_SAVE_FILENAME, emptyRound);

        // WHEN the round is loaded
        mainActivity.loadFromFile();

        // THEN it shouldn't crash
        // AND the adapter shouldn't change
        assertTrue(true);
    }
}