package concept.com.labtech.ui;

import javax.inject.Inject;
import concept.com.labtech.R;
import concept.com.labtech.ui.adapters.MainListAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainFragment extends ABaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener
{
    private String[] urls = {
            "http://www.mvm.pitt.edu/research/antimicrobial-therapeutics-and-drug-discovery",
            "http://www.mvm.pitt.edu/research/cancer-virology",
            "http://www.mvm.pitt.edu/research/microbial-pathogenesis",
            "http://www.mvm.pitt.edu/research/molecular-and-cellular-biology-microbes"
    };

    @Inject
    Handler handler;

    private MainListAdapter adapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private EditText input;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        root.findViewById(R.id.fab).setOnClickListener(this);

        ListView list = (ListView) root.findViewById(R.id.list_main);
        String[] listItems = getResources().getStringArray(R.array.list_titles);
        this.adapter = new MainListAdapter(getActivity(), listItems);

        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        input = (EditText) root.findViewById(R.id.input_search);
//        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE)
//                {
//                    Toast.makeText(getActivity(), v.getText().toString(), Toast.LENGTH_LONG).show();
//                }
//                return false;
//            }
//        });


        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                final String temp = input.getText().toString();
                if (temp != null) {
                    if (temp.length() > 0) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ((MainActivity) getActivity()).newEntry(temp);
                            }
                        }, 333);
                    }
                } else {
                    input.setError("Cannot be empty.");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        WebViewActivity.launch(getActivity(), urls[position]);
    }

}