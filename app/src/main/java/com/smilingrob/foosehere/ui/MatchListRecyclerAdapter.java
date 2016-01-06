package com.smilingrob.foosehere.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smilingrob.foosehere.R;
import com.smilingrob.foosehere.match.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * List of matches.
 */
public class MatchListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Match> matchList = new ArrayList<>();
    LayoutInflater layoutInflater;

    /**
     * List of matches.
     *
     * @param context activity.
     */
    public MatchListRecyclerAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = layoutInflater.inflate(R.layout.match_item, parent, false);
        return new MatchViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MatchViewHolder matchViewHolder = (MatchViewHolder) holder;
        final Match match = matchList.get(position);
        matchViewHolder.onBindView(match);
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

}
