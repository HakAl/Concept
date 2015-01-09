package concept.com.labtech.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import concept.com.labtech.activities.ABaseActivity;

/** Base fragment which performs injection using the activity-scoped object graph **/
public abstract class ABaseFragment extends Fragment {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getActivity() instanceof ABaseActivity;
        ((ABaseActivity)getActivity()).inject(this);
    }
}