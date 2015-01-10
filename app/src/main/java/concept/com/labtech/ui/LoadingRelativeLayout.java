package concept.com.labtech.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import concept.com.labtech.R;

public class LoadingRelativeLayout extends RelativeLayout
{
    public LoadingRelativeLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.component_progress, this);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LoadingRelativeLayout,
                0, 0);

        try {
            String loadingText = a.getString(R.styleable.LoadingRelativeLayout_loadingText);
            ((TextView) this.findViewById(R.id.txt_progress_title)).setText(loadingText);
        } finally {
            a.recycle();
        }
    }

    public LoadingRelativeLayout(Context context, String loadingText)
    {
        super(context);
        this.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        LayoutInflater.from(context).inflate(R.layout.component_progress, this);
        ((TextView) this.findViewById(R.id.txt_progress_title)).setText(loadingText);
    }

    /**
     * Set the layout view into a loading state
     *
     * @param loadingText The text to display while loading
     */
    public void setLoadingView(String loadingText)
    {
        this.inflateLoadingView();
        ((TextView) this.findViewById(R.id.txt_progress_title)).setText(loadingText);
    }

    public void clearLoadingView()
    {
        this.removeView(LayoutInflater.from(getContext()).inflate(R.layout.component_progress, this));
    }

    public void updateView(int layoutId)
    {
        this.removeAllViews();
        LayoutInflater.from(getContext()).inflate(layoutId, this);
    }

    public void updateView(View update)
    {
        this.removeAllViews();
        this.addView(update);
    }
//
//    public void setEmptyView(String text, String btnText, OnClickListener clickListener)
//    {
//        this.removeAllViews();
//        LayoutInflater.from(getContext()).inflate(R.layout.empty_action, this);
//        ((TextView) this.findViewById(R.id.txt_empty_action)).setText(text);
//        Button emptyBtn = (Button) this.findViewById(R.id.btn_empty_action);
//        emptyBtn.setText(btnText);
//        emptyBtn.setOnClickListener(clickListener);
//    }

    private View inflateLoadingView()
    {
        return LayoutInflater.from(getContext()).inflate(R.layout.component_progress, this);
    }
}
