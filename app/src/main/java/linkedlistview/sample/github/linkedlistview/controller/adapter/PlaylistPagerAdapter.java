package linkedlistview.sample.github.linkedlistview.controller.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by GensaGames
 * GensaGames
 */

public class PlaylistPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mListFragments;
    private List<String> mListTitles;

    public PlaylistPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        mListFragments = new LinkedList<>();
        mListTitles = new LinkedList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mListTitles.get(position);
    }

    @Override
    public int getCount() {
        return mListFragments.size();
    }

    @SuppressWarnings("unused")
    public void addFragment(Fragment fragment, String title) {
        mListFragments.add(fragment);
        mListTitles.add(title);
    }

    public void addAll(List<Fragment> fragments, List<String> titles) {
        mListFragments.addAll(fragments);
        mListTitles.addAll(titles);
    }
}