package com.smilingrob.foosehere.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.smilingrob.foosehere.R;
import com.smilingrob.foosehere.match.Match;

/**
 * Holds a single match view.
 */
class MatchViewHolder extends RecyclerView.ViewHolder {

    /**
     * Represent a single match view.
     *
     * @param itemView inflated view to bind data into.
     */
    public MatchViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * Fill the match view with the match data.
     *
     * @param match the match data.
     */
    public void onBindView(final Match match) {
        CheckBox played = (CheckBox) itemView.findViewById(R.id.match_item_check);
        TextView teamOne = (TextView) itemView.findViewById(R.id.match_item_team_one);
        TextView teamTwo = (TextView) itemView.findViewById(R.id.match_item_team_two);
        played.setChecked(match.isMatchPlayed());
        played.setOnCheckedChangeListener(new PlayedCheckChangeListener(match));
        teamOne.setText(match.teamOneName());
        teamTwo.setText(match.teamTwoName());
    }

    /**
     * When the round is played or not.
     */
    class PlayedCheckChangeListener implements CheckBox.OnCheckedChangeListener {

        final Match match;

        /**
         * @param match to change the data if the match was played.
         */
        public PlayedCheckChangeListener(final Match match) {
            this.match = match;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            match.setIsMatchPlayed(isChecked);
        }
    }
}
