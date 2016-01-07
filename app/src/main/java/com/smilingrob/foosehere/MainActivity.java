package com.smilingrob.foosehere;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.smilingrob.foosehere.data.RoundFile;
import com.smilingrob.foosehere.data.RoundText;
import com.smilingrob.foosehere.match.Match;
import com.smilingrob.foosehere.match.MeProfile;
import com.smilingrob.foosehere.match.Player;
import com.smilingrob.foosehere.match.Round;
import com.smilingrob.foosehere.ui.MatchListRecyclerAdapter;
import com.smilingrob.foosehere.ui.MatchViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Main application activity.
 */
public class MainActivity extends BaseActivity implements MatchViewHolder.MatchViewActions {
    String roundText;
    Round round;
    MatchListRecyclerAdapter matchListRecyclerAdapter;
    RecyclerView matchListRecyclerView;
    private Drawer mDrawer;

    static final String BUNDLE_ROUND_TEXT = "BUNDLE_ROUND_TEXT";
    static final String ROUND_SAVE_FILENAME = "round.json";
    static final String TAG = BaseActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        if (savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_ROUND_TEXT)) {
            roundText = savedInstanceState.getString(BUNDLE_ROUND_TEXT);
        }

        matchListRecyclerAdapter = new MatchListRecyclerAdapter(this, this);
        matchListRecyclerView = (RecyclerView) findViewById(R.id.activity_main_match_list);
        matchListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        matchListRecyclerView.setAdapter(matchListRecyclerAdapter);


        createDrawer();
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
        saveToFile();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFromFile();
    }

    /**
     * Create a material navigation drawer.
     */
    private void createDrawer() {
        PrimaryDrawerItem addLeagueEntry = new PrimaryDrawerItem().withName(R.string.drawer_entry_add_league);
        SecondaryDrawerItem settingsEntry = new SecondaryDrawerItem().withName(R.string.drawer_entry_settings);

        AccountHeader meProfile = new AccountHeaderBuilder()
                .withActivity(this)
                .withDividerBelowHeader(true)
                .addProfiles(new MeProfile())
                .withHeaderBackground(R.color.headerColor)
                .withTextColor(Color.WHITE)
                .build();

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withToolbar(mToolbar)
                .withAccountHeader(meProfile)
                .withActionBarDrawerToggle(true)
                .withDrawerGravity(Gravity.START)
                .addDrawerItems(addLeagueEntry, new DividerDrawerItem(), settingsEntry)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        return false;
                    }
                })
                .build();
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
     * Save the round information to the save file.
     */
    void loadFromFile() {
        round = RoundFile.loadRound(getApplicationContext().getFilesDir(), ROUND_SAVE_FILENAME);
        refreshRoundData();
    }

    /**
     * Load the round information from the file.
     */
    void saveToFile() {
        RoundFile.saveRound(getApplicationContext().getFilesDir(), ROUND_SAVE_FILENAME, round);
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
