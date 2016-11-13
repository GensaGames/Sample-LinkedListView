package linkedlistview.sample.github.linkedlistview.model;

import android.view.View;

/**
 * Created by GensaGames
 * GensaGames
 */

public class AnimBounceTask {

    private View mView;
    private long mDuration;
    private float mMaxTranslationY;
    private State mActiveState;

    public AnimBounceTask(View mView, long mDuration,
                          float mMaxTranslationY, State mActiveState) {
        this.mView = mView;
        this.mDuration = mDuration;
        this.mMaxTranslationY = mMaxTranslationY;
        this.mActiveState = mActiveState;
    }

    public View getView() {
        return mView;
    }

    public long getDuration() {
        return mDuration;
    }

    public float getMaxTranslationY() {
        return mMaxTranslationY;
    }

    public State getState() {
        return mActiveState;
    }

    public enum State {
        STOP, START, RELEASE
    }
}
