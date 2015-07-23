package intership.dev.contact.fragments;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import intership.dev.contact.R;
import intership.dev.contact.model.User;
import intership.dev.contact.utils.ContactListAdapter;
import intership.dev.contact.utils.ContactDBHelper;
import intership.dev.contact.utils.LoadMoreListView;

/**
 * This class is used to display list of contacts
 * Created by hodachop93 on 21/07/2015.
 */
public class ContactsFragment extends Fragment {

    //Listview is used to display a list of contacts
    private LoadMoreListView mListView;

    //The list of users
    private List<User> mUsers;

    //The adapter of the listview
    private ContactListAdapter mAdapter;

    //The object used to control data in the database
    private ContactDBHelper mDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*mUsers = new ArrayList<User>();
        for (int i = 0; i < 2; i++) {
            createDefaultData();
        }

        //Add data to database
        ContactDBHelper db = new ContactDBHelper(getActivity());
        for (User user : mUsers){
            db.addUser(user);
        }*/
        mDbHelper = new ContactDBHelper(getActivity());
        int size = mDbHelper.getAllUsers().size();
        if (mDbHelper.getAllUsers().size() == 0) {
            mUsers = new ArrayList<User>();
            for (int i = 0; i < 2; i++) {
                createDefaultData();
            }
        } else {
            mUsers = mDbHelper.getAllUsers();
            TypedArray avatars = getResources().obtainTypedArray(R.array.list_avatar);
            for (int i = 0; i < mUsers.size(); i++) {
                User user = mUsers.get(i);
                Bitmap avatar = BitmapFactory.decodeResource(getResources(),
                        avatars.getResourceId(user.getIdAvatar(), -1));
                user.setAvatar(avatar);
            }
        }
        mAdapter = new ContactListAdapter(getActivity(), mUsers);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        mListView = (LoadMoreListView) rootView.findViewById(R.id.lvContacts);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mListView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new LoadDataTask().execute();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void createDefaultData() {
        String[] names = getResources().getStringArray(R.array.list_name);
        TypedArray avatars = getResources().obtainTypedArray(R.array.list_avatar);
        int size = names.length;
        for (int i = 0; i < size; i++) {
            Bitmap avatar = BitmapFactory.decodeResource(getResources(), avatars.getResourceId(i, -1));
            String name = names[i];
            String des = "";
            User user = new User(mUsers.size(), avatar, name, des, i);
            mUsers.add(user);
            mDbHelper.addUser(user);
        }
    }

    /**
     * Load more data AsyncTask
     */
    private class LoadDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            if (isCancelled()) {
                return null;
            }

            // Simulates a background task
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // load more data
            createDefaultData();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            // We need notify the adapter that the data have been changed
            mAdapter.notifyDataSetChanged();

            // Call onLoadMoreComplete when the LoadMore task, has finished
            mListView.onLoadMoreComplete();

            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            // Notify the loading more operation has finished
            mListView.onLoadMoreComplete();
        }
    }
}
