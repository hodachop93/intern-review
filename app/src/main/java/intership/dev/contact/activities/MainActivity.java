package intership.dev.contact.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;

import intership.dev.contact.fragments.ContactsFragment;
import intership.dev.contact.R;

/**
 * Main Activity that is launched when application start
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addContactsFragment();
    }

    /**
     * Add ContactsFragment to MainActivity
     */
    private void addContactsFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        ContactsFragment frag = new ContactsFragment();
        transaction.add(R.id.container, frag);
        transaction.commit();
    }

}
