package linkedlistview.sample.github.linkedlistview.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import linkedlistview.sample.github.linkedlistview.R;
import linkedlistview.sample.github.linkedlistview.controller.adapter.MusicListAdapter;
import linkedlistview.sample.github.linkedlistview.stub.StubItems;

/**
 * Created by GensaGames
 * GensaGames
 */

public class FragmentPlaylist extends Fragment {

    @BindView(R.id.content_recyclerview)
    public RecyclerView mRecyclerView;

    private MusicListAdapter mMusicListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playlist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mMusicListAdapter = new MusicListAdapter();
        mMusicListAdapter.addAll(StubItems.getBasePlaylistMusicItems());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMusicListAdapter);
    }
}
