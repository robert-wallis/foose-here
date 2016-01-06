package com.smilingrob.foosehere.match;

import junit.framework.TestCase;

/**
 * Test logic in the Player model.
 */
public class PlayerTest extends TestCase {

    public void testEquals() throws Exception {
        // GIVEN two player instances with the same name
        Player a = new Player("a");
        Player b = new Player("a");

        // THEN both players should be equal.
        assertTrue(a.equals(b));
    }

    public void testEqualsBadData() throws Exception {
        // GIVEN bad data
        assertTrue(new Player(null).equals(new Player(null)));
        //noinspection ObjectEqualsNull
        assertFalse(new Player(null).equals(null));
        assertFalse(new Player(null).equals(new Player("a")));
        assertFalse(new Player("a").equals(new Player(null)));
    }
}