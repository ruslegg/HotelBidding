package hotel.aau.hotelbidding2.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import hotel.aau.hotelbidding2.BidsFragments.CurrentBidsFragment;
import hotel.aau.hotelbidding2.BidsFragments.HistoryBidsFragment;

/**
 * Created by admin on 14/04/2018.
 */

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabsPagerFragmentAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        tabs = new String[] {
                "Current bids",
                "History bids"
        };
    }
    @Override
    public CharSequence getPageTitle(int position){
        return tabs[position];
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return CurrentBidsFragment.getInstance();
            case 1:
                return HistoryBidsFragment.getInstance();
        }
    return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
