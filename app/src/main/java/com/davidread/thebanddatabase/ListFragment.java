package com.davidread.thebanddatabase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

/**
 * {@link ListFragment} represents a user interface that lists the names of all {@link Band}
 * objects provided by {@link BandRepository}. Clicking on a list item navigates to
 * {@link DetailFragment}.
 */
public class ListFragment extends Fragment {

    /**
     * Callback method invoked to return the {@link View} for this fragment's UI. It returns a
     * {@link View} that contains a {@link Button} for each {@link Band} provided by
     * {@link BandRepository}.
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
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        LinearLayout layout = (LinearLayout) rootView;

        // Create a Button for each Band provided by BandRepository.
        List<Band> bandList = BandRepository.getInstance(requireContext()).getBands();
        for (Band band : bandList) {
            Button button = new Button(this.requireActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 10);
            button.setLayoutParams(layoutParams);
            button.setText(band.getName());

            // Attach Band id as a tag to the Button.
            button.setTag(band.getId());

            /* Attach OnClickListener to Button to load a new DetailFragment. Pass the tag
             * associated with the selected Button as an argument to DetailFragment. */
            button.setOnClickListener(buttonView -> {

                int selectedBandId = (int) buttonView.getTag();
                Bundle args = new Bundle();
                args.putInt(DetailFragment.BAND_ID_ARG, selectedBandId);

                Navigation.findNavController(buttonView).navigate(R.id.show_item_detail, args);
            });

            // Add the Button to the LinearLayout.
            layout.addView(button);
        }

        return rootView;
    }
}