package concept.com.labtech.injection;

import concept.com.labtech.ui.MainActivity;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import android.app.Activity;
import android.content.Context;
import concept.com.labtech.ui.ABaseActivity;
import concept.com.labtech.ui.MainFragment;

/**
 * Provides Activity lifecycle dependencies.
 * Here we enable object graph validation.
 * addsTo() is important for object graph validation at compile time.
 */
@Module (
            complete = true,
            library = true,
            addsTo = ApplicationScopeModule.class,
            injects = {
                    MainActivity.class,
                    MainFragment.class })

public class ActivityScopeModule {

    private final ABaseActivity mActivity;

    public ActivityScopeModule(ABaseActivity activity) {
        mActivity = activity;
    }

    @Provides
    @Singleton
    Activity providesActivity() {
        return mActivity;
    }

    @Provides
    @Singleton
    @ForActivity
    Context providesActivityContext() {
        return mActivity;
    }
}