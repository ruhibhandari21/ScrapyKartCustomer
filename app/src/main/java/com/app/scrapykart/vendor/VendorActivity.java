package com.app.scrapykart.vendor;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.app.scrapykart.R;
import com.app.scrapykart.order.OrderActivity;


import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.Locale;

public class VendorActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private CategoryListingAdapter categoryListingAdapter;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        setUpNavigationView();

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        categoryListingAdapter = new CategoryListingAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(categoryListingAdapter);
    }

    public class CategoryListingAdapter extends RecyclerView.Adapter<CategoryListingAdapter.ViewHolder> {


        public class ViewHolder extends RecyclerView.ViewHolder {

            RatingBar ratingBar;
            Button btRateCard;

            public ViewHolder(View view) {
                super(view);
                ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
                btRateCard = (Button) view.findViewById(R.id.tvCheck);
            }
        }

        public CategoryListingAdapter() {

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.vendor_card, parent, false);

            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            holder.ratingBar.setRating(3);
            holder.btRateCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(VendorActivity.this, RateCardActivity.class);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return 7;
        }

    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
//                        navItemIndex = 0;
//                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_lang:
                        String lang = "hi";
                        Locale current = getResources().getConfiguration().locale;
                        if(current!= null){
                            if(current.getDisplayLanguage().equalsIgnoreCase("english")){
                                lang = "hi";
                            }else{
                                lang = "en_IN";
                            }
                        }
//                        Toast.makeText(VendorActivity.this,
//                                ""+current.getCountry()+" "+current.getDisplayLanguage(), Toast.LENGTH_SHORT).show();
//                        navItemIndex = 3;
//                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        Resources res = getResources();
// Change locale settings in the app.
                        DisplayMetrics dm = res.getDisplayMetrics();
                        android.content.res.Configuration conf = res.getConfiguration();
                        conf.setLocale(new Locale(lang)); // API 17+ only.
// Use conf.locale = new Locale(...) if targeting lower versions
                        res.updateConfiguration(conf, dm);
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        break;
                    case R.id.nav_settings:
//                        navItemIndex = 4;
//                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_orders:
                        startActivity(new Intent(VendorActivity.this, OrderActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_logout:
//                        // launch new intent instead of loading fragment
//                        startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                        drawer.closeDrawers();
                        finish();
                        return true;
                    default:
//                        navItemIndex = 0;
                }

               /* //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);*/


                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }


}









