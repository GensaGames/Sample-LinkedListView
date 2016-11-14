package linkedlistview.sample.github.linkedlistview.controller.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private OnItemPlayClick mOnItemPlayClick;
    private Context mContext;

    public MusicListAdapter(Context context) {
        this.mMusicListItems = new LinkedList<>();
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_music_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MusicListItem music = mMusicListItems.get(position);
        holder.mTitle.setText(music.getTitle());
        holder.mDuration.setText(music.getDuration());
        holder.mAuthor.setText(music.getAuthor());
        if (music.isPlaying()) {
            holder.mImagePlay.setImageDrawable(ContextCompat.getDrawable(mContext,
                    R.mipmap.ic_pause_circle_black_24dp));
        } else {
            holder.mImagePlay.setImageDrawable(ContextCompat.getDrawable(mContext,
                    R.mipmap.ic_play_circle_filled_black_24dp));
        }
    }

    @Override
    public int getItemCount() {
        return mMusicListItems.size();
    }

    public List<MusicListItem> getMusicListItems() {
        return mMusicListItems;
    }

    public void setOnItemPlayClick(OnItemPlayClick onItemPlayClick) {
        this.mOnItemPlayClick = onItemPlayClick;
    }

    public void addAll(List<MusicListItem> musicListItems) {
        this.mMusicListItems.addAll(musicListItems);
        notifyDataSetChanged();
    }

    public void updatePlayingState(boolean state, int position) {
        for (MusicListItem listItem : mMusicListItems) {
            listItem.setIsPlaying(false);
        }
        mMusicListItems.get(position).setIsPlaying(state);
        notifyDataSetChanged();
    }

    public interface OnItemPlayClick {
        void onItemPlayClick(int layoutPosition);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitle, mDuration, mAuthor;
        private ImageView mImagePlay;

        private MyViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.adapter_title);
            mDuration = (TextView) view.findViewById(R.id.adapter_duration);
            mAuthor = (TextView) view.findViewById(R.id.adapter_author);
            mImagePlay = (ImageView) view.findViewById(R.id.adapter_image_play);
            mImagePlay.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemPlayClick != null) {
                mOnItemPlayClick.onItemPlayClick(getLayoutPosition());
            }
        }
    }
}