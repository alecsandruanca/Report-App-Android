package ro.ase.pdm.incercare4;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Anca on 12/19/2017.
 */

public class ReportWorker extends AsyncTask<String,Integer,Report> {
    private TextView titleTV=null;
    private TextView descriptionTV=null;
    private TextView dateTV=null;
    private ImageView imageIV=null;
    public ReportWorker(TextView _title, TextView _description, TextView _date, ImageView _image){
        this.descriptionTV=_description;
        this.dateTV=_date;
        this.titleTV=_title;
        this.imageIV=_image;
    }

    @Override
    protected Report doInBackground(String... strings) {
        if (strings == null || strings.length == 0 || "".equals(strings[0])) {
            return null;
        }
        HttpURLConnection connection = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("https://api.myjson.com/bins/1fql53");
            connection = (HttpURLConnection) url.openConnection();
            is = connection.getInputStream();
            reader = new BufferedReader(isr);
            String line = null;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            String json = response.toString();
            Log.d("JSON", json);

            Report report = new Report();
            JSONObject responseJSON = new JSONObject(json);
            JSONObject mainJson = responseJSON.getJSONObject("main");
            String temp = mainJson.getString("Title");
            report.setTitle(temp);

            JSONArray reportArray = responseJSON.getJSONArray("Title");
            JSONObject reportObject = reportArray.getJSONObject(0);
            report.setDescription(reportObject.getString("Description"));


            return report;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Report report) {
        if(report!=null){

            if(titleTV!=null){
                titleTV.setText(report.getTitle());
            }
            if(descriptionTV!=null){
                descriptionTV.setText(report.getDescription());
            }
        }
    }
}
