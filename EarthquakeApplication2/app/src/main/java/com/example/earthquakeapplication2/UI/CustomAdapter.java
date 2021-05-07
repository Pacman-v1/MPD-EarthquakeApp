package com.example.earthquakeapplication2.UI;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.earthquakeapplication2.DataObject.Earthquake;
import com.example.earthquakeapplication2.R;
import com.example.earthquakeapplication2.DetailActivity.DetailActivity;


import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Earthquake> earthquakes;

    public CustomAdapter(Context c, ArrayList<Earthquake> earthquakes) {
        this.c = c;
        this.earthquakes = earthquakes;
    }

    @Override
    public int getCount() {
        return earthquakes.size();
    }

    @Override
    public Object getItem(int position) {
        return earthquakes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        }

        TextView titleTxt= (TextView) convertView.findViewById(R.id.titleTxt);
        TextView descTxt= (TextView) convertView.findViewById(R.id.descTxt);
        TextView dateTxt= (TextView) convertView.findViewById(R.id.dateTxt);

        Earthquake earthquake= (Earthquake) this.getItem(position);

        final String title=earthquake.getTitle();
        final String desc=earthquake.getDescription();
        final String date=earthquake.getDate();
        final String guid=earthquake.getGuid();
        final String link=earthquake.getLink();

        titleTxt.setText(title);
        descTxt.setText(Html.fromHtml(desc));
        dateTxt.setText(date);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DETAIL ACTIVITY
                openDetailActivity(title,desc,date,guid,link);
            }
        });

        return convertView;
    }
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(c, DetailActivity.class);

        i.putExtra("TITLE_KEY",details[0]);
        i.putExtra("DESC_KEY",details[1]);
        i.putExtra("DATE_KEY",details[2]);
        i.putExtra("GUID_KEY",details[3]);
        i.putExtra("LINK_KEY",details[4]);

        c.startActivity(i);
    }
}
