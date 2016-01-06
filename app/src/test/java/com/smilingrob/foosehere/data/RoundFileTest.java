package com.smilingrob.foosehere.data;

import com.smilingrob.foosehere.match.Player;

import junit.framework.TestCase;

import java.util.Collections;
import java.util.List;

/**
 * Saving the Round file to JSON.
 */
public class RoundFileTest extends TestCase {

    public void testReplacePlayerObjects() throws Exception {
        // GIVEN a list of players and a team
        List<Player> players = Collections.singletonList(new Player("A"));
        List<Player> team = Collections.singletonList(new Player("A"));

        assertNotSame(players.get(0), team.get(0));

        // WHEN when we consolidate the memory objects, they should be consolidated
        List<Player> newTeam = RoundFile.replacePlayerObjects(players, team);
        assertSame(players.get(0), newTeam.get(0));
    }
}