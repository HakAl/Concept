package concept.com.labtech.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import concept.com.labtech.R;

/**
 * Created by alex on 1/10/15.
 */
public class NewPatientDialog extends DialogFragment {

    public static NewPatientDialog newInstance()
    {
        return new NewPatientDialog();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), DialogFragment.STYLE_NO_FRAME);
        View view = inflater.inflate(R.layout.patient_entry_dialog, new LinearLayout(getActivity()), false);
        return builder.setView(view).create();
    }
}
