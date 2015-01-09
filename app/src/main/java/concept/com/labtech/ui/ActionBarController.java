package concept.com.labtech.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import concept.com.labtech.R;
import concept.com.labtech.activities.TabActivity;

/**
 * Created by alex on 1/8/15.
 */
public class ActionBarController implements AdapterView.OnItemClickListener
{
    private static final String[] drawerStrings = {
            "Demographics", "Established Diagnoses", "Sino-pulmonary symptoms", "Situs Abnormalities", "Fertility Problems"};

    private TabActivity context;
    private View actionBar;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarToggle;

    public ActionBarController(TabActivity context)
    {
        this(context, drawerStrings);
//        this(context, null);
    }

    public ActionBarController(TabActivity context, String[] drawerList)
    {
        this.context = context;
        toolbar = (Toolbar) context.findViewById(R.id.toolbar);
        actionBar = LayoutInflater.from(context).inflate(R.layout.action_bar, toolbar, false);
        drawer = (DrawerLayout) context.findViewById(R.id.drawer_layout);
        actionBarToggle = new ActionBarDrawerToggle(context, drawer, toolbar, R.string.empty, R.string.empty);

        if (drawerList == null) {

//            TODO

        } else {
            prepareToDisplay(context);
            setDrawerView();
        }
    }

    private void prepareToDisplay(TabActivity context)
    {
        context.setSupportActionBar(toolbar);
        toolbar.addView(actionBar);
        actionBarToggle.syncState();
        setDrawerView();

//        ViewCompat.setElevation(toolbar, 5f);
//        drawer.setScrimColor(Color.parseColor("#66000000"));
    }

    private void setDrawerView()
    {
        final ListView mDrawerList = (ListView) context.findViewById(R.id.list_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<>(context, R.layout.drawer_item, drawerStrings));
        mDrawerList.setOnItemClickListener(this);
    }

    private String getDrawerTitle()
    {
        String brand = Build.BRAND.substring(0, 1).toUpperCase() + Build.BRAND.substring(1);
        return brand + " " + Build.MODEL;
    }

    public void closeDrawer()
    {
        this.drawer.closeDrawers();
    }

    public void onPostCreate()
    {
        actionBarToggle.syncState();
    }

    public void onStop()
    {
        drawer.closeDrawers();
    }

    // experimental
    public void onConfigurationChanged(Configuration newConfig)
    {
        actionBarToggle.onConfigurationChanged(newConfig);
    }

    public void setActionBarTitle(String title)
    {
        ((TextView) actionBar.findViewById(R.id.action_bar_title)).setText(title);
    }

    public boolean isDrawerOpen()
    {
        return drawer.isDrawerOpen(GravityCompat.START);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        drawer.closeDrawers();
//        ((DrawerClickListener) context).drawerListClick(position);
    }
}