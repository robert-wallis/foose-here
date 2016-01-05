package com.smilingrob.foosehere.data;

import com.smilingrob.foosehere.match.Match;

import junit.framework.TestCase;

import java.util.List;

/**
 * Test text to round parsing.
 */
public class TextToMatchTest extends TestCase {

    static final String ROUND_TEXT = "Becca-Daniel vs. Kennedy-Robert\n" +
            "Darin-Stephen vs. Phil-Terrill\n" +
            "Becca-Hexar vs. Daniel-Kennedy\n" +
            "Darin-Hexar vs. Robert-Terrill\n" +
            "Darin-Robert vs. Phil-Stephen\n" +
            "Becca-Terrill vs. Kennedy-Phil\n" +
            "Daniel-Darin vs. Hexar-Stephen\n" +
            "Hexar-Phil vs. Robert-Stephen\n" +
            "Becca-Kennedy vs. Daniel-Terrill\n" +
            "Becca-Darin vs. Hexar-Terrill\n" +
            "Daniel-Robert vs. Kennedy-Stephen\n" +
            "Becca-Robert vs. Darin-Phil";

    public void testParseRoundText() throws Exception {
        // GIVEN a list of players that Hexar generated
        // WHEN it is parsed
        List<Match> matches = TextToMatch.parseRoundText(ROUND_TEXT);

        // THEN it should be valid
        assertNotNull(matches);
        assertEquals("All 12 were not parsed, did you forget a newline?", 12, matches.size());

        // THEN matches should have the right players.
        assertEquals("Darin", matches.get(1).getTeamOne().get(0).getmName());
        assertEquals("Terrill", matches.get(3).getTeamTwo().get(1).getmName());

        // THEN the player objects should be re-used in different matches
        assertEquals(matches.get(1).getTeamOne().get(0), matches.get(11).getTeamTwo().get(0));
    }
}