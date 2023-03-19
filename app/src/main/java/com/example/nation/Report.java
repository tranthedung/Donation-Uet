package com.example.nation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module.Donation;

import java.util.List;

public class Report extends Base  {
    ListView listView;
    DonationAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_report);

        listView = (ListView) findViewById(R.id.reportList);
        adapter = new DonationAdapter(this, app.dbManager.getAll());
        listView.setAdapter(adapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuReport:
                Toast.makeText(this, "Report Selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Report.class));
                break;

            case R.id.menuDonate:
                Toast.makeText(this, "Donate Selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Donate.class));
                break;

            default:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_donate, menu);
        return true;
    }
}


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_donate, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected( MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.menuDonate: startActivity(new Intent(this, Donate.class));
//            break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

class DonationAdapter extends ArrayAdapter<Donation>{
    private Context context;
    public List<Donation> donations;
    public DonationAdapter(@NonNull Context context, List<Donation> donations){
        super(context, R.layout.row_donate, donations);
        this.context = context;
        this.donations = donations;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_donate, parent, false);
        Donation donation = donations.get(position);
        TextView amountView = view.findViewById(R.id.row_amount);
        TextView methodView = view.findViewById(R.id.row_method);
        amountView.setText("$" + donation.getAmount());
        methodView.setText( donation.getMethod());
        return view;
    }
    @Override
    public int getCount(){
        return donations.size();
    }
}