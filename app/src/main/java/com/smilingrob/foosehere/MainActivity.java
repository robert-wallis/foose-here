package com.smilingrob.foosehere;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.smilingrob.foosehere.data.SaveFile;
import com.smilingrob.foosehere.data.TextToMatch;
import com.smilingrob.foosehere.match.Match;
import com.smilingrob.foosehere.match.Round;
import com.smilingrob.foosehere.ui.MatchListRecyclerAdapter;

import java.util.List;

/**
 * Main application activity.
 */
public class MainActivity extends AppCompatActivity {

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

        matchListRecyclerAdapter = new MatchListRecyclerAdapter(this);

        findViewById(R.id.activity_main_edit_round).setOnClickListener(new EditRoundClickListener());
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
        SaveFile.saveRound(getApplicationContext().getFilesDir(), ROUND_SAVE_FILENAME, round);
    }

    @Override
    protected void onResume() {
        super.onResume();
        round = SaveFile.loadRound(getApplicationContext().getFilesDir(), ROUND_SAVE_FILENAME);
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
                matchListRecyclerView.setAdapter(matchListRecyclerAdapter);
            }
        }
    }

    /**
     * When someone wants to edit the list of matches.
     */
    private class EditRoundClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            final EditText editText = new EditText(MainActivity.this);
            editText.setHint("round text");
            editText.setText(roundText);
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("Edit Round")
                    .setMessage("Paste text from Hexar")
                    .setView(editText)
                    .setPositiveButton("Save", new SaveRoundClickListener(editText))
                    .show();
        }
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
            round = TextToMatch.parseRoundText(roundText);
            SaveFile.saveRound(getApplicationContext().getFilesDir(), ROUND_SAVE_FILENAME, round);
            refreshRoundData();
        }
    }
}
