package com.davidread.thebanddatabase;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link BandRepository} provides a dummy dataset of {@link Band} objects. The class is implemented
 * as a singleton, so {@link #getInstance(Context)} should be used to access the methods of this
 * class.
 */
public class BandRepository {

    /**
     * Reference to a previous instance of {@link BandRepository}, if one exists.
     */
    private static BandRepository instance;

    /**
     * {@link List} of dummy {@link Band} objects.
     */
    private final List<Band> mBands;

    /**
     * Returns an initialized instance of {@link BandRepository}.
     *
     * @param context {@link Context} for the private constructor.
     * @return An initialized instance of {@link BandRepository}.
     */
    public static BandRepository getInstance(Context context) {
        if (instance == null) {
            instance = new BandRepository(context);
        }
        return instance;
    }

    /**
     * Constructs a new {@link BandRepository} with a {@link #mBands} of dummy {@link Band} objects.
     *
     * @param context {@link Context} to retrieve {@link String} arrays from resources.
     */
    private BandRepository(Context context) {
        mBands = new ArrayList<>();
        Resources res = context.getResources();
        String[] bands = res.getStringArray(R.array.bands);
        String[] descriptions = res.getStringArray(R.array.descriptions);
        for (int i = 0; i < bands.length; i++) {
            mBands.add(new Band(i + 1, bands[i], descriptions[i]));
        }
    }

    /**
     * Returns a {@link List} of {@link Band} objects with dummy attributes.
     *
     * @return {@link List} of {@link Band} objects with dummy attributes.
     */
    public List<Band> getBands() {
        return mBands;
    }

    /**
     * Returns a particular {@link Band} object from the dummy {@link List} of {@link Band} objects.
     *
     * @param bandId The id of the {@link Band} object to retrieve.
     * @return The {@link Band} object with the given id from the dummy {@link List} of {@link Band}
     * objects.
     */
    public Band getBand(int bandId) {
        for (Band band : mBands) {
            if (band.getId() == bandId) {
                return band;
            }
        }
        return null;
    }
}
