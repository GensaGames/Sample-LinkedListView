package linkedlistview.sample.github.linkedlistview.view;

import android.Manifest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.cleveroad.audiovisualization.DbmHandler;
import com.cleveroad.audiovisualization.GLAudioVisualizationView;
import com.cleveroad.audiovisualization.VisualizerDbmHandler;
import com.gensagames.linkedlistview.LinkedListView;
import com.thedeanda.lorem.LoremIpsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.gresse.hugo.vumeterlibrary.VuMeterView;
import linkedlistview.sample.github.linkedlistview.R;
import linkedlistview.sample.github.linkedlistview.controller.adapter.MusicHeaderAdapter;
import linkedlistview.sample.github.linkedlistview.controller.adapter.PlaylistPagerAdapter;
import linkedlistview.sample.github.linkedlistview.controller.animation.MusicHeaderAnimator;
import linkedlistview.sample.github.linkedlistview.stub.StubItems;

public class MainActivity extends BaseActivity implements LinkedListView.OnItemClickListener,
        AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.header_toolbar)
    public Toolbar mMainToolbar;

    @BindView(R.id.header_vumeter)
    public VuMeterView mVuMeterView;

    @BindView(R.id.header_visualizer_view)
    public GLAudioVisualizationView mVisualWaves;

    @BindView(R.id.header_appbar)
    public AppBarLayout mAppBarLayout;

    @BindView(R.id.header_tab_layout)
    public TabLayout mTabLayout;

    @BindView(R.id.header_fab)
    public FloatingActionButton mMainFab;

    @BindView(R.id.header_button_bar)
    public LinearLayout mHeaderButtonBar;

    @BindView(R.id.content_linkedlistview)
    public LinkedListView mLinkedListView;

    @BindView(R.id.content_viewpager)
    public ViewPager mViewPager;

    private MediaPlayer mMediaPlayer;
    private MusicHeaderAdapter mMusicHeaderAdapter;
    private MusicHeaderAnimator mMusicHeaderAnimator;
    private PlaylistPagerAdapter mPlaylistPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_header);
        ButterKnife.bind(this);

        setupToolbar();
        setupLinkedList();
        setupViewPager();
        setupMediaPlayer();

    }

    private void setupToolbar() {
        mMainToolbar.setTitle(LoremIpsum.getInstance().getWords(3, 5));
        mVuMeterView.setBlockNumber(StubItems.VU_METER_ITEMS);
        mAppBarLayout.addOnOffsetChangedListener(this);
        setSupportActionBar(mMainToolbar);
    }

    private void setupMediaPlayer() {
        int sampleSongLocation = getResources().getIdentifier(StubItems.SAMPLE_MUSIC_1,
                StubItems.SAMPLE_MUSIC_FOLDER, getPackageName());
        mMediaPlayer = MediaPlayer.create(MainActivity.this, sampleSongLocation);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
    }

    @SuppressWarnings("CheckPermission")
    private void updateToolbarVisualizer() {
        VisualizerDbmHandler visualizerHandler = DbmHandler.Factory
                .newVisualizerHandler(MainActivity.this, mMediaPlayer);
        mVisualWaves.linkTo(visualizerHandler);
        visualizerHandler.onResume();
    }

    private void updatePlayState() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mVuMeterView.stop(true);
            mMainFab.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,
                    android.R.drawable.ic_media_play));
        } else {
            mMediaPlayer.start();
            mVuMeterView.resume(true);
            mMainFab.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,
                    android.R.drawable.ic_media_pause));
        }
    }

    private void updateToolbarScroll(float scrollPercentage) {
        float differenceScale = 1 - StubItems.MIN_VUMETER_SCALE;
        float updatedScale = StubItems.MIN_VUMETER_SCALE +
                ((1 - scrollPercentage) * differenceScale);
        mVuMeterView.setScaleX(updatedScale);
        mVuMeterView.setScaleY(updatedScale);

        float maxTranslateX = StubItems.MAX_VUMETER_TRANSLATE;
        float updatedTranslate = scrollPercentage * maxTranslateX;
        mVuMeterView.setTranslationX(updatedTranslate);

        mHeaderButtonBar.setScaleX(1 - scrollPercentage);
        mHeaderButtonBar.setScaleY(1 - scrollPercentage);
        /*if (scrollPercentage > MAX_SCROLL_HIDE_BAR) {
            mHeaderButtonBar.setVisibility(View.GONE);
        } else {
            mHeaderButtonBar.setVisibility(View.VISIBLE);
        }*/
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

    @OnClick(R.id.header_fab)
    @SuppressWarnings("unused")
    public void onClickPlayFab() {
        updatePlayState();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mVisualWaves.release();
        mMediaPlayer.release();
    }

    @Override
    public void onResume() {
        super.onResume();
        mVisualWaves.onResume();
    }

    @Override
    public void onPause() {
        mVisualWaves.onPause();
        super.onPause();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            configureListBounds();
            checkActivityPermissions();
        }
    }

    @Override
    public ArrayList<String> requiredPermissions() {
        return new ArrayList<>(Arrays.asList(Manifest.permission.RECORD_AUDIO,
                Manifest.permission.MODIFY_AUDIO_SETTINGS));
    }

    @Override
    public void onPermissionsDenied(List<String> deniedPermissionList) {
        /**-------------------------
         * TODO(Permission): ROFL
         * -----------------------*/
    }

    @Override
    public void onPermissionsGranted() {
        updateToolbarVisualizer();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        float currentScrollPercentage = (float) Math.abs(verticalOffset)
                / appBarLayout.getTotalScrollRange();
        updateToolbarScroll(currentScrollPercentage);
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
