package concept.com.labtech.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import concept.com.labtech.R;
import concept.com.labtech.ui.callbacks.DrawerClickListener;
import concept.com.labtech.util.FragmentHelper;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;
import static concept.com.labtech.util.FragmentHelper.MAIN_FRAGMENT;

public class MainActivity extends ABaseActivity implements DrawerClickListener, View.OnClickListener, DialogHelper.DialogCallback {

    //TODO remove
    private boolean isNewPatient = false;
    private ActionBarController actionBarController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.inject(this);

        setContentView(R.layout.activity_main);
        this.actionBarController = new ActionBarController(this);
        this.findViewById(R.id.fab).setOnClickListener(this);

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
        Toast.makeText(this, "Unimplemented Functionality", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                newEntry();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {

        if (actionBarController.isDrawerOpen()) {
            actionBarController.onStop();
        } else {
            if (isNewPatient) {
                isNewPatient = false;
            FragmentHelper.removeFragment(getFragmentManager(), FragmentHelper.PATIENT_FRAGMENT);
            FragmentHelper.replaceFragment(
                    getFragmentManager(),
                    PatientEntryFragment.newInstance(),
                    R.id.container,
                    FragmentHelper.MAIN_FRAGMENT);
            isNewPatient = false;
            } else {
                super.onBackPressed();
            }
        }
//        if (isNewPatient) {
//            FragmentHelper.removeFragment(getFragmentManager(), FragmentHelper.PATIENT_FRAGMENT);
//            FragmentHelper.replaceFragment(
//                    getFragmentManager(),
//                    PatientEntryFragment.newInstance(),
//                    R.id.container,
//                    FragmentHelper.MAIN_FRAGMENT);
//            isNewPatient = false;
//        }
//
//        super.onBackPressed();
    }

    public void setWindowTitle(String title) {
        actionBarController.setActionBarTitle(title);
    }

    public void toggleFabVisiblility() {
        if (this.findViewById(R.id.fab).getVisibility() == View.VISIBLE) {
            this.findViewById(R.id.fab).setVisibility(View.INVISIBLE);
        } else {
            this.findViewById(R.id.fab).setVisibility(View.VISIBLE);
        }
    }

    private void newEntry() {
        View v = LayoutInflater.from(this).inflate(
                R.layout.patient_entry_dialog,
                (ViewGroup) findViewById(R.id.container), false);
//        v.findViewById(R.id.btn_number_dialog_negative).setOnClickListener(this);
//        v.findViewById(R.id.btn_number_dialog_positive).setOnClickListener(this);
        DialogHelper.modal().show(this, v, "OK", "CANCEL");
//        NewPatientDialog.newInstance().show(getFragmentManager(), "Patient");
    }

    @Override
    public void onResult(int which) {
        switch (which) {
            case BUTTON_POSITIVE:
                isNewPatient = true;
                this.setWindowTitle("New Patient");
                toggleFabVisiblility();
                FragmentHelper.removeFragment(getFragmentManager(), FragmentHelper.MAIN_FRAGMENT);
                FragmentHelper.replaceFragment(
                        getFragmentManager(),
                        PatientEntryFragment.newInstance(),
                        R.id.container,
                        FragmentHelper.PATIENT_FRAGMENT);
                break;
            case BUTTON_NEGATIVE:
//                Toast.makeText(this, "negative", Toast.LENGTH_LONG).show();
                break;
            default: break;
        }
    }
}
