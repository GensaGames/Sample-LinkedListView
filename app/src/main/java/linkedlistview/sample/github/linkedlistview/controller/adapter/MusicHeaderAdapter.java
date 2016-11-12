package linkedlistview.sample.github.linkedlistview.controller.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gensagames.linkedlistview.LinkedListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import linkedlistview.sample.github.linkedlistview.R;
import linkedlistview.sample.github.linkedlistview.model.MusicHeaderItem;

/**
 * Created by GensaGames
 * GensaGames
 */

public class MusicHeaderAdapter extends LinkedListView.Adapter {

    private Context mContext;
    private List<MusicHeaderItem> mItemsList;
    private List<MusicViewHolder> mViewsLinkedList;

    public MusicHeaderAdapter(Context mContext) {
        this.mContext = mContext;
        mItemsList = new ArrayList<>();
        mViewsLinkedList = new LinkedList<>();
    }

    public void addItem(MusicHeaderItem musicHeaderItem) {
        mItemsList.add(musicHeaderItem);
        notifyDataSetChanged();
    }

    public void addItemList(List<MusicHeaderItem> itemList) {
        mItemsList.addAll(itemList);
        notifyDataSetChanged();
    }

    @Override
    public void bindView(LinkedListView.ViewHolder v, int position) {
        MusicViewHolder viewHolder = (MusicViewHolder) v;
        viewHolder.cardView.setCardBackgroundColor(ContextCompat
                .getColor(mContext, android.R.color.white));

        Picasso.with(mContext).load(mItemsList.get(position).getImageResourceId())
                .error(android.R.drawable.stat_notify_error)
                .resize(100, 100)
                .into(viewHolder.imageView);
    }

    @Override
    public int getObjectCount() {
        return mItemsList.size();
    }

    @Override
    public LinkedListView.ViewHolder getViewHolder(int position, ViewGroup parentView) {
        if (position >= mViewsLinkedList.size() || mViewsLinkedList.get(position) == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            mViewsLinkedList.add(new MusicViewHolder(inflater
                    .inflate(R.layout.adapter_music_header_item, parentView, false)));
        }
        return mViewsLinkedList.get(position);
    }


    private static class MusicViewHolder extends LinkedListView.ViewHolder {

        private ImageView imageView;
        private CardView cardView;

        MusicViewHolder(View mainView) {
            super(mainView);
            cardView = (CardView) mainView.findViewById(R.id.adapter_cardview);
            imageView = (ImageView) mainView.findViewById(R.id.adapter_item_photo);
        }
    }
}
