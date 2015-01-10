package concept.com.labtech.ui;

import javax.inject.Inject;
import concept.com.labtech.R;
import concept.com.labtech.ui.adapters.MainListAdapter;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainFragment extends ABaseFragment implements View.OnClickListener
{
    @Inject
    Handler handler;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        root.findViewById(R.id.fab).setOnClickListener(this);

        ListView list = (ListView) root.findViewById(R.id.list_main);
        String[] listItems = getResources().getStringArray(R.array.list_titles);
        list.setAdapter(new MainListAdapter(getActivity(), listItems));

        return root;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity) getActivity()).newEntry();
                    }
                }, 666);
                break;
            default:
                break;
        }
    }
}