package linkedlistview.sample.github.linkedlistview.stub;

import android.support.v4.app.Fragment;

import com.thedeanda.lorem.LoremIpsum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import linkedlistview.sample.github.linkedlistview.R;
import linkedlistview.sample.github.linkedlistview.model.MusicHeaderItem;
import linkedlistview.sample.github.linkedlistview.model.MusicListItem;
import linkedlistview.sample.github.linkedlistview.view.PlaylistFragment;

/**
 * Created by GensaGames
 * GensaGames
 */

public class StubItems {

    public static final String SAMPLE_MUSIC_1 = "sample_song_1";
    public static final String SAMPLE_MUSIC_FOLDER = "raw";
    public static final long ANIM_SCROLLTO_DUR = 500L;
    public static final int VU_METER_ITEMS = 7;

    public static final float MIN_VUMETER_SCALE = 0.6f;
    public static final float MAX_VUMETER_TRANSLATE = -60f;
    public static final float MAX_SCROLL_HIDE_BAR = 0.6f;

    private static final int PLAYLIST_ITEMS_MIN = 10;
    private static final int PLAYLIST_ITEMS_MAX = 15;
    private static final int PLAYLIST_COUNT = 3;

    private static final int[] MUSIC_PHOTOS = {R.drawable.music_album_photo_2, R.drawable.music_album_photo_3,
            R.drawable.music_album_photo_4, R.drawable.music_album_photo_5, R.drawable.music_album_photo_6,
            R.drawable.music_album_photo_7, R.drawable.music_album_photo_8, R.drawable.music_album_photo_9,
            R.drawable.music_album_photo_10, R.drawable.music_album_photo_11, R.drawable.music_album_photo_12,
            R.drawable.music_album_photo_13, R.drawable.music_album_photo_14, R.drawable.music_album_photo_15,
            R.drawable.music_album_photo_16, R.drawable.music_album_photo_17, R.drawable.music_album_photo_18,
            R.drawable.music_album_photo_19, R.drawable.music_album_photo_20, R.drawable.music_album_photo_21,
            R.drawable.music_album_photo_22};

    public static List<MusicHeaderItem> getBaseMusicItems() {
        List<MusicHeaderItem> items = new ArrayList<>();
        for (int resId : MUSIC_PHOTOS) {
            items.add(new MusicHeaderItem(resId, LoremIpsum.getInstance().getName()));
        }
        return items;
    }

    public static List<Fragment> getBasePlaylistFragments() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < PLAYLIST_COUNT; i++) {
            fragments.add(new PlaylistFragment());
        }
        return fragments;
    }

    public static List<String> getBasePlaylistTitles() {
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < PLAYLIST_COUNT; i++) {
            titles.add(LoremIpsum.getInstance().getWords(2));
        }
        return titles;
    }

    public static List<MusicListItem> getBasePlaylistMusicItems() {
        List<MusicListItem> musicListItems = new ArrayList<>();
        Random random = new Random();
        int randomCount = random.nextInt((PLAYLIST_ITEMS_MAX -
                PLAYLIST_ITEMS_MIN) + 1) + PLAYLIST_ITEMS_MIN;
        for (int i = 0; i < randomCount; i++) {
            String time = random.nextInt(50) + ":" + random.nextInt(59);
            musicListItems.add(new MusicListItem(LoremIpsum.getInstance().getTitle(3, 4), time,
                    LoremIpsum.getInstance().getName()));
        }
        return musicListItems;
    }
}
