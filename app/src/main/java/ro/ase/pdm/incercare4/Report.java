package ro.ase.pdm.incercare4;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Anca on 11/18/2017.
 */
/*public enum DepartmentResponsible{
    FINANCE=1;,POLICE(2),CHILD_CARE(3),HEALTH(4),GARBAGE_COLLECTION(5),RECYCLING(6);
    private int value;
    ////de ce spanac nu vrea asta sa mearga????????????????????????
    public int getValue() {
        return value;
    }
    //Just for testing from some SO answers, but no use
    public void setValue(int value) {
        this.value = value;
    }
    public static DepartmentResponsible getDepartmentResponsibleById(int id){
        DepartmentResponsible depart=null;

    }
}

*/






public class Report implements Serializable {



    private String Title;
    private String Description;
    private String Location;
    private DepartmentResponsible Department;
    private Bitmap Image;

    private Date Date;
    private Date Date_added;

    Report(){

    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public DepartmentResponsible getDepartment() {
        return Department;
    }

    public void setDepartment(DepartmentResponsible department) {
        Department = department;
    }

//    public Drawable getImage() {
//        return Image;
//    }
//
//    public void setImage(Drawable image) {
//        this.Image = image;
//    }


    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    public void setDate(Date date) {
        this.Date = date;
    }


    public Date getDate() {
        return Date;
    }

    public void setDate(String date) throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat("MM/dd/yyyy");
        this.Date = formatter.parse(date);
    }

    public Date getDate_added() {
        return Date_added;
    }

    public void setDate_added(Date date_added) {
        this.Date_added = date_added;
    }

    public Report(String title, String description, DepartmentResponsible department) {
        Title = title;
        Description = description;
        Department = department;

    }
    public Report(String title, String description ){
        Title = title;
        Description = description;

    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


}
