package intership.dev.contact.fragments;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import intership.dev.contact.R;
import intership.dev.contact.model.User;
import intership.dev.contact.utils.ActionBarCustom;
import intership.dev.contact.utils.ContactListAdapter;

/**
 * This class is used to display list of contacts
 * Created by hodachop93 on 21/07/2015.
 */
public class ContactsFragment extends Fragment {
    private ActionBarCustom mActionBar;

    //Listview is used to display a list of contacts
    private ListView mListView;

    //The list of users
    private List<User> mUsers;

    //The adapter of the listview
    private ContactListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        mActionBar = (ActionBarCustom) rootView.findViewById(R.id.actionbar);
        mListView = (ListView) rootView.findViewById(R.id.lvContacts);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mUsers = new ArrayList<User>();
        for (int i = 0; i < 20; i++) {
            createDefaultData();
        }
        mAdapter = new ContactListAdapter(getActivity(), mUsers);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void createDefaultData() {
        String[] names = getResources().getStringArray(R.array.list_name);
        TypedArray avatars = getResources().obtainTypedArray(R.array.list_avatar);
        int size = names.length;
        for (int i = 0; i < size; i++) {
            Bitmap avatar = BitmapFactory.decodeResource(getResources(), avatars.getResourceId(i, -1));
            String name = names[i];
            String des = "";
            User user = new User(avatar, name, des);
            mUsers.add(user);
        }
    }
}
