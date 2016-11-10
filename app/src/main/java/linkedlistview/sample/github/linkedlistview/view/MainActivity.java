package linkedlistview.sample.github.linkedlistview.view;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gensagames.linkedlistview.LinkedListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import linkedlistview.sample.github.linkedlistview.R;
import linkedlistview.sample.github.linkedlistview.controller.adapter.MusicHeaderAdapter;
import linkedlistview.sample.github.linkedlistview.controller.adapter.PlaylistPagerAdapter;
import linkedlistview.sample.github.linkedlistview.controller.animation.MusicHeaderAnimator;
import linkedlistview.sample.github.linkedlistview.stub.StubItems;

public class MainActivity extends AppCompatActivity implements LinkedListView.OnItemClickListener {

    @BindView(R.id.header_toolbar)
    public Toolbar mMainToolbar;

    @BindView(R.id.header_tab_layout)
    public TabLayout mTabLayout;

    @BindView(R.id.content_linkedlistview)
    public LinkedListView mLinkedListView;

    @BindView(R.id.content_viewpager)
    public ViewPager mViewPager;


    private MusicHeaderAdapter mMusicHeaderAdapter;
    private MusicHeaderAnimator mMusicHeaderAnimator;
    private PlaylistPagerAdapter mPlaylistPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_header);
        ButterKnife.bind(this);

        setSupportActionBar(mMainToolbar);
        setupLinkedList();
        setupViewPager();

    }

    private void setupViewPager() {
        mPlaylistPagerAdapter = new PlaylistPagerAdapter(getSupportFragmentManager());
        mPlaylistPagerAdapter.addAll(StubItems.getBasePlaylistFragments(),
                StubItems.getBasePlaylistTitles());

        mTabLayout.setupWithViewPager(mViewPager, true);
        mViewPager.setAdapter(mPlaylistPagerAdapter);
    }

    private void setupLinkedList() {
        mMusicHeaderAdapter = new MusicHeaderAdapter(getApplicationContext());
        mMusicHeaderAdapter.addItemList(StubItems.getBaseMusicItems());
        mMusicHeaderAdapter.setOnItemClickListener(this);
        mLinkedListView.setAdapter(mMusicHeaderAdapter);

        mMusicHeaderAnimator = new MusicHeaderAnimator();
        mMusicHeaderAnimator.setMaxSideRotation(40);
        mMusicHeaderAnimator.setMaxTranslationX(120);
        mMusicHeaderAnimator.setOptLeftOverlap(-40);
        mMusicHeaderAnimator.setOptRightOverlap(-40);
        mMusicHeaderAnimator.setSupportRotationY(true);
        mLinkedListView.setAnimationController(mMusicHeaderAnimator);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mMusicHeaderAnimator.setSupportTranslationZ(true);
        }
    }

    private void configureListBounds() {
        int sidePadding = mLinkedListView.getWidth() / 2 - mLinkedListView
                .getPaddingStart() - mLinkedListView.getPaddingEnd();
        mLinkedListView.getMainViewHolder().setPadding(sidePadding, 0, sidePadding, 0);
        mLinkedListView.onScrollChanged();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            configureListBounds();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    @SuppressWarnings("ALL")
    public void onItemClick(View view) {
        if (true) {
            mMusicHeaderAnimator.animateScrollTo(view, StubItems.ANIM_SCROLLTO_DUR);
        }
        Log.e("Te", "view: " + view.getTranslationZ());
    }
}
