package com.example.nation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.module.Donation;

public class Donate extends Base {
    Button donatebutton;
    RadioGroup paymentMethod;
    ProgressBar progressBar;
    NumberPicker amountpicker;
    TextView donationtitle, donationsubtitle;
    RadioButton paynal, direct;
    EditText amountText;
    TextView amountTotal;
    public int totalDonated = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();
//        if (donatebutton != null) {
//            Log.v("Donate", "Really got the donate button");
//        }
        amountpicker.setMinValue(0);
        amountpicker.setMaxValue(1000);
        progressBar.setMax(10000);
        amountTotal.setText("$"+ app.totalDonated);


    }
////////
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_donate, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        switch (id)
//        {
//            case R.id.menuReport:
//                return false;
//            case R.id.menuDonate:
//                return false;
//            case R.id.menuReset:
//                return false;
//        }
//        return super.onOptionsItemSelected(item);
//    }
///////
    public void Anhxa() {
        donatebutton = (Button) findViewById(R.id.donateButton);
        paymentMethod = (RadioGroup) findViewById(R.id.paymentMethod);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        amountpicker = (NumberPicker) findViewById(R.id.amountPicker);
        donationtitle = (TextView) findViewById(R.id.donateTitle);
        donationsubtitle = (TextView) findViewById(R.id.donateSubtitle);
        paynal = (RadioButton) findViewById(R.id.Paypal);
        direct = (RadioButton) findViewById(R.id.Direct);
        amountText = (EditText) findViewById(R.id.paymentAmount);
        amountTotal = (TextView) findViewById(R.id.totalsofar);
    }

    public void donateButtonPressed(View view) {
        String method = paymentMethod.getCheckedRadioButtonId() == R.id.Paypal ? "PayPal" : "Direct";
        int donatedAmount = amountpicker.getValue();
        if (donatedAmount == 0) {
            String text = amountText.getText().toString();
            if (!text.equals("")) {
                donatedAmount = Integer.parseInt(text);
            }
        }
        if (donatedAmount > 0) {

            app.newDonation(new Donation(donatedAmount, method));
//            Donation donation = new Donation(donatedAmount, method);
            progressBar.setProgress(app.totalDonated);
            String totalDonatedStr = "$" + app.totalDonated;
            amountTotal.setText(totalDonatedStr);
        }
    }
    @Override
    public void reset(MenuItem item)
    {
        app.dbManager.reset();
        app.totalDonated = 0;
        progressBar.setProgress(0);
        amountTotal.setText("$" + totalDonated);


        // Your implementation goes here
    }
}



