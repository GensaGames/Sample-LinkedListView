package linkedlistview.sample.github.linkedlistview.model;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by GensaGames
 * GensaGames
 */

public class AnimPaddingTask {
    private View mAnimatedView;
    private long mDuration;
    private int[] mPadding;
    private Animation.AnimationListener mAnimationListener;
    private OnAnimationFrame mOnAnimationFrame;

    public AnimPaddingTask(View animatedView, long duration, int[] padding,
                           @Nullable Animation.AnimationListener animationListener,
                           @Nullable OnAnimationFrame onAnimationFrame) {
        this.mAnimatedView = animatedView;
        this.mDuration = duration;
        this.mPadding = padding;
        this.mAnimationListener = animationListener;
        this.mOnAnimationFrame = onAnimationFrame;
    }

    public View getAnimatedView() {
        return mAnimatedView;
    }

    public long getDuration() {
        return mDuration;
    }

    public int[] getPadding() {
        return mPadding;
    }

    public Animation.AnimationListener getAnimationListener() {
        return mAnimationListener;
    }

    public OnAnimationFrame getOnAnimationFrame() {
        return mOnAnimationFrame;
    }

    public interface OnAnimationFrame {
        void onAnimationFrame();
    }
}
