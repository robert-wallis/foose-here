package com.smilingrob.foosehere.data;

import com.smilingrob.foosehere.match.Player;
import com.smilingrob.foosehere.match.Round;

import junit.framework.TestCase;

/**
 * Test text to round parsing.
 */
public class RoundTextTest extends TestCase {

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
        Round round = RoundText.parseRoundText(ROUND_TEXT);

        // THEN it should be valid
        assertNotNull(round);
        assertNotNull(round.getMatches());

        assertEquals("All 12 were not parsed, did you forget a newline?", 12, round.getMatches().size());

        // THEN matches should have the right players.
        assertEquals("Darin", round.getMatches().get(1).getTeamOne().get(0).getName());
        assertEquals("Terrill", round.getMatches().get(3).getTeamTwo().get(1).getName());
    }

    public void testParseSameObjects() throws Exception {
        // GIVEN a list of players that Hexar generated
        // WHEN it is parsed
        Round round = RoundText.parseRoundText(ROUND_TEXT);


        // THEN the player objects should be re-used in different matches
        assertEquals(
                round.getMatches().get(1).getTeamOne().get(0).hashCode(),
                round.getMatches().get(11).getTeamTwo().get(0).hashCode()
        );
        for (Player player : round.getPlayers()) {
            if (player.getName().equals("Robert")) {
                assertEquals(player.hashCode(), round.getMatches().get(0).getTeamTwo().get(1).hashCode());
                break;
            }
        }
    }
}