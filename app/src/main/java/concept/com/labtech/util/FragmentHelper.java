package concept.com.labtech.util;

import android.app.Fragment;
import android.app.FragmentManager;

public class FragmentHelper
{
    public static final String DIALOG_FRAGMENT = "DialogFragment";
    public static final String MAIN_FRAGMENT = "MainFragment";
    public static final String PATIENT_FRAGMENT = "PatientFragment";

    public static void addFragment(FragmentManager fm, Fragment fragment, String tag)
    {
        try {
            fm.beginTransaction()
                    .add(android.R.id.content, fragment, tag)
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) { }
    }

    public static void addFragment(FragmentManager fm, Fragment fragment, int containerResId, String tag)
    {
        try {
            fm.beginTransaction()
                    .add(containerResId, fragment, tag)
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) { }
    }

    public static void replaceFragment(FragmentManager fm, Fragment fragment, int containerResId, String tag)
    {
        try {
            fm.beginTransaction()
                    .replace(containerResId, fragment, tag)
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) { }
    }

    public static void removeFragment(FragmentManager fragmentManager, String tag)
    {
        Fragment fragmentToRemove = fragmentManager.findFragmentByTag(tag);
        if (fragmentToRemove != null) {
            fragmentManager.beginTransaction().remove(fragmentToRemove).commit();
        }
    }
}