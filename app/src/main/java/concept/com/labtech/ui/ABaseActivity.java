package concept.com.labtech.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import concept.com.labtech.injection.ActivityScopeModule;
import concept.com.labtech.injection.DaggerApplication;
import concept.com.labtech.injection.DaggerInjector;
import dagger.ObjectGraph;


/** Create the activity graph by .plus-ing our modules onto the application graph.
 ** Inject ourselves so subclasses will have dependencies fulfilled when this method returns. **/
public abstract class ABaseActivity extends ActionBarActivity implements DaggerInjector {
    private ObjectGraph mActivityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerApplication application = (DaggerApplication) getApplication();
        mActivityGraph = application.getObjectGraph().plus(geActivitytModules());
        mActivityGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        mActivityGraph = null;
        super.onDestroy();
    }

    /** Inject the supplied {@code object} using the activity-specific graph. **/
    @Override
    public void inject(Object object) {
        mActivityGraph.inject(object);
    }

    public ObjectGraph getObjectGraph() {
        return mActivityGraph;
    }

    protected Object[] geActivitytModules() {
        return new Object[]{
                new ActivityScopeModule(this),
        };
    }

    protected <T> T find(int id) {
        return (T) findViewById(id);
    }
}
