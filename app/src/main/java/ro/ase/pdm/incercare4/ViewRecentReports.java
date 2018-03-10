package ro.ase.pdm.incercare4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ViewRecentReports extends AppCompatActivity implements Serializable{
    ArrayList<Report> list=new ArrayList<>();
    ListView listView=null;

    Report report1=new Report("Probeleme finante","descrierea problemei blablablablablablablablablablablablablablablablablablablablablablablablabla");

    Report report2=new Report("Child Care ","alta descriere blablablablablablablablablablabablablablablablablablablablablabla");




    public void receiveReport(Bundle b){
        Log.d("TAG","Crapa in functia de  primire?");
        Intent i=this.getIntent();

        b=i.getExtras();

        if(b!=null){
            Report rep=(Report)b.getSerializable("report");
            list.add(rep);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recent_reports);

        final TextView departmentTextView=(TextView)findViewById(R.id.tv_department);
        final TextView locationTextView=(TextView)findViewById(R.id.tv_location);
        final TextView titleTextView=(TextView)findViewById(R.id.tv_titlu);
        final TextView descrTextView=(TextView)findViewById(R.id.tv_description);
        final TextView dateTextView=(TextView)findViewById(R.id.tv_dataAdaugare);
        final ImageView imageView=(ImageView)findViewById(R.id.image_report);
        listView=(ListView)findViewById(R.id.listView);

        Log.d("TAG","Crapa in OnCreate inainte de ?");

        receiveReport(savedInstanceState);


        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.iconita_draguta);

//        report1.setImage(icon);
//        report2.setImage(icon);
//        list.add(report1);
//        list.add(report2);
//        list.add(report1);
//        list.add(report2);

        Log.d("TAG","Crapa in OnCreate dupa ?");

        ReportArrayAdapter adapter=new ReportArrayAdapter(getApplicationContext(),R.layout.layout__row_list_view,list);


        listView.setAdapter(adapter);

        ReportWorker worker;
        worker = new ReportWorker(titleTextView,descrTextView,dateTextView,imageView);
        worker.execute();









    }
}
