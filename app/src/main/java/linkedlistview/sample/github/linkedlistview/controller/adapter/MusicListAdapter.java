package linkedlistview.sample.github.linkedlistview.controller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import linkedlistview.sample.github.linkedlistview.R;
import linkedlistview.sample.github.linkedlistview.model.MusicListItem;

/**
 * Created by GensaGames
 * GensaGames
 */

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MyViewHolder> {

    private List<MusicListItem> mMusicListItems;

    public MusicListAdapter() {
        this.mMusicListItems = new LinkedList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_music_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MusicListItem movie = mMusicListItems.get(position);
        holder.mTitle.setText(movie.getTitle());
        holder.mDuration.setText(movie.getDuration());
        holder.mAuthor.setText(movie.getAuthor());
    }

    @Override
    public int getItemCount() {
        return mMusicListItems.size();
    }

    public void addAll(List<MusicListItem> musicListItems) {
        this.mMusicListItems.addAll(musicListItems);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle, mDuration, mAuthor;

        private MyViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.adapter_title);
            mDuration = (TextView) view.findViewById(R.id.adapter_duration);
            mAuthor = (TextView) view.findViewById(R.id.adapter_author);
        }
    }
}