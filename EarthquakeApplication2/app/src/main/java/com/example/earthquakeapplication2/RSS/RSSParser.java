// Student Name: Timothy Mulindwa Student ID: S1903348

package com.example.earthquakeapplication2.RSS;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.earthquakeapplication2.DataObject.Earthquake;
import com.example.earthquakeapplication2.UI.CustomAdapter;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RSSParser extends AsyncTask<Void,Void,Boolean> {

    Context c;
    InputStream is;
    ListView lv;

    ProgressDialog pd;
    ArrayList<Earthquake> earthquakes=new ArrayList<>();

    public RSSParser(Context c, InputStream is, ListView lv) {
        this.c = c;
        this.is = is;
        this.lv = lv;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Parse Earthquakes");
        pd.setMessage("Parsing...Please wait");
        pd.show();

    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseRSS();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        pd.dismiss();
        if(isParsed)
        {
            //BIND

            lv.setAdapter(new CustomAdapter(c,earthquakes));

        }else {
            Toast.makeText(c,"Unable To Parse",Toast.LENGTH_SHORT).show();
        }
    }
    private Boolean parseRSS()
    {
        try
        {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser parser=factory.newPullParser();

            parser.setInput(is,null);
            int event=parser.getEventType();

            String tagValue=null;
            Boolean isSiteMeta=true;

            earthquakes.clear();
            Earthquake earthquake=new Earthquake();

            do {

                String tagName=parser.getName();

                switch (event)
                {
                    case XmlPullParser.START_TAG:
                        if(tagName.equalsIgnoreCase("item"))
                        {
                            earthquake=new Earthquake();
                            isSiteMeta=false;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        tagValue=parser.getText();
                        break;

                    case XmlPullParser.END_TAG:

                        if(!isSiteMeta)
                        {
                            if(tagName.equalsIgnoreCase("title"))
                            {
                                earthquake.setTitle(tagValue);
                            }else if(tagName.equalsIgnoreCase("description"))
                            {
                                earthquake.setDescription(tagValue);

                            }else if(tagName.equalsIgnoreCase("pubDate"))
                            {
                                earthquake.setDate(tagValue);

                            }else if(tagName.equalsIgnoreCase("guid"))
                            {
                                earthquake.setGuid(tagValue);

                            }else if(tagName.equalsIgnoreCase("link"))
                            {
                                earthquake.setLink(tagValue);

                            }
                        }

                        if(tagName.equalsIgnoreCase("item"))
                        {
                            earthquakes.add(earthquake);
                            isSiteMeta=true;
                        }
                        break;
                }

                event=parser.next();

            }while (event != XmlPullParser.END_DOCUMENT);

            return true;

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
