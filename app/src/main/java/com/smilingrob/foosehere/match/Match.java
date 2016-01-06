package com.smilingrob.foosehere.match;

import java.util.List;

/**
 * Set of players that play a game.
 */
public class Match {

    boolean isMatchPlayed = false;
    List<Player> teamOne;
    List<Player> teamTwo;

    /**
     * Tournament match.
     *
     * @param teamOne players in one team.
     * @param teamTwo playesr in the other team.
     */
    public Match(List<Player> teamOne, List<Player> teamTwo) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }

    /**
     * Calculate a team name based on player names.
     *
     * @param team list of players in the team.
     * @return tean name.
     */
    private static String teamNameFromList(List<Player> team) {
        String name = "";
        int playerId = 0;
        for (Player player : team) {
            if (playerId > 0) {
                name += " - ";
            }
            name += player.getName();
            playerId++;
        }
        return name;
    }

    /**
     * Return the name of the first team.
     *
     * @return team name.
     */
    public String teamOneName() {
        return teamNameFromList(teamOne);
    }

    /**
     * Return the name of the second team.
     *
     * @return team name.
     */
    public String teamTwoName() {
        return teamNameFromList(teamTwo);
    }

    /**
     * Has the match been played?
     *
     * @return true if the match was played.
     */
    public boolean isMatchPlayed() {
        return isMatchPlayed;
    }

    public void setIsMatchPlayed(boolean isMatchPlayed) {
        this.isMatchPlayed = isMatchPlayed;
    }

    /**
     * @return true if all players in the match are available to play.
     */
    public boolean areAllPlayersAvailable() {
        if (teamOne != null && teamTwo != null) {
            for (Player p : teamOne) {
                if (!p.isHere()) {
                    return false;
                }
            }
            for (Player p : teamTwo) {
                if (!p.isHere()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public List<Player> getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(List<Player> teamOne) {
        this.teamOne = teamOne;
    }

    public List<Player> getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(List<Player> teamTwo) {
        this.teamTwo = teamTwo;
    }

    @Override
    public String toString() {
        return "Match{" + teamOne +
                " vs. " + teamTwo +
                '}';
    }
}
