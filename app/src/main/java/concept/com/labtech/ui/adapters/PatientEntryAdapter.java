package concept.com.labtech.ui.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by alex on 1/10/15.
 */
public class PatientEntryAdapter extends FragmentPagerAdapter {

    public PatientEntryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
