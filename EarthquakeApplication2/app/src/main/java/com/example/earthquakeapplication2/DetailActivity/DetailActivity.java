// Student Name: Timothy Mulindwa Student ID: S1903348

package com.example.earthquakeapplication2.DetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.earthquakeapplication2.R;

public class DetailActivity extends AppCompatActivity {

    TextView titleTxt,descTxt,dateTxt,guidTxt,linkTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        titleTxt= (TextView) findViewById(R.id.titleDetailTxt);
        descTxt= (TextView) findViewById(R.id.descDetailTxt);
        dateTxt= (TextView) findViewById(R.id.dateDetailTxt);
        guidTxt= (TextView) findViewById(R.id.guidDetailTxt);
        linkTxt= (TextView) findViewById(R.id.linkDetailTxt);

        Intent i=this.getIntent();

        String title=i.getExtras().getString("TITLE_KEY");
        String desc=i.getExtras().getString("DESC_KEY");
        String date=i.getExtras().getString("DATE_KEY");
        String guid=i.getExtras().getString("GUID_KEY");
        String link=i.getExtras().getString("LINK_KEY");

        titleTxt.setText(title);
        descTxt.setText(Html.fromHtml(desc));
        dateTxt.setText(date);
        guidTxt.setText(guid);
        linkTxt.setText(link);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
