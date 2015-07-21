package intership.dev.contact;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by hodachop93 on 21/07/2015.
 */
public class ContactsFragment extends Fragment {
    private ActionBarCustom mActionBar;
    private ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_contacts, container, false);
        mActionBar = (ActionBarCustom) rootView.findViewById(R.id.actionbar);
        mListView = (ListView) rootView.findViewById(R.id.lvContacts);
        return rootView;
    }
}
