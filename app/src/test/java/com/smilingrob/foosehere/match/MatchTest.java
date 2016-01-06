package com.smilingrob.foosehere.match;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

/**
 * Test Match model's logic.
 */
public class MatchTest extends TestCase {

    public void testTeamOneName() throws Exception {
        // GIVEN a match
        List<Player> team1 = Arrays.asList(new Player("one"), new Player("two"));
        List<Player> team2 = Arrays.asList(new Player("three"), new Player("four"));
        Match match = new Match(team1, team2);

        // WHEN the team name is generated
        String teamName = match.teamOneName();

        // THEN it should be the expected name
        assertEquals("one - two", teamName);
    }

    public void testTeamTwoName() throws Exception {
        // GIVEN a match
        List<Player> team1 = Arrays.asList(new Player("one"), new Player("two"));
        List<Player> team2 = Arrays.asList(new Player("three"), new Player("four"));
        Match match = new Match(team1, team2);

        // WHEN the team name is generated
        String teamName = match.teamTwoName();

        // THEN it should be the expected name
        assertEquals("three - four", teamName);
    }

    public void testAreAllPlayersAvailable() throws Exception {
        // GIVEN a valid match
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        Player p4 = new Player("four");
        List<Player> team1 = Arrays.asList(p1, p2);
        List<Player> team2 = Arrays.asList(p3, p4);
        Match match = new Match(team1, team2);

        // WHEN all players are available
        p1.setIsHere(true);
        p2.setIsHere(true);
        p3.setIsHere(true);
        p4.setIsHere(true);

        // THEN the match should know all players are available.
        assertTrue(match.areAllPlayersAvailable());
    }

    public void testAreAllPlayersAvailableNotHere() throws Exception {
        // GIVEN a valid match
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        Player p3 = new Player("three");
        Player p4 = new Player("four");
        List<Player> team1 = Arrays.asList(p1, p2);
        List<Player> team2 = Arrays.asList(p3, p4);
        Match match = new Match(team1, team2);

        // WHEN one player is unavailable
        p1.setIsHere(false);
        p2.setIsHere(true);
        p3.setIsHere(true);
        p4.setIsHere(true);

        // THEN the match should know all players are unavailable.
        assertFalse(match.areAllPlayersAvailable());

        // WHEN one player is unavailable
        p1.setIsHere(true);
        p2.setIsHere(false);
        p3.setIsHere(true);
        p4.setIsHere(true);

        // THEN the match should know all players are unavailable.
        assertFalse(match.areAllPlayersAvailable());

        // WHEN one player is unavailable
        p1.setIsHere(true);
        p2.setIsHere(true);
        p3.setIsHere(false);
        p4.setIsHere(true);

        // THEN the match should know all players are unavailable.
        assertFalse(match.areAllPlayersAvailable());

        // WHEN one player is unavailable
        p1.setIsHere(true);
        p2.setIsHere(true);
        p3.setIsHere(true);
        p4.setIsHere(false);

        // THEN the match should know all players are unavailable.
        assertFalse(match.areAllPlayersAvailable());
    }

    public void testAreAllPlayersAvailableBadData() throws Exception {
        // GIVEN an invalid match
        Match match = new Match(null, null);

        // THEN the match should know all players are unavailable.
        assertFalse(match.areAllPlayersAvailable());

        // GIVEN a semi-valid match
        Player p1 = new Player("one");
        Player p2 = new Player("two");
        List<Player> team1 = Arrays.asList(p1, p2);
        Match match1 = new Match(team1, null);

        // THEN the match should know all players are unavailable.
        assertFalse(match1.areAllPlayersAvailable());

        // GIVEN a semi-valid match
        Match match2 = new Match(null, team1);

        // THEN the match should know all players are unavailable.
        assertFalse(match2.areAllPlayersAvailable());

        // GIVEN a semi-valid match
        List<Player> halfTeam = Arrays.asList(p1, null);
        Match match3 = new Match(halfTeam, null);

        // THEN the match should know all players are unavailable.
        assertFalse(match3.areAllPlayersAvailable());

        // GIVEN a semi-valid match
        Match match4 = new Match(null, halfTeam);

        // THEN the match should know all players are unavailable.
        assertFalse(match4.areAllPlayersAvailable());
    }
}