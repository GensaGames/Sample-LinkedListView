package linkedlistview.sample.github.linkedlistview.model;

/**
 * Created by GensaGames
 * GensaGames
 */

public class MusicHeaderItem {
    private int mImageResourceId;
    private String mMusicName;

    public MusicHeaderItem(int mImageResourceId, String mMusicName) {
        this.mImageResourceId = mImageResourceId;
        this.mMusicName = mMusicName;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.mImageResourceId = imageResourceId;
    }

    public String getMusicName() {
        return mMusicName;
    }

    public void setMusicName(String musicName) {
        this.mMusicName = musicName;
    }
}
