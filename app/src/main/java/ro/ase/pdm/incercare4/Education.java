package ro.ase.pdm.incercare4;

/**
 * Created by Anca on 11/19/2017.
 */

public enum Education {
    ELEMENTARY_SCHOOL(1),
    HIGHSCCOOL(2),
    BACHELOR_DEGREE(3),
    PHD(4);
    private int educationNo;

    private Education(final int educNo)
    {
        this.educationNo=educNo;
    }

    public int getEducationNo() {
        return educationNo;
    }

    public void setEducationNo(int educationNo) {
        this.educationNo = educationNo;
    }

    public static Education getEducationById(int id){
        Education education=null;
        switch (id){
            case 1:
                education=ELEMENTARY_SCHOOL;
                break;
            case 2:
                education=HIGHSCCOOL;
                break;
            case 3:
                education=BACHELOR_DEGREE;
                break;
            case 4:
                education=PHD;
                break;
            default:
                break;

        }
        return education;
    }
}
