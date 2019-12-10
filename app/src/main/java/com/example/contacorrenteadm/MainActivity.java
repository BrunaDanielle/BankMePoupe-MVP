package com.example.contacorrenteadm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contacorrenteadm.base.AddFragmentHandler;
import com.example.contacorrenteadm.base.BackButtonSupportFragment;
import com.example.contacorrenteadm.base.BaseFragment;
import com.example.contacorrenteadm.fragments.BankStatementFragment;
import com.example.contacorrenteadm.fragments.HomeFragment;
import com.example.contacorrenteadm.fragments.TransferFragment;
import com.example.contacorrenteadm.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;
    private AddFragmentHandler fragmentHandler;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private ImageView ivUser;
    private TextView tvNameUser;

    private String emailUser;
    private String nameUser;
    private Integer idUser;
    private double balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentHandler = new AddFragmentHandler(fragmentManager);
        fragmentManager.addOnBackStackChangedListener(backStackListener);

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        ivUser = headerView.findViewById(R.id.imgUser);
        tvNameUser = headerView.findViewById(R.id.nameUser);

        Intent i = getIntent();
        Bundle extras = i.getExtras();

        if (extras != null) {
            populateDetails(extras);
        } else {
            finish();
        }

        if (savedInstanceState == null) {
            add(HomeFragment.newInstance(),emailUser, idUser, balance);
            navigationView.setCheckedItem(R.id.nav_home);
        }
        setupDrawerAndToggle();
    }

    private void populateDetails(Bundle extras) {
        tvNameUser.setText(extras.getString("NameUser"));
        nameUser = extras.getString("NameUser");

        Picasso.with(this)
                .load(extras.getString("PhotoUser"))
                .fit().centerCrop()
                .into(ivUser);

        emailUser = extras.getString("EmailUser");
        idUser = extras.getInt("IdUser");
        balance = extras.getDouble("Balance");
    }

    private void setupDrawerAndToggle() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setDrawerIndicatorEnabled(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                showHomeFragment();
                break;
            case R.id.nav_extract:
                showBankStatementFragment();
                break;
            case R.id.nav_transfer:
                showBankTransferFragment();
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showBankStatementFragment() {
        add(BankStatementFragment.newInstance(),null,idUser,balance);
    }

    private void showHomeFragment() {
        add(HomeFragment.newInstance(), emailUser, idUser,balance);
    }

    private void showBankTransferFragment() {
        add(TransferFragment.newInstance(),nameUser, idUser, 0);
    }

    private final View.OnClickListener navigationBackPressListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fragmentManager.popBackStack();
        }
    };

    private final FragmentManager.OnBackStackChangedListener backStackListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            onBackStackChangedEvent();
        }
    };

    private void onBackStackChangedEvent() {
        syncDrawerToggleState();
    }

    private void syncDrawerToggleState() {
        ActionBarDrawerToggle drawerToggle = getDrawerToggle();
        if (drawerToggle == null) {
            return;
        }
        if (fragmentManager.getBackStackEntryCount() > 1) {
            drawerToggle.setDrawerIndicatorEnabled(false);
            drawerToggle.setToolbarNavigationClickListener(navigationBackPressListener);
        } else {
            drawerToggle.setDrawerIndicatorEnabled(true);
            drawerToggle.setToolbarNavigationClickListener(drawerToggle.getToolbarNavigationClickListener());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDrawer().addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                syncDrawerToggleState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                syncDrawerToggleState();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        fragmentManager.removeOnBackStackChangedListener(backStackListener);
        fragmentManager = null;
        super.onDestroy();
    }

    protected void add(BaseFragment fragment, String email, int value, double balance) {
        fragmentHandler.add(fragment,email, value, balance);
    }

    @Override
    public void onBackPressed() {
        if (sendBackPressToDrawer()) {
            return;
        }

        if (sendBackPressToFragmentOnTop()) {
            return;
        }

        super.onBackPressed();
    }

    private boolean sendBackPressToDrawer() {
        DrawerLayout drawer = getDrawer();
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    private boolean sendBackPressToFragmentOnTop() {
        BaseFragment fragmentOnTop = fragmentHandler.getCurrentFragment();
        if (fragmentOnTop == null) {
            return false;
        }
        if (!(fragmentOnTop instanceof BackButtonSupportFragment)) {
            return false;
        }
        return ((BackButtonSupportFragment) fragmentOnTop).onBackPressed();
    }

    protected ActionBarDrawerToggle getDrawerToggle() {
        return toggle;
    }

    protected DrawerLayout getDrawer() {
        return drawer;
    }
}
