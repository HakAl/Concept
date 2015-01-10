package concept.com.labtech.ui;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import concept.com.labtech.R;
import concept.com.labtech.ui.callbacks.DrawerClickListener;

/**
 * Created by alex on 1/8/15.
 */
public class ActionBarController implements AdapterView.OnItemClickListener
{
    @Inject
    Picasso picasso;

    private MainActivity context;
    private View actionBar;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarToggle;

    public ActionBarController(MainActivity context)
    {
        context.inject(this);
        this.context = context;
        this.toolbar = (Toolbar) context.findViewById(R.id.toolbar);
        prepareToDisplay(context);
    }

    private void prepareToDisplay(MainActivity context)
    {
        context.setSupportActionBar(toolbar);
        this.actionBar = LayoutInflater.from(context).inflate(R.layout.actionbar, toolbar, false);
        toolbar.addView(actionBar);
        this.drawer = (DrawerLayout) context.findViewById(R.id.drawer_layout);
//        this.drawer.setScrimColor(Color.parseColor("#00000000"));
        this.drawer.setScrimColor(Color.parseColor("#aa111111"));
        this.actionBarToggle = new ActionBarDrawerToggle(context, drawer, toolbar, R.string.empty, R.string.empty);
        actionBarToggle.syncState();
        final ListView mDrawerList = (ListView) context.findViewById(R.id.list_drawer);
        String[] titles = context.getResources().getStringArray(R.array.drawer_list);
        mDrawerList.setAdapter(new ArrayAdapter<>(context, R.layout.drawer_item, titles));
        mDrawerList.setOnItemClickListener(this);
//        header.post(new Runnable() {
//            @Override
//            public void run() {
//            }
//        });
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

    public void openDrawer()
    {
        this.drawer.openDrawer(Gravity.LEFT);
    }

    public void onPostCreate()
    {
        actionBarToggle.syncState();
    }

    public void onStop()
    {
        drawer.closeDrawers();
        context = null;
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
        ((DrawerClickListener) context).drawerListClick(position);
    }
}