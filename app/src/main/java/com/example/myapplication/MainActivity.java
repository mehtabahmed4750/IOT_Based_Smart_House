package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //variable
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView card1;
    ImageView card2;
    ImageView card3;
    ImageView card4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout= findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        card1=findViewById(R.id.imageView1);
        card2=findViewById(R.id.imageView2);
        card3=findViewById(R.id.imageView3);
        card4=findViewById(R.id.imageView4);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.home);

    }
    public void card1(View view) {
        Intent i = new Intent(MainActivity.this, room1.class);
        startActivity(i);
    }
    public void card2(View view){
        Intent i=new Intent(MainActivity.this, room2.class);
        startActivity(i);
    }
    public void card3(View view){
        Intent i=new Intent(MainActivity.this, kitchen.class);
        startActivity(i);
    }
    public void card4(View view){
        Intent i=new Intent(MainActivity.this, water.class);
        startActivity(i);
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                break;
            case R.id.abouttus:
                Intent intent =new Intent(this, aboutus.class);
                startActivity(intent);
                break;
            case R.id.review:
                Intent intent0 =new Intent(this, Review.class);
                startActivity(intent0);
                break;
            case R.id.logout:
                Intent intent1 =new Intent(this,login.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.share:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(i.EXTRA_SUBJECT,"Check out this application");
                i.putExtra(i.EXTRA_TEXT,"Your application link here");
                startActivity(Intent.createChooser(i,"Share using:"));
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}