package concept.com.labtech.injection;

import java.util.List;
import dagger.ObjectGraph;
import java.util.ArrayList;
import java.util.Collections;
import android.app.Application;

/** bootstrap -- allows no-arg constructor in AndroidAppModule
 **/
public class DaggerApplication extends Application implements DaggerInjector {

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidAppModule sharedAppModule = new AndroidAppModule();
        sharedAppModule.sApplicationContext = this.getApplicationContext();

        List<Object> modules = new ArrayList<Object>();
        modules.add(sharedAppModule);
        modules.addAll(getAppModules());

        mObjectGraph = ObjectGraph.create(modules.toArray());
        mObjectGraph.inject(this);
    }

    protected List<Object> getAppModules() {
        return Collections.<Object>singletonList(new ApplicationScopeModule());
    }

    @Override
    public void inject(Object object) {
        mObjectGraph.inject(object);
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }
}