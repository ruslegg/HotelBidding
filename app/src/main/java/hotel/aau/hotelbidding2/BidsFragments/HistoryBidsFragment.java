package hotel.aau.hotelbidding2.BidsFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hotel.aau.hotelbidding2.R;

public class HistoryBidsFragment extends Fragment {
    View view;

    public HistoryBidsFragment() {
        // Required empty public constructor
    }

    public static HistoryBidsFragment getInstance(){
        Bundle args = new Bundle();
        HistoryBidsFragment historyBidsFragment = new HistoryBidsFragment();
        historyBidsFragment.setArguments(args);
        return historyBidsFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_history_bids ,container,false);





        return view;
    }
}
