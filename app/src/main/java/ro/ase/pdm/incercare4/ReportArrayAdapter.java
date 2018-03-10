package ro.ase.pdm.incercare4;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Anca on 12/19/2017.
 */

public class ReportArrayAdapter extends ArrayAdapter<Report> {

    public ReportArrayAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
    public ReportArrayAdapter(@NonNull Context context, int resource, ArrayList<Report> list) {
        super(context, resource,list);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       if(convertView==null){
           LayoutInflater inflater=LayoutInflater.from(getContext());
           convertView=inflater.inflate(R.layout.layout__row_list_view,null);

           //convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout__row_list_view, parent, false);
       }

       Report rep=getItem(position);


         TextView departmentTextView=(TextView)convertView.findViewById(R.id.tv_department);
         TextView locationTextView=(TextView)convertView.findViewById(R.id.tv_location);
        TextView dateTextView=(TextView)convertView.findViewById(R.id.tv_dataAdaugare);
        TextView titleTextView=(TextView)convertView.findViewById(R.id.tv_titlu);
        TextView descTextView=(TextView)convertView.findViewById(R.id.tv_description);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.image_report);


        titleTextView.setText(rep.getTitle());
        descTextView.setText(rep.getDescription());
        locationTextView.setText(rep.getLocation());


        SimpleDateFormat postFormater = new SimpleDateFormat("MM-dd-yyyy");
        String newDateStr = postFormater.format(rep.getDate());
        dateTextView.setText(newDateStr);
        departmentTextView.setText(rep.getDepartment().toString());


            Log.d("Tag","if Intri macar aici? pam pam ");
            imageView.setImageBitmap(rep.getImage());

//            Log.d("TAG","else, intri aici pam pam");
           imageView.setImageResource(R.drawable.doh);



        return convertView;




    }
}
