package com.ftovaro.articlereader.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ftovaro.articlereader.R;
import com.ftovaro.articlereader.adapters.ViewPagerAdapter;
import com.ftovaro.articlereader.fragments.FeedFragment;
import com.ftovaro.articlereader.fragments.SettingsDialogFragment;
import com.ftovaro.articlereader.fragments.WebFragment;
import com.ftovaro.articlereader.interfaces.CommunicatorFragmentListener;
import com.ftovaro.articlereader.interfaces.WebURLLoaderListener;
import com.ftovaro.articlereader.interfaces.WebURLSenderListener;
import com.ftovaro.articlereader.interfaces.SettingsDialogListener;

/**
 * Main class of the app, contains the fragments that shows all the information and helps to
 * intercommunicate the fragments with each other.
 * Created by FelipeTovar on 09-Mar-16.
 */
public class MainActivity extends AppCompatActivity implements SettingsDialogListener, WebURLSenderListener {


    private CommunicatorFragmentListener fragmentListener;
    private WebURLLoaderListener urlLoaderListener;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_rss_feed,
            R.drawable.ic_description
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment) {
        super.onAttachFragment(fragment);
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            if(fragment instanceof FeedFragment){
                try {
                    fragmentListener = (CommunicatorFragmentListener) fragment;
                }catch (ClassCastException e){
                    // The activity doesn't implement the interface, throw exception
                    throw new ClassCastException(fragment.toString()
                            + " must implement CommunicatorFragmentListener");
                }
            } else if(fragment instanceof WebFragment){
                try {
                    urlLoaderListener = (WebURLLoaderListener) fragment;
                }catch (ClassCastException e){
                    // The activity doesn't implement the interface, throw exception
                    throw new ClassCastException(fragment.toString()
                            + " must implement WebURLLoaderListener");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            DialogFragment newFragment = new SettingsDialogFragment();
            newFragment.show(getSupportFragmentManager(), "");
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Adds to the viewpager the fragments that are going to be shown in tabs.
     * @param viewPager the pager for the tabs.
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FeedFragment(), "Feed");
        adapter.addFragment(new WebFragment(), "Article");
        viewPager.setAdapter(adapter);
    }

    /**
     * Set the icons for each tab.
     */
    private void setupTabIcons() {
        try{
            tabLayout.getTabAt(0).setIcon(tabIcons[0]);
            tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        }catch (NullPointerException e){
            Log.d("Error", e.getMessage());
        }
    }

    @Override
    public void onDialogPositiveClick(boolean switchStatus) {
        fragmentListener.changeCardsColors(switchStatus);
    }

    @Override
    public void sendURL(String url) {
        urlLoaderListener.loadURL(url);
        viewPager.setCurrentItem(1, true);
    }
}
