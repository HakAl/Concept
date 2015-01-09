package concept.com.labtech.fragments;

import javax.inject.Inject;
import concept.com.labtech.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainFragment extends ABaseFragment
{
    @Inject Picasso picasso;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static MainFragment newInstance(int sectionNumber) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private int one = 0xf00;
    private int two = 0xfF0;
    private int three = 0x00f;

    int getInt()
    {
        double d = Math.random();
        if (d < .34) {
            return one;
        }
        if (d < .67) {
            return two;
        }
        return three;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        TextView tv = (TextView) rootView.findViewById(R.id.section_label);
        tv.setText("1, 2, 3: " + getInt());
        return rootView;
    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState)
//    {
//        super.onViewCreated(view, savedInstanceState);
//
//        String imageUrl = "http://cdn1.smosh.com/sites/default/files/legacy.images/smosh-pit/092010/philosoraptor-synonym.jpeg";
//
//        ImageView imageView = getView(R.id.img_poc);
//        picasso.load(imageUrl).into(imageView);
//    }

}