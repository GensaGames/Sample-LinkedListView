package linkedlistview.sample.github.linkedlistview.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gensagames.linkedlistview.LinkedListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import linkedlistview.sample.github.linkedlistview.R;
import linkedlistview.sample.github.linkedlistview.controller.model.MusicItem;

/**
 * Created by GensaGames
 * GensaGames
 */

public class MusicListAdapter extends LinkedListView.Adapter {

    private Context context;
    private List<MusicItem> itemsList;
    private List<View> viewsLinkedList;

    public MusicListAdapter(Context context) {
        this.context = context;
        itemsList = new ArrayList<>();
        viewsLinkedList = new LinkedList<>();
    }

    public void addItemList (MusicItem musicItem) {
        itemsList.add(musicItem);
        notifyDataSetChanged();
    }

    @Override
    public View getObjectView(int position, ViewGroup parentView) {
        if (position >= viewsLinkedList.size() || viewsLinkedList.get(position) == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            viewsLinkedList.add(inflater.inflate(R.layout.adapter_music_item, parentView, false));
        }
        return viewsLinkedList.get(position);
    }

    @Override
    public int getObjectCount() {
        return itemsList.size();
    }
}
