package saptarshi.com.techtatva_1;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import io.realm.Realm;


public class SecondActivity extends AppCompatActivity {
    TextView textViewEmail;
    TextView textViewPhone;
    TextView textViewWebsite;
    TextView textViewStreet;
    TextView textViewSuite;
    TextView textViewCity;
    TextView textViewZipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Realm database = Realm.getDefaultInstance();
        int id= getIntent().getIntExtra("id",0);
        Details pos = database.where(Details.class).equalTo("id", id).findFirst();

        textViewEmail= (TextView)findViewById(R.id.email);
        textViewPhone= (TextView)findViewById(R.id.phone);
        textViewWebsite= (TextView)findViewById(R.id.website);
        textViewStreet=(TextView)findViewById(R.id.street);
        textViewSuite= (TextView)findViewById(R.id.suite) ;
        textViewCity= (TextView) findViewById(R.id.city);
        textViewZipcode=(TextView)findViewById(R.id.zipcode) ;


        textViewEmail.setText(pos.getEmail());
        textViewPhone.setText(pos.getPhone());
        textViewWebsite.setText(pos.getWebsite());
        textViewStreet.setText(pos.getAddress().getStreet());
        textViewSuite.setText(pos.getAddress().getSuite());
        textViewCity.setText(pos.getAddress().getCity());
        textViewZipcode.setText(pos.getAddress().getZipcode());

        setTitle(pos.getName());
        database.close();

    }
}
