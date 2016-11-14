package linkedlistview.sample.github.linkedlistview.model;

/**
 * Created by GensaGames
 * GensaGames
 */

public class MusicListItem {
    private String mTitle, mDuration, mAuthor;
    private boolean mIsPlaying;

    public MusicListItem(String title, String genre, String year) {
        this.mTitle = title;
        this.mDuration = genre;
        this.mAuthor = year;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.mIsPlaying = isPlaying;
    }

    public boolean isPlaying() {
        return mIsPlaying;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String name) {
        this.mTitle = name;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String mDuration) {
        this.mDuration = mDuration;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }
}
