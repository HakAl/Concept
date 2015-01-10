package concept.com.labtech.ui;

import javax.inject.Inject;
import concept.com.labtech.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainFragment extends ABaseFragment implements View.OnClickListener
{
    @Inject Picasso picasso;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public static MainFragment newInstance(int sectionNumber) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        root.findViewById(R.id.fab).setOnClickListener(this);

        return root;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                ((MainActivity) getActivity()).newEntry();
                break;
            default:
                break;
        }
    }
}