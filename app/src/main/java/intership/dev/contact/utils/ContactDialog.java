package intership.dev.contact.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import intership.dev.contact.R;

/**
 * Create a dialog when clicking button delete in a contact item
 * in list contacts
 * Created by hodachop93 on 22/07/2015.
 */
public class ContactDialog extends Dialog {
    public ContactDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_contact);
    }
}
