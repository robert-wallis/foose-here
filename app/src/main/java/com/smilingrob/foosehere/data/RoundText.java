package com.smilingrob.foosehere.data;

import com.smilingrob.foosehere.match.Match;
import com.smilingrob.foosehere.match.Player;
import com.smilingrob.foosehere.match.Round;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Transform a text block into a match list.
 */
public class RoundText {

    /**
     * Get an object representation of the match list provied by Hexar.
     *
     * @param roundText match list copy pasted from Hexar.
     * @return list of matches.
     */
    public static Round parseRoundText(String roundText) {

        if (roundText == null || roundText.length() == 0) {
            return null;
        }

        ArrayList<Match> matchList = new ArrayList<>();
        Map<String, Player> playerMap = new HashMap<>();

        String[] matchesText = roundText.split("\n");
        for (String matchText : matchesText) {
            String[] teamsText = matchText.split("vs\\.");
            if (teamsText.length != 2) {
                continue;
            }

            ArrayList<Player> team1List = new ArrayList<>();
            ArrayList<Player> team2List = new ArrayList<>();

            int teamNumber = 1;
            for (String teamText : teamsText) {
                String[] names = teamText.split("-");
                for (String rawName : names) {
                    String name = rawName.trim();
                    Player player;
                    // re-use player objects, so we can check availability
                    if (playerMap.containsKey(name)) {
                        player = playerMap.get(name);
                    } else {
                        player = new Player(name);
                        playerMap.put(name, player);
                    }
                    // hardcoded 1,2 because all matches are 2 teams
                    if (teamNumber == 1) {
                        team1List.add(player);
                    } else if (teamNumber == 2) {
                        team2List.add(player);
                    }
                }
                teamNumber++;
            }
            Match match = new Match(team1List, team2List);
            matchList.add(match);
        }

        ArrayList<Player> players = new ArrayList<>(playerMap.values());
        return new Round(matchList, players);
    }

}
