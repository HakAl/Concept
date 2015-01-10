package concept.com.labtech.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import concept.com.labtech.R;

public class MainActivity extends ABaseActivity implements ActionBarController.DrawerClickListener {

    private ActionBarController actionBarController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.inject(this);
        setContentView(R.layout.activity_main);
        this.actionBarController = new ActionBarController(this);
        if (savedInstanceState == null) {
//            TODO
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarController.onPostCreate();
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
        //todo
    }

    public void setWindowTitle(String title) {
        actionBarController.setActionBarTitle(title);
    }
}
