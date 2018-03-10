package ro.ase.pdm.incercare4;

import android.widget.DatePicker;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Anca on 11/19/2017.
 */

public class UserData implements Serializable{
    //precum Location..de stabilit toate atributele ->getters and setters


    private  String lastName;
    private  String firstName;
    private String phone;
    private enum Sex{MALE, FEMALE};
    private Date birthDay;
    private Date registration_date;
    private String residenceCity;
    private String placeOfBirth;
    private Education educationLevel;


    public UserData(String lname,String fname, String Phone){
        this.lastName=lname;
        this.firstName=fname;
        this.phone=Phone;
    }

        public  UserData(){

        }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public String getResidenceCity() {
        return residenceCity;
    }

    public void setResidenceCity(String residenceCity) {
        this.residenceCity = residenceCity;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Education getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(Education educationLevel) {
        this.educationLevel = educationLevel;
    }
}
