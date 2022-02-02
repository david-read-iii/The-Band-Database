package com.davidread.thebanddatabase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * {@link DetailFragment} represents a user interface for the detail view of a {@link Band} object.
 */
public class DetailFragment extends Fragment {

    /**
     * {@link String} identifier for finding the selected {@link Band} id in the {@link Bundle}
     * arguments passed to this fragment.
     */
    public static final String BAND_ID_ARG = "band_id";

    /**
     * {@link Band} whose information is currently being displayed in this fragment.
     */
    private Band mBand;

    /**
     * Required empty public constructor.
     */
    public DetailFragment() {
    }

    /**
     * Callback method invoked when this fragment is initially created. It simply initializes
     * {@link #mBand} with the {@link Band} object whose information should be displayed in this
     * fragment.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get selected Band id from ListFragment.
        int bandId = 1;
        Bundle args = getArguments();
        if (args != null) {
            bandId = args.getInt(BAND_ID_ARG);
        }

        // Get the selected Band.
        mBand = BandRepository.getInstance(requireContext()).getBand(bandId);
    }

    /**
     * Callback method invoked to return the {@link View} for this fragment's UI. It returns a
     * {@link View} that contains information about {@link #mBand}.
     *
     * @param inflater           Used to inflate any views in the fragment.
     * @param container          If non-null, is the parent view that the fragment's UI should be
     *                           attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous
     *                           saved state as given here.
     * @return The {@link View} for the fragment's UI.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        if (mBand != null) {
            TextView nameTextView = rootView.findViewById(R.id.band_name);
            nameTextView.setText(mBand.getName());

            TextView descriptionTextView = rootView.findViewById(R.id.band_description);
            descriptionTextView.setText(mBand.getDescription());
        }

        return rootView;
    }
}