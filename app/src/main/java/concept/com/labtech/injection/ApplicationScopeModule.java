package concept.com.labtech.injection;

import dagger.Module;
import dagger.Provides;
import android.content.Context;
import com.squareup.picasso.Picasso;

/** Provides Application scope dependencies.
 ** Enables object graph validation.
 ** addsTo() is important for object graph validation at compile time.
 **/

@Module (
            complete = true,
            library = true,
            addsTo = AndroidAppModule.class,
            injects = { DaggerApplication.class })

public class ApplicationScopeModule
{
    @Provides
    Picasso providesPicasso(@ForApplication Context context) {
        return Picasso.with(context);
    }
}