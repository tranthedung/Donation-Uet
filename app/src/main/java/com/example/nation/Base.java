package com.example.nation;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.database.DBManager;
import com.example.main.DonationApp;
import com.example.module.Donation;


import java.util.ArrayList;
import java.util.List;


public class Base extends AppCompatActivity {

//    public final int target = 10000;
//    public int totalDonated = 0;
//    public static List<Donation> donations = new ArrayList<Donation>();
//
//
//    public boolean newDonation(Donation donation) {
//        boolean targetAchieved = totalDonated > target;
//        if (!targetAchieved) {
//            donations.add(donation);
//            totalDonated = totalDonated + donation.amount;
//        } else {
//            Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT).show();
//        }
//        return targetAchieved;
//    }
    public DonationApp app;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        app = (DonationApp) getApplication();
        app.dbManager.open();
        app.dbManager.setTotalDonated(this);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        app.dbManager.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_donate, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem report = menu.findItem(R.id.menuReport);
        MenuItem donate = menu.findItem(R.id.menuDonate);
        MenuItem reset  = menu.findItem(R.id.menuReset);

        if (app.donations.isEmpty()) {
            report.setEnabled(false);
            reset.setEnabled(false);
        } else {
            report.setEnabled(true);
            reset.setEnabled(true);

            donate.setEnabled(true);
        }
        if (this instanceof Donate ) {
            donate.setVisible(false);
            if (!app.donations.isEmpty()) {
                report.setVisible(true);
                reset.setVisible(true);
            }
        } else {
            report.setVisible(false);
            donate.setVisible(true);
            reset.setVisible(false);
        }
            return true;
        }
        public void settings(MenuItem item){
            Toast.makeText(this, "Settings Select", Toast.LENGTH_SHORT).show();
        }
        public void report(MenuItem item){
            startActivity(new Intent(this, Report.class));
        }
        public void donate(MenuItem item){
            startActivity(new Intent(this, Report.class));
        }
        public void reset(MenuItem item){
        app.dbManager.reset();
        app.totalDonated = 0;
        progressBar.setProgress(0);
        }


}


