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
import android.support.v4.view.ViewCompat;
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
import linkedlistview.sample.github.linkedlistview.controller.animation.ActivityAnimator;
import linkedlistview.sample.github.linkedlistview.controller.animation.MusicHeaderAnimator;
import linkedlistview.sample.github.linkedlistview.model.AnimBounceTask;
import linkedlistview.sample.github.linkedlistview.model.AnimPaddingTask;
import linkedlistview.sample.github.linkedlistview.stub.StubItems;
import linkedlistview.sample.github.linkedlistview.view.base.BaseActivity;

public class ActivityMainPlay extends BaseActivity implements LinkedListView.OnItemClickListener,
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

    @BindView(R.id.header_bar_previous)
    public FloatingActionButton mBarPrevious;

    @BindView(R.id.header_bar_star)
    public FloatingActionButton mBarStar;

    @BindView(R.id.header_bar_next)
    public FloatingActionButton mBarNext;

    @BindView(R.id.header_button_bar)
    public LinearLayout mHeaderBarLayout;

    @BindView(R.id.content_linkedlistview)
    public LinkedListView mLinkedListView;

    @BindView(R.id.content_viewpager)
    public ViewPager mViewPager;

    private MediaPlayer mMediaPlayer;
    private MusicHeaderAdapter mMusicHeaderAdapter;
    private MusicHeaderAnimator mMusicHeaderAnimator;
    private PlaylistPagerAdapter mPlaylistPagerAdapter;
    private ActivityAnimator activityAnimator;

    private boolean mIsHeaderBarHidden;

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
        mVuMeterView.stop(false);
        mHeaderBarLayout.setScaleX(0);
        mHeaderBarLayout.setScaleY(0);
        mAppBarLayout.addOnOffsetChangedListener(this);
        mAppBarLayout.setExpanded(false, false);
        setSupportActionBar(mMainToolbar);
    }

    private void setupMediaPlayer() {
        int sampleSongLocation = getResources().getIdentifier(StubItems.SAMPLE_MUSIC_1,
                StubItems.SAMPLE_MUSIC_FOLDER, getPackageName());
        mMediaPlayer = MediaPlayer.create(ActivityMainPlay.this, sampleSongLocation);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
    }

    private void updateToolbarVisualizer() {
        VisualizerDbmHandler visualizerHandler = DbmHandler.Factory
                .newVisualizerHandler(ActivityMainPlay.this, mMediaPlayer);
        mVisualWaves.linkTo(visualizerHandler);
        visualizerHandler.onResume();
    }

    private void starAnimDispatcher() {
        activityAnimator = new ActivityAnimator();
        activityAnimator.loopRandomDelayedAnim(new AnimBounceTask(mBarStar,
                StubItems.ANIM_STAR_BOUND_DUR, (mBarStar.getWidth() / 3),
                AnimBounceTask.State.START));
    }

    public void updatePlayState(boolean play) {
        if (mMediaPlayer.isPlaying()) {
            if (play) {
                return;
            }
            mMediaPlayer.pause();
            mVuMeterView.stop(true);
            mMainFab.setImageDrawable(ContextCompat.getDrawable(ActivityMainPlay.this,
                    android.R.drawable.ic_media_play));
        } else {
            if (!play) {
                return;
            }
            mMediaPlayer.start();
            mVuMeterView.resume(true);
            mMusicHeaderAnimator.animateScrollTo(mLinkedListView.getMainViewHolder()
                    .getChildAt(mMusicHeaderAnimator.getCenterViewIndex()), StubItems
                    .ANIM_LINKEDLIST_SELECT);
            mMainFab.setImageDrawable(ContextCompat.getDrawable(ActivityMainPlay.this,
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

        if (scrollPercentage > StubItems.MAX_SCROLL_HIDE_BAR) {
            if (!mIsHeaderBarHidden) {
                mIsHeaderBarHidden = true;
                ViewCompat.animate(mHeaderBarLayout).scaleY(0).scaleX(0).start();
            }
        } else {
            if (mIsHeaderBarHidden) {
                mIsHeaderBarHidden = false;
                ViewCompat.animate(mHeaderBarLayout).scaleY(StubItems.MAX_HEADER_BAR_SCALE)
                        .scaleX(StubItems.MAX_HEADER_BAR_SCALE).start();
            }
        }
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

    private void updateListBounds() {
        mAppBarLayout.setExpanded(true, true);
        int sidePadding = mLinkedListView.getWidth() / 2 - mLinkedListView
                .getPaddingStart() - mLinkedListView.getPaddingEnd();
        int padding[] = new int[]{sidePadding, 0, sidePadding, 0};
        AnimPaddingTask animPaddingTask = new AnimPaddingTask(mLinkedListView.getMainViewHolder(),
                StubItems.ANIM_LINKEDLIST_BOUNDS, padding, null, new AnimPaddingTask.OnAnimationFrame() {
            @Override
            public void onAnimationFrame() {
                mLinkedListView.onScrollChanged();
            }
        });
        activityAnimator.animatePaddingChange(animPaddingTask);
    }
    

    @SuppressWarnings("unused")
    @OnClick(R.id.header_fab)
    public void onClickPlayFab() {
        updatePlayState(!mMediaPlayer.isPlaying());
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.header_bar_next)
    public void onClickNextFab() {
        int index = mMusicHeaderAnimator.getCenterViewIndex() + 1;
        if (index >= mMusicHeaderAdapter.getObjectCount()) {
            return;
        }
        mMusicHeaderAnimator.animateScrollTo(mLinkedListView.getMainViewHolder()
                .getChildAt(index), StubItems.ANIM_LINKEDLIST_SELECT);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.header_bar_previous)
    public void onClickPreviousFab() {
        int index = mMusicHeaderAnimator.getCenterViewIndex() - 1;
        if (index < 0) {
            return;
        }
        mMusicHeaderAnimator.animateScrollTo(mLinkedListView.getMainViewHolder()
                .getChildAt(index), StubItems.ANIM_LINKEDLIST_SELECT);
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
            checkActivityPermissions();
            starAnimDispatcher();
            updateListBounds();
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
        Log.e(ActivityMainPlay.class.getSimpleName(), "onItemClick: " + view.getId());
        mMusicHeaderAnimator.animateScrollTo(view, StubItems.ANIM_LINKEDLIST_SELECT);
    }


}
