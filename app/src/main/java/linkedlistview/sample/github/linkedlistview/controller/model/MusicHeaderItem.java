package linkedlistview.sample.github.linkedlistview.controller.model;

/**
 * Created by GensaGames
 * GensaGames
 */

public class MusicHeaderItem {
    private int imageResourceId;
    private String musicName;

    public MusicHeaderItem(int imageResourceId, String musicName) {
        this.imageResourceId = imageResourceId;
        this.musicName = musicName;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }
}
