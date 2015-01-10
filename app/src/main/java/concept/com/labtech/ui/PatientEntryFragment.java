package concept.com.labtech.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import concept.com.labtech.R;

/**
 * Created by alex on 1/10/15.
 */
public class PatientEntryFragment extends ABaseFragment {

    public static PatientEntryFragment newInstance()
    {
        return new PatientEntryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.patient_entry_fragment, container, false);
    }
}
