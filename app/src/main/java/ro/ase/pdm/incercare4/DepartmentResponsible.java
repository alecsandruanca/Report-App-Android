package ro.ase.pdm.incercare4;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anca on 11/18/2017.
 */

public enum DepartmentResponsible {
    FINANCE(1), POLICE(2), CHILD_CARE(3), HEALTH_CARE(4),GARBAGE_COLLECTION(5);

    private int depNo;




    private DepartmentResponsible(final int dep)
    {
        this.depNo = dep;
    }

   public int getDepNo(){
        return depNo;
   }
   public void setValue(int val){
       this.depNo=val;
   }

   public static DepartmentResponsible getDepartementDepartmentResponsibleById(int id){
       DepartmentResponsible department=null;

       switch(id){
           case 1:
               department=FINANCE;
               break;
           case 2:
               department=POLICE;
               break;
           case 3:
               department=CHILD_CARE;
               break;
           case 4:
               department=HEALTH_CARE;
               break;
           case 5:
               department=GARBAGE_COLLECTION;
               break;
           default:
                   break;

       }
       return department;
   }
   //type of call..try it out
//event.setEventStatus(EventStatus.getEventStatusById(getAttributeValueInt(linkedEventElement, "status")));

}