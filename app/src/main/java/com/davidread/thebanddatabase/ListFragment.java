package com.davidread.thebanddatabase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * {@link ListFragment} represents a user interface that lists the names of all {@link Band}
 * objects provided by {@link BandRepository}. Clicking on a list item navigates to
 * {@link DetailFragment}.
 */
public class ListFragment extends Fragment {

    /**
     * Callback method invoked to return the {@link View} for this fragment's UI. It returns a
     * {@link View} that contains a {@link RecyclerView} with item views for each {@link Band}
     * provided by the {@link BandRepository}.
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

        /* Create OnClickListener to apply to each RecyclerView view. On click, it loads a new
         * DetailFragment while passing the selected view's tag as an argument. */
        View.OnClickListener onClickListener = itemView -> {

            int selectedBandId = (int) itemView.getTag();
            Bundle args = new Bundle();
            args.putInt(DetailFragment.BAND_ID_ARG, selectedBandId);

            Navigation.findNavController(itemView).navigate(R.id.show_item_detail, args);
        };

        // Get List of Band objects from BandRepository.
        List<Band> bands = BandRepository.getInstance(requireContext()).getBands();

        // Initialize the RecyclerView to show the List.
        RecyclerView recyclerView = rootView.findViewById(R.id.band_list);
        recyclerView.setAdapter(new BandAdapter(bands, onClickListener));

        // Add divider lines to RecyclerView.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(), DividerItemDecoration.VERTICAL
        );
        recyclerView.addItemDecoration(dividerItemDecoration);

        return rootView;
    }

    /**
     * {@link BandAdapter} provides a binding from a {@link List} of {@link Band} objects to a
     * {@link RecyclerView}. An {@link View.OnClickListener} may be passed into the constructor to
     * define what each item view should do when it is clicked.
     */
    private class BandAdapter extends RecyclerView.Adapter<BandHolder> {

        /**
         * {@link List} of {@link Band} objects being adapted for a {@link RecyclerView}.
         */
        private final List<Band> mBands;

        /**
         * {@link View.OnClickListener} to apply to each {@link RecyclerView} item view.
         */
        private final View.OnClickListener mOnClickListener;

        /**
         * Constructs a new {@link BandAdapter}.
         *
         * @param bands           @link List} of {@link Band} objects being adapted for a
         *                        {@link RecyclerView}.
         * @param onClickListener {@link View.OnClickListener} to apply to each {@link RecyclerView}
         *                        item view.
         */
        public BandAdapter(List<Band> bands, View.OnClickListener onClickListener) {
            mBands = bands;
            mOnClickListener = onClickListener;
        }

        /**
         * Callback method invoked when {@link RecyclerView} needs a new empty {@link BandHolder} to
         * represent a {@link Band}.
         *
         * @param parent   {@link ViewGroup} into which the new {@link View} will be added after it
         *                 is bound to an adapter position.
         * @param viewType The view type of the new {@link View}.
         * @return A new {@link BandHolder}.
         */
        @NonNull
        @Override
        public BandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new BandHolder(layoutInflater, parent);
        }

        /**
         * Callback method invoked when {@link RecyclerView} needs to bind data to a
         * {@link BandHolder} at a certain position index.
         *
         * @param holder   {@link BandHolder} to be bound.
         * @param position The {@link BandHolder} object's position index in the adapter.
         */
        @Override
        public void onBindViewHolder(BandHolder holder, int position) {
            Band band = mBands.get(position);
            holder.bind(band);
            holder.itemView.setTag(band.getId());
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        /**
         * Returns the total number of items this adapter is adapting.
         *
         * @return The total number of items this adapter is adapting.
         */
        @Override
        public int getItemCount() {
            return mBands.size();
        }
    }

    /**
     * {@link BandHolder} is a model class that describes a single band item view and metadata about
     * its place within a {@link RecyclerView}.
     */
    private static class BandHolder extends RecyclerView.ViewHolder {

        /**
         * {@link TextView} to hold the name of a band.
         */
        private final TextView mNameTextView;

        /**
         * Constructs a new {@link BandHolder}.
         *
         * @param inflater {@link LayoutInflater} for inflating layout files.
         * @param parent   {@link ViewGroup}
         */
        public BandHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_band, parent, false));
            mNameTextView = itemView.findViewById(R.id.band_name);
        }

        /**
         * Binds new data to this {@link BandHolder}.
         *
         * @param band {@link Band} with new data to bind here.
         */
        public void bind(Band band) {
            mNameTextView.setText(band.getName());
        }
    }
}