package concept.com.labtech.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;

/**
 * Usage:
 *          DialogHelper.modal().show();
 */
public class DialogHelper implements DialogInterface.OnClickListener
{
    private DialogCallback listener;

    private DialogHelper()
    {
    }

    public static DialogHelper modal()
    {
        return new DialogHelper();
    }

    public void show(Context context, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setCancelable(true)
                .setPositiveButton(context.getString(android.R.string.ok), getListener());
        builder.create().show();
    }

    public void show(DialogCallback listener, String message, String positiveBtn, String negativeBtn)
    {
        this.listener = listener;
        AlertDialog.Builder builder = new AlertDialog.Builder((Context) listener);
        builder.setMessage(message)
                .setCancelable(true)
                .setNegativeButton(negativeBtn, this)
                .setPositiveButton(positiveBtn, this);
        builder.create().show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        dialog.dismiss();
        switch (which) {
            case BUTTON_POSITIVE:
                listener.onResult(BUTTON_POSITIVE);
                break;
            case BUTTON_NEGATIVE:
                listener.onResult(BUTTON_NEGATIVE);
                break;
        }
    }

    private DialogInterface.OnClickListener getListener()
    {
        return new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        };
    }

    public interface DialogCallback
    {
        public void onResult(int which);
    }
}