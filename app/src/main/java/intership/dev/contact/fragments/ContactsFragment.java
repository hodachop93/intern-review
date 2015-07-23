package intership.dev.contact.fragments;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import intership.dev.contact.R;
import intership.dev.contact.model.User;
import intership.dev.contact.utils.ContactDBHelper;
import intership.dev.contact.utils.ContactListAdapter;
import intership.dev.contact.utils.LoadMoreListView;

/**
 * This class is used to display list of contacts
 * Created by hodachop93 on 21/07/2015.
 */
public class ContactsFragment extends Fragment {
    //The number of items will be loaded more when loading more
    //more list contacts
    private final int NUMBER_DATA_GET_MORE = 8;

    //The number of items will be loaded when application run
    private final int NUMBER_FIRST_ITEMS = 8;

    //The number times will be looped when creating default data
    private final int FACTOR_DATA = 6;

    //Listview is used to display a list of contacts
    private LoadMoreListView mListView;

    //The list of users
    private List<User> mUsers;

    //The adapter of the listview
    private ContactListAdapter mAdapter;

    //The object used to control data in the database
    private ContactDBHelper mDbHelper;

    //The number of items in list contacts
    private int mCurNumItems;

    //Inform when no more data to load
    private boolean mIsOverData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create database if it is not exist
        mDbHelper = new ContactDBHelper(getActivity());
        mUsers = new ArrayList<User>();

        if (mDbHelper.getAllUsers().size() == 0) {
            for (int i = 0; i < FACTOR_DATA; i++) {
                createDefaultData(i);
            }
        }
        mCurNumItems = 0;

        try {
            getDataForContacts(mCurNumItems, mCurNumItems + NUMBER_FIRST_ITEMS);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }

        mIsOverData = false;
        mAdapter = new ContactListAdapter(getActivity(), mUsers);
    }

    /**
     * Get first data for list contacts
     */
    private void getDataForContacts(int start, int end) throws IndexOutOfBoundsException {
        //Get  8 first items in database
        mCurNumItems = start;
        List<User> userList = mDbHelper.getAllUsers();
        TypedArray avatars = getResources().obtainTypedArray(R.array.list_avatar);
        for (int i = start; i < end; i++) {
            User user = userList.get(i);
            Bitmap avatar = BitmapFactory.decodeResource(getResources(),
                    avatars.getResourceId(user.getIdAvatar(), -1));
            user.setAvatar(avatar);

            mUsers.add(user);
        }
        mCurNumItems = end;
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
                if (!mIsOverData){
                    //If data is not over
                    new LoadDataTask().execute();
                } else {
                    //If data is over
                    mListView.onLoadMoreComplete();
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Create default data for application
     *
     * @param factor The multiplicative factor
     */
    private void createDefaultData(int factor) {
        String[] names = getResources().getStringArray(R.array.list_name);
        TypedArray avatars = getResources().obtainTypedArray(R.array.list_avatar);
        String[] desList = getResources().getStringArray(R.array.list_description);
        int size = names.length;
        for (int i = 0; i < size; i++) {
            Bitmap avatar = BitmapFactory.decodeResource(getResources(), avatars.getResourceId(i, -1));
            String name = names[i];
            String des = desList[i];
            User user = new User(factor * size + i, avatar, name, des, i);
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

            // Simulates a background task
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // load more data
            try {
                getDataForContacts(mCurNumItems, mCurNumItems + NUMBER_DATA_GET_MORE);
            } catch (IndexOutOfBoundsException ex) {
                mIsOverData = true;
                final FragmentActivity activity = getActivity();
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(activity, "No more data to load", Toast.LENGTH_SHORT).show();
                    }
                });

            }

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

    }
}
