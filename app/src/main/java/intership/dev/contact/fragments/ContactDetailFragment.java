package intership.dev.contact.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import intership.dev.contact.R;
import intership.dev.contact.model.User;
import intership.dev.contact.utils.CircleImageView;

/**
 * Create a contact detail fragment when button edit on item of list contacts
 * is clicked
 * Created by hodachop93 on 22/07/2015.
 */
public class ContactDetailFragment extends Fragment implements View.OnClickListener {
    //The user in a list contacts that is given from ContactsFragment
    private User mUser;

    //The position of a user in list contacts that is given from ContactsFragment
    private int mPosition;

    private EditText mEdtName;
    private EditText mEdtDescription;
    private TextView mTvName;
    private Button mBtnSave;
    private Button mBtnCancel;
    private ImageView mImgAvatar;

    //Listener used to dispatch changed user at the position event
    private OnChangeItemListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_detail_fragment, container, false);

        mImgAvatar = (CircleImageView) rootView.findViewById(R.id.imgAvatar);
        mTvName = (TextView) rootView.findViewById(R.id.tvname);
        mEdtName = (EditText) rootView.findViewById(R.id.edtName);
        mEdtDescription = (EditText) rootView.findViewById(R.id.edtDescription);
        mBtnSave = (Button) rootView.findViewById(R.id.btnSave);
        mBtnCancel = (Button) rootView.findViewById(R.id.btnCancel);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = this.getArguments();
        mUser = (User) bundle.getSerializable("user");
        mPosition = bundle.getInt("position");

        mImgAvatar.setImageBitmap(mUser.getAvatar());
        mTvName.setText(mUser.getUserName());
        mEdtName.setText(mUser.getUserName());
        mEdtDescription.setText(mUser.getDescription());

        mBtnSave.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == mBtnSave.getId()) {
            mUser.setUserName(mEdtName.getText().toString());
            mUser.setDescription(mEdtDescription.getText().toString());

            mListener.onChange(mUser, mPosition);
            getActivity().onBackPressed();
        }
        if (id == mBtnCancel.getId()) {
            getActivity().onBackPressed();
        }
    }

    /**
     * Register a callback to be invoked when a changed user was given from
     * ContactDetailFragment to ContactsFragment
     *
     * @param listener The callback will run
     */
    public void setOnChangeItemListener(OnChangeItemListener listener) {
        mListener = listener;
    }

    /**
     * Interface definition for a callback to be invoked when an item in list contacts
     * was changed
     */
    public interface OnChangeItemListener {
        void onChange(User user, int position);
    }
}
