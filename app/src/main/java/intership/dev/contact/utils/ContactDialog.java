package intership.dev.contact.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import intership.dev.contact.R;
import intership.dev.contact.model.User;

/**
 * Create a dialog when clicking button delete in a contact item
 * in list contacts
 * Created by hodachop93 on 22/07/2015
 */
public class ContactDialog extends Dialog implements View.OnClickListener {
    private TextView mMessage;
    private Button mBtnOK;
    private Button mBtnCancel;
    private OnClickContactDialog mListener;

    public ContactDialog(Context context) {
        super(context, R.style.ContactDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_contact);
        mMessage = (TextView) findViewById(R.id.message);
        mBtnOK = (Button) findViewById(R.id.btnOK);
        mBtnCancel = (Button) findViewById(R.id.btnCancel);
        mBtnOK.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }

    public void setUser(User user) {
        String ques = "Are you sure you want to delete " +
                "<b>" + user.getUserName() + "</b>" + " ?";
        mMessage.setText(Html.fromHtml(ques));
    }

    /**
     * Register a callback to be invoked when this view is clicked.
     *
     * @param listener The callback that will run
     */
    public void setOnClickListViewContactListener(OnClickContactDialog listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener == null) {
            return;
        }
        int id = v.getId();
        if (id == mBtnOK.getId()) {
            mListener.onClickBtnOK(v);
        }
        if (id == mBtnCancel.getId()) {
            mListener.onClickBtnCancel(v);
        }
    }

    /**
     * Interface definition for a callback to be invoked when a button in
     * the contact dialog is clicked
     */
    public interface OnClickContactDialog {
        /**
         * Called when button OK has been clicked and then the user will be
         * removed from list contacts.
         *
         * @param v The view that was clicked.
         */
        void onClickBtnOK(View v);

        /**
         * Called when the button Cancel has been clicked and the list contacts
         * is not changed.
         *
         * @param v The view that was clicked.
         */
        void onClickBtnCancel(View v);
    }
}
