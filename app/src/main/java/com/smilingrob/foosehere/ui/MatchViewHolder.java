package com.smilingrob.foosehere.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.smilingrob.foosehere.R;
import com.smilingrob.foosehere.match.Match;
import com.smilingrob.foosehere.match.Player;

/**
 * Holds a single match view.
 */
public class MatchViewHolder extends RecyclerView.ViewHolder {

    MatchViewActions matchViewActionListener;

    boolean isBinding = false;

    /**
     * Represent a single match view.
     *
     * @param itemView                inflated view to bind data into.
     * @param matchViewActionListener listen for data changes.
     */
    public MatchViewHolder(View itemView, MatchViewActions matchViewActionListener) {
        super(itemView);
        this.matchViewActionListener = matchViewActionListener;
    }

    /**
     * Fill the match view with the match data.
     *
     * @param match the match data.
     */
    public void onBindView(final Match match) {
        isBinding = true;

        CheckBox played = (CheckBox) itemView.findViewById(R.id.match_item_check);
        Switch player1 = (Switch) itemView.findViewById(R.id.match_item_team_1_player_0);
        Switch player2 = (Switch) itemView.findViewById(R.id.match_item_team_1_player_1);
        Switch player3 = (Switch) itemView.findViewById(R.id.match_item_team_2_player_0);
        Switch player4 = (Switch) itemView.findViewById(R.id.match_item_team_2_player_1);

        played.setChecked(match.isMatchPlayed());
        played.setOnCheckedChangeListener(new MatchPlayedCheckListener(match));

        bindPlayerToToggleButton(player1, match.getTeamOne().get(0));
        bindPlayerToToggleButton(player2, match.getTeamOne().get(1));
        bindPlayerToToggleButton(player3, match.getTeamTwo().get(0));
        bindPlayerToToggleButton(player4, match.getTeamTwo().get(1));

        int backgroundColor;
        if (match.areAllPlayersAvailable()) {
            //noinspection deprecation
            backgroundColor = itemView.getResources().getColor(R.color.matchAvailableColor);
        } else {
            //noinspection deprecation
            backgroundColor = itemView.getResources().getColor(R.color.matchInactiveColor);
        }
        itemView.setBackgroundColor(backgroundColor);
        isBinding = false;
    }

    /**
     * Make sure the toggle button has all the data and actions set up.
     *
     * @param playerSwitch toggle button for the player.
     * @param player       player data.
     */
    private void bindPlayerToToggleButton(Switch playerSwitch, Player player) {
//        playerSwitch.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        playerSwitch.setText(player.getName());
        playerSwitch.setChecked(player.isHere());
        playerSwitch.setOnCheckedChangeListener(new PlayerNameCheckListener(player));
    }

    /**
     * Actions that can happen in a MatchViewHolder.
     */
    public interface MatchViewActions {
        /**
         * When the player data has been changed.
         *
         * @param updatedPlayer player who's data has changed.
         */
        void onPlayerDataUpdated(Player updatedPlayer);
    }

    /**
     * When the round is played or not.
     */
    class MatchPlayedCheckListener implements CheckBox.OnCheckedChangeListener {

        final Match match;

        /**
         * @param match to change the data if the match was played.
         */
        public MatchPlayedCheckListener(final Match match) {
            this.match = match;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!isBinding) {
                match.setIsMatchPlayed(isChecked);
            }
        }
    }

    /**
     * When the player name is tapped.
     */
    class PlayerNameCheckListener implements ToggleButton.OnCheckedChangeListener {

        Player player;

        /**
         * @param player player model for the player.
         */
        public PlayerNameCheckListener(Player player) {
            this.player = player;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!isBinding) {
                player.setIsHere(isChecked);
                if (matchViewActionListener != null) {
                    matchViewActionListener.onPlayerDataUpdated(player);
                }
            }
        }
    }
}
