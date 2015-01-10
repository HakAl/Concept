package concept.com.labtech.ui;

import android.os.Bundle;
import android.app.Fragment;

/** Base fragment which performs injection using the activity-scoped object graph **/
public abstract class ABaseFragment extends Fragment {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getActivity() instanceof ABaseActivity;
        ((ABaseActivity)getActivity()).inject(this);
    }
}