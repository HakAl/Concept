package concept.com.labtech.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import concept.com.labtech.R;
import concept.com.labtech.ui.adapters.PatientEntryAdapter;
import concept.com.labtech.util.FragmentHelper;

/**
 * Created by alex on 1/10/15.
 */
public class PatientEntryFragment extends ABaseFragment implements View.OnClickListener {

    public static PatientEntryFragment newInstance(String id)
    {
        PatientEntryFragment fragment = new PatientEntryFragment();
        Bundle args = new Bundle();
        args.putString("ID", id);
        fragment.setArguments(args);
        return fragment;
    }
    public static PatientEntryFragment newInstance(int which)
    {
        PatientEntryFragment fragment = new PatientEntryFragment();
        Bundle args = new Bundle();
        args.putInt("which", which);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root;
        if (getArguments().getString("ID") == null) {
            root = inflater.inflate(R.layout.entry_fragment_one, container, false);
            root.findViewById(R.id.btn_entry_back).setOnClickListener(this);


        } else {
            root = inflater.inflate(R.layout.patient_entry_fragment, container, false);

            TextView unInput = (TextView) root.findViewById(R.id.input_unique_id);
            unInput.setText(getArguments().getString("ID"));

            root.findViewById(R.id.first_back).setOnClickListener(this);
            root.findViewById(R.id.first_next).setOnClickListener(this);

            root.findViewById(R.id.input_unique_prob).requestFocus();

        }

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_next:
                FragmentHelper.replaceFragment(getActivity().getFragmentManager(),
                        PatientEntryFragment.newInstance(0), R.id.container, "TAG");
                break;
            case R.id.first_back:
                getActivity().onBackPressed();
                break;
            case R.id.btn_entry_back:
                getActivity().onBackPressed();
                break;
            default: break;
        }
    }
}