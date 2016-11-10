package linkedlistview.sample.github.linkedlistview.controller.animation;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.gensagames.linkedlistview.anim.CenterMotionController;

/**
 * Created by GensaGames
 * GensaGames
 */

public class MusicHeaderAnimator extends CenterMotionController {

    private float mMaxSideRotation;
    private boolean mSupportTranslationZ;
    private boolean mSupportRotationY = true;

    @Override
    public void onScrollAction() {
        super.onScrollAction();

        if (mSupportRotationY) {
            for (int i = getFirstVisiblePosition() - 1; i < getLastVisiblePosition() + 1; i++) {
                View view = getMainViewHolder().getChildAt(i);
                if (view != null) {
                    updateAngle(view);
                }
            }
        }

        if (mSupportTranslationZ) {
            for (int i = getFirstVisiblePosition() - 3; i < getCenterViewIndex() + 1; i++) {
                View view = getMainViewHolder().getChildAt(i);
                if (view != null) {
                    updateTranslation(view, i, getCenterViewIndex());
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setSupportTranslationZ(boolean supportTranslationZ) {
        this.mSupportTranslationZ = supportTranslationZ;
    }

    public void setSupportRotationY(boolean supportRotationY) {
        this.mSupportRotationY = supportRotationY;
    }

    public void setMaxSideRotation(float maxSideRotation) {
        this.mMaxSideRotation = maxSideRotation;
    }

    private void updateAngle(View v) {
        float rotationFactor = mMaxSideRotation / (getScrollViewWidth() / 2);
        int updated = -1 * (int) (getTotalScrollToCenter(v) * rotationFactor);
        v.setRotationY(updated);
    }

    private void updateTranslation(View v, int indexInVisibleQueue, int centerIndex) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        if (indexInVisibleQueue < centerIndex) {
            v.setTranslationZ(indexInVisibleQueue * -1);
            return;
        }
        v.setTranslationZ(0);
    }
}
