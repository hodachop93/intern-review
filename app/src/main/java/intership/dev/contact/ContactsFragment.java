package intership.dev.contact;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * This class is used to display list of contacts
 * Created by hodachop93 on 21/07/2015.
 */
public class ContactsFragment extends Fragment {
    //Action Bar
    private ActionBarCustom mActionBar;

    //Listview is used to display a list of contacts
    private ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_contacts, container, false);
        mActionBar = (ActionBarCustom) rootView.findViewById(R.id.actionbar);
        mListView = (ListView) rootView.findViewById(R.id.lvContacts);
        return rootView;
    }
}
