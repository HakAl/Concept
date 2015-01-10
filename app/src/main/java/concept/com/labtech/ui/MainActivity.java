package concept.com.labtech.ui;

import android.content.res.Configuration;
import android.os.Bundle;

import concept.com.labtech.R;
import concept.com.labtech.ui.callbacks.DrawerClickListener;
import concept.com.labtech.util.FragmentHelper;
import static concept.com.labtech.util.FragmentHelper.MAIN_FRAGMENT;

public class MainActivity extends ABaseActivity implements DrawerClickListener {

    private ActionBarController actionBarController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.inject(this);

        setContentView(R.layout.activity_main);
        this.actionBarController = new ActionBarController(this);

        if (savedInstanceState == null) {
            FragmentHelper.addFragment(
                    getFragmentManager(),
                    MainFragment.newInstance(),
                    R.id.container,
                    MAIN_FRAGMENT);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarController.onPostCreate();
        actionBarController.openDrawer();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        actionBarController.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onStop() {
        super.onStop();

        actionBarController.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        actionBarController = null;
    }

    @Override
    public void drawerListClick(int position) {
        //TODO
    }

    public void setWindowTitle(String title) {
        actionBarController.setActionBarTitle(title);
    }
}
