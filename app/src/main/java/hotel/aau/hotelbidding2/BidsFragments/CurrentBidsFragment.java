package hotel.aau.hotelbidding2.BidsFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hotel.aau.hotelbidding2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentBidsFragment extends Fragment {
    View view;

    public CurrentBidsFragment() {
        // Required empty public constructor
    }
    public static CurrentBidsFragment getInstance(){
        Bundle args = new Bundle();
        CurrentBidsFragment currentBidsFragment = new CurrentBidsFragment();
        currentBidsFragment.setArguments(args);
        return currentBidsFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_current_bids,container,false);


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

}
