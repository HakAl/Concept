package concept.com.labtech.injection;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.location.LocationManager;

/**
 * Allow the application context to be injected but require that it be annotated with
 * { com.jacmobile.daggerframework.injection.annotations.ForApplication @Annotation} to
 * explicitly differentiate it from an activity context.
 */
@Module (
            complete = false,
            library = true,
            injects = { })

public class AndroidAppModule
{

    /* package */ static Context sApplicationContext = null;

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext()
    {
        return sApplicationContext;
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager()
    {
        return (LocationManager) sApplicationContext.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater()
    {
        return LayoutInflater.from(sApplicationContext);
    }


    @Provides @Singleton Handler provideHandler()
    {
        return new Handler(Looper.getMainLooper());
    }
}