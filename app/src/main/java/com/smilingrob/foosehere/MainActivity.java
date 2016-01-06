package com.smilingrob.foosehere;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.smilingrob.foosehere.data.RoundFile;
import com.smilingrob.foosehere.data.RoundText;
import com.smilingrob.foosehere.match.Match;
import com.smilingrob.foosehere.match.Player;
import com.smilingrob.foosehere.match.Round;
import com.smilingrob.foosehere.ui.MatchListRecyclerAdapter;
import com.smilingrob.foosehere.ui.MatchViewHolder;

import java.util.List;

/**
 * Main application activity.
 */
public class MainActivity extends AppCompatActivity implements MatchViewHolder.MatchViewActions {

    static final String BUNDLE_ROUND_TEXT = "BUNDLE_ROUND_TEXT";
    static final String ROUND_SAVE_FILENAME = "round.json";

    String roundText;
    Round round;
    MatchListRecyclerAdapter matchListRecyclerAdapter;
    RecyclerView matchListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_ROUND_TEXT)) {
            roundText = savedInstanceState.getString(BUNDLE_ROUND_TEXT);
        }

        matchListRecyclerAdapter = new MatchListRecyclerAdapter(this, this);

        matchListRecyclerView = (RecyclerView) findViewById(R.id.activity_main_match_list);
        matchListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        matchListRecyclerView.setAdapter(matchListRecyclerAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        if (roundText != null && roundText.length() > 0) {
            outState.putString(BUNDLE_ROUND_TEXT, roundText);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        RoundFile.saveRound(getApplicationContext().getFilesDir(), ROUND_SAVE_FILENAME, round);
    }

    @Override
    protected void onResume() {
        super.onResume();
        round = RoundFile.loadRound(getApplicationContext().getFilesDir(), ROUND_SAVE_FILENAME);
        refreshRoundData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.main_menu_edit_round).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showEditRoundDialog();
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onPlayerDataUpdated(Player updatedPlayer) {
        refreshRoundData();
    }

    /**
     * Update the recycler view with new round information.
     */
    private void refreshRoundData() {
        if (round != null) {
            List<Match> matches = round.getMatches();
            if (matches != null) {
                matchListRecyclerAdapter.setMatchList(matches);
                matchListRecyclerAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * Show an edit window to paste round text.
     */
    private void showEditRoundDialog() {
        final EditText editText = new EditText(MainActivity.this);
        editText.setHint(getText(R.string.round_text_hint));
        editText.setText(roundText);
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(getText(R.string.menu_edit_round))
                .setView(editText)
                .setPositiveButton(getText(R.string.round_save), new SaveRoundClickListener(editText))
                .show();
    }

    /**
     * When someone saves the round text.
     */
    private class SaveRoundClickListener implements DialogInterface.OnClickListener {
        EditText editText;

        /**
         * Save button.
         *
         * @param editText text view that holds the round text.
         */
        public SaveRoundClickListener(final EditText editText) {
            this.editText = editText;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            roundText = editText.getText().toString();
            round = RoundText.parseRoundText(roundText);
            RoundFile.saveRound(getApplicationContext().getFilesDir(), ROUND_SAVE_FILENAME, round);
            refreshRoundData();
        }
    }
}
