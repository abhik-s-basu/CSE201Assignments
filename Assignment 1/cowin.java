import java.util.*;
/**
 * main
 */
class vaccine {
    private String name;
    private int numDose;
    private int gap;
    private ArrayList<vaccine> vaxList= new ArrayList<>();
    private ArrayList<hospital> availableHospitals=new ArrayList<>();
    vaccine(){};
    void addVax(String name,int numDose,int gap){
        vaccine temp= new vaccine();
        temp.setName(name);
        temp.setnumDose(numDose);
        temp.setGap(gap);
        temp.displayDeet();
        addVaxList(temp);
    }
    void setName(String name){
        this.name=name;
    }
    void setnumDose(int numDose){
        this.numDose=numDose;
    }
    void setGap(int gap){
        this.gap=gap;
    }
    String getName(){
        return this.name;
    }
    int getnumDose(){
        return this.numDose;
    }
    int getGap(){
        return this.gap;
    }
    void displayDeet(){
        System.out.println("Vaccine Name: "+ this.getName() +" , "+"Number of Doses: "+ this.getnumDose() +" , "+"Gap Between Doses: "+ this.getGap());
    }
    void addVaxList(vaccine vax){
        getList().add(vax);
    }
    ArrayList<vaccine> getList(){
        return vaxList;
    }
    void showList(){
        for (int i=0;i<getList().size();i++){
            System.out.println(i+". "+getList().get(i).getName());
        }
    }
    String getNameChoice(int i){
        return getList().get(i).getName();
    }
    ArrayList<hospital> getVaxAvailableHospitals(){
        return availableHospitals;
    }
    vaccine vaxFinder(String name){
        // System.out.println("hello");
        // System.out.println(getList().size());
        for (int i=0;i<getList().size();i++){
            // System.out.println(name);
            // System.out.println(getList().get(i).getName());
            if (getList().get(i).getName().equals(name)){
                return getList().get(i);
            }
        }
        return null;
    }
    void printAllHospitals(vaccine v){
        // vaccine temp=new vaccine();
        // System.out.println("hellooo helllooo");
        // System.out.println(v.getVaxAvailableHospitals().size() + " rfjrijrd");
        // System.out.println(this.getVaxAvailableHospitals().size()); 
        for (int i=0;i<v.getVaxAvailableHospitals().size();i++){
            System.out.println(v.getVaxAvailableHospitals().get(i).getID() + " "+v.getVaxAvailableHospitals().get(i).getName());
        }
    }
}

class hospital {
    private String name;
    private int pinCode;
    private int id;
    private ArrayList<hospital> hospitalList= new ArrayList<>();
    private slots slo= new slots();
    public vaccine vax= new vaccine();
    private static int rand=100000;
    private ArrayList<slots> slotList= new ArrayList<>();
    hospital(){};
    void addHospital(String name,int pinCode){
        hospital temp=new hospital();
        temp.randIncrement();
        temp.setName(name);
        temp.setpinCode(pinCode);
        temp.genID();
        temp.allocID();
        temp.displayDeet();
        addHospitalList(temp);
    }
    void addSlotHospital(int id,int day,int quantity,String name,vaccine vax){
        hospital h=hospitalFinder(id);//need to check for null cases
        if (h==null){
            System.out.println("Wrong ID entered");
            return;
        }
        // System.out.println(vax.getList().size());
        slots s = slo.addSlot(day, quantity,name);
        h.getSlotList().add(s);
        System.out.println("Slot added by Hospital "+h.getID()+" for Day "+ s.getDay() +" , "+" Available Quantity: "+s.getQuantity()+" of Vaccine "+s.getVaxName());
        // vaccine vax= new vaccine();
        vaccine v=vax.vaxFinder(s.getVaxName());
        // System.out.println(v.getName());
        h.addVaxToHospital(v,h);
    }
    void addVaxToHospital(vaccine v,hospital h){
        // vaccine temp=new vaccine();
        v.getVaxAvailableHospitals().add(h);
        // System.out.println(v.getVaxAvailableHospitals().size()+ " Size here");
        // for (int i=0;i<v.getList().size();i++){
        //     System.out.println(v.getList().get(i).getName() + "hello");
        //     if (v.getList().get(i).getName().equals(name)){
        //         v.getList().get(i).getVaxAvailableHospitals().add(h);
        //     }
        // }
    }
    void setName(String name){
        this.name=name;
    }
    void setpinCode(int pinCode){
        this.pinCode=pinCode;
    }
    void genID(){
        // this.id= new Random().nextInt(900000) + 100000;
        this.id=getRand();
    }
    int getRand(){
        return rand;
    }
    void randIncrement(){
        rand++;
    }
    String getName(){
        return this.name;
    }
    int getpinCode(){
        return this.pinCode;
    }
    int getID(){
        return this.id;
    }
    void allocID(){
        System.out.println("Allocated Hospital ID is "+ this.getID());
    }
    void displayDeet(){
        System.out.println("Hospital Name: "+ this.getName() +" , "+"PinCode: "+ this.getpinCode() +" , "+"Unique ID: "+ this.getID());
    }
    void addHospitalList(hospital hosp){
        hospitalList.add(hosp);
    }
    ArrayList<hospital> getList(){
        return hospitalList;
    }
    hospital hospitalFinder(int id){
        for (int i=0;i<getList().size();i++){
            if (id == getList().get(i).getID()){
                return getList().get(i);
            }
        }
        return null;
    }
    ArrayList<slots> getSlotList(){
        return slotList;
    }
    void showSlotList(hospital h){
        for (int i=0;i<h.getSlotList().size();i++){
            System.out.println("Day: "+h.getSlotList().get(i).getDay()+" Vaccine: "+h.getSlotList().get(i).getVaxName()+" Available Qty: "+h.getSlotList().get(i).getQuantity());
        }
    }
    void showAllSlotList(hospital h){
        for (int i=0;i<h.getSlotList().size();i++){
            System.out.println(i+ "->Day: "+h.getSlotList().get(i).getDay()+" Available Qty: "+h.getSlotList().get(i).getQuantity()+" Vaccine: "+h.getSlotList().get(i).getVaxName());
        }
    }
    void showAllSlots(int id){
        hospital h=new hospital();
        h.showAllSlotList(hospitalFinder(id));
    }
    void showAllSlotsVax(int id,String name){
        hospital h= new hospital();
        showAllSlotsListVax(hospitalFinder(id), name);
    }
    void showAllSlotsListVax(hospital h,String name){
        for (int i=0;i<h.getSlotList().size();i++){
            if (h.getSlotList().get(i).getVaxName().equals(name)){
                System.out.println(i+ "->Day: "+h.getSlotList().get(i).getDay()+" Available Qty: "+h.getSlotList().get(i).getQuantity()+" Vaccine: "+h.getSlotList().get(i).getVaxName());
            }
        }
    }
    void showSlots(int id){
        hospital h=new hospital();
        h.showSlotList(hospitalFinder(id));
    }
    void hospitalPinCode(int pinCode){
        // hospital h= new hospital();
        for (int i=0;i<getList().size();i++){
            if (pinCode==getList().get(i).getpinCode()){
                System.out.println(getList().get(i).getID() + " " + getList().get(i).getName());
            }
        }
    }
    
}

class slots{
    private int day;
    private int quantity;
    private String vaxName;
    // private vaccine vax= new vaccine();
    private ArrayList<slots> slotList= new ArrayList<>();
    slots(){};
    slots addSlot(int day,int quantity,String vaxName){
        slots temp=new slots();
        temp.setDay(day);
        temp.setQuantity(quantity);
        temp.setVaxName(vaxName);
        // addSlotList(this);
        return temp;
    }
    // void setHospital(hospital h){
    //     this.h=h;
    // }
    void setDay(int day){
        this.day=day;
    }
    void setQuantity(int quantity){
        this.quantity=quantity;
    }
    void setVaxName(String vaxName){
        this.vaxName=vaxName;
    }
    // hospital getHospital(){
    //     return this.h;
    // }
    int getDay(){
        return this.day;
    }
    int getQuantity(){
        return this.quantity;
    }
    String getVaxName(){
        return this.vaxName;
    }
    void addSlotList(slots s){
        getSlotList().add(s);
    }
    ArrayList<slots> getSlotList(){
        return slotList;
    }
    void quantityDecrementer(){
        this.quantity--;
    }
}

class citizen{
    private String name;
    private int age;
    private String id;
    private String vaxStatus;
    private int dosesLeft;
    private int numDoses=0;
    private int nextDate;
    private String vaxGiven;
    private ArrayList<citizen> citiList=new ArrayList<>();

    void addCitizen(String name, int age, String id){
        citizen temp=new citizen();
        temp.setName(name);
        temp.setAge(age);
        temp.setID(id);
        temp.showDeets();
        temp.setStatus("REGISTERED");
        addCitiList(temp);
    }
    void addSlot(String id, int choice, int hospID,hospital h,vaccine v){
        citizen tba=searchID(id);
        // hospital h= new hospital();
        hospital hosp=h.hospitalFinder(hospID);
        // System.out.println(h.getName()+" !@");
        // System.out.println(h.getSlotList().size()+" djnj");
        // System.out.println(hosp.getName()+" !@");
        // System.out.println(hosp.getSlotList().size()+" djnj");
        // vaccine v= new vaccine();
        vaccine vax=v.vaxFinder(hosp.getSlotList().get(choice).getVaxName());
        if (tba==null){
            System.out.println("Wrong ID was entered");
            return;
        }
        if(hosp.getSlotList().get(choice).getQuantity()!=0){
            // maybe need to look for case if person is fully vaccinated
            if (tba.getStatus().equals("REGISTERED")){
                // System.out.println(vax.getName() +);
                tba.setVaxGiven(vax.getName());
                tba.setNextDate(hosp.getSlotList().get(choice).getDay()+vax.getGap());
                // System.out.println("1");
                hosp.getSlotList().get(choice).quantityDecrementer();
                // System.out.println("2");
                tba.setnumDose(tba.getnumDose()+1);
                // System.out.println("3");
                tba.setDosesLeft(vax.getnumDose()-tba.getnumDose());
                if (tba.getDosesLeft()==0){
                    tba.setStatus("FULLY VACCINATED");
                }
                else {
                    tba.setStatus("PARTIALLY VACCINATED");
                }
                System.out.println(tba.getName() + " vaccinated with "+tba.getVaxGiven());
            }
            else if (tba.getStatus().equals("PARTIALLY VACCINATED")){
                if (hosp.getSlotList().get(choice).getDay() < tba.getNextDate()){
                    System.out.println("YOU ARE ASKING FOR VACCINE BEFORE DUE DATE.Come back later");
                    return;
                }
                else {
                    tba.setVaxGiven(vax.getName());
                    tba.setNextDate(hosp.getSlotList().get(choice).getDay()+vax.getGap());
                    hosp.getSlotList().get(choice).quantityDecrementer();
                    tba.setnumDose(tba.getnumDose()+1);
                    tba.setDosesLeft(vax.getnumDose()-tba.getnumDose());
                    if (tba.getDosesLeft()==0){
                        tba.setStatus("FULLY VACCINATED");
                    }
                    System.out.println(tba.getName() + " vaccinated with "+tba.getVaxGiven());
                }
            }
        }
        else{
            System.out.println("NOT ENOUGH QUANTITY LEFT. SORRY. Try Again");
            return;
        }
    }
    void vaccinationStatus(citizen c){
        // citizen temp=new citizen();
        System.out.println(c.getStatus());
        if (c.getStatus().equals("PARTIALLY VACCINATED")){
            System.out.println("Vaccine Given: "+c.getVaxGiven());
            System.out.println("Number of doses given: "+c.getnumDose());
            System.out.println("Next Dose due date: " + c.getNextDate());
        }
        else if (c.getStatus().equals("FULLY VACCINATED")){
            System.out.println("Vaccine Given: "+c.getVaxGiven());
            System.out.println("Number of doses given: "+c.getnumDose());
        }
        else if (c.getStatus().equals("REGISTERED")){
            return;
        }
        
    }
    void addCitiList(citizen temp){
        getCitiList().add(temp);
    }
    ArrayList<citizen> getCitiList(){
        return citiList;
    }
    citizen searchID(String id){
        for (int i=0;i<getCitiList().size();i++){
            if (getCitiList().get(i).getID().equals(id)){
                return getCitiList().get(i);
            }
        }
        return null;
    }
    void setName(String name){
        this.name=name;
    }
    void setAge(int age){
        this.age=age;
    }
    void setID(String id){
        this.id=id;
    }
    void setStatus(String vaxStatus){
        this.vaxStatus=vaxStatus;
    }
    void setDosesLeft(int dosesLeft){
        this.dosesLeft=dosesLeft;
    }
    void setnumDose(int numDose){
        this.numDoses=numDose;
    }
    void setNextDate(int nextDate){
        this.nextDate=nextDate;
    }
    void setVaxGiven(String vaxName){
        this.vaxGiven=vaxName;
        

    }
    String getName(){
        return this.name;
    }
    int getAge(){
        return this.age;
    }
    String getID(){
        return this.id;
    }
    void showDeets(){
        System.out.println("Citizen Name: "+this.getName()+" , Age: "+this.getAge()+" , Unique ID: "+this.getID());
    }
    String getStatus(){
        return this.vaxStatus;
    }
    int getDosesLeft(){
        return this.dosesLeft;
    }
    int getnumDose(){
        return this.numDoses;
    }
    int getNextDate(){
        return this.nextDate;
    }
    String getVaxGiven(){
        return this.vaxGiven;
    }
}

public class cowin {
    public static void main(String[] args) {
        vaccine vax= new vaccine();
        hospital hosp= new hospital();
        citizen cit=new citizen();
        Scanner sc= new Scanner(System.in);
        int n=0;
        System.out.println("CoWin Portal Initialised.....");
        while(true){
            System.out.println("-----------------------------------------");
            System.out.println("1. Add Vaccine");
            System.out.println("2. Register Hospital");
            System.out.println("3. Register Citizen");
            System.out.println("4. Add Slot for vaccination");
            System.out.println("5. Book Slot for vaccination");
            System.out.println("6. List all slots for a hospital");
            System.out.println("7. Check vaccination status");
            System.out.println("8. Exit");
            System.out.println("-----------------------------------------");
            n=sc.nextInt();
            if(n==1){
                String name;
                int numDose;
                int gap;
                System.out.print("Vaccine Name: ");
                name=sc.next();
                System.out.print("Number of Doses: ");
                numDose=sc.nextInt();
                if (numDose!=1){
                    System.out.print("Gap between Doses: ");
                    gap=sc.nextInt();
                    vax.addVax(name, numDose, gap);
                }
                else{
                    vax.addVax(name, numDose, 0);
                }  
            }
            else if (n==2){
                String name;
                int pinCode; //may need to do error handling here
                System.out.print("Hospital Name: ");
                name=sc.next();
                System.out.print("PinCode: ");
                pinCode=sc.nextInt();
                hosp.addHospital(name, pinCode);
            }
            else if (n==3){
                String name;
                int age;
                String id;
                System.out.print("Citizen Name: ");
                name=sc.next();
                System.out.print("Age: ");
                age=sc.nextInt();
                System.out.print("Unique ID: ");
                id=sc.next();
                if (age < 18){
                    System.out.println("Only above 18 are allowed");
                }
                else {
                    cit.addCitizen(name, age, id);
                }
            }
            else if (n==4){
                int hospID;
                int numSlots;
                int day;
                int quantity;
                int choice;
                String name;
                System.out.print("Enter Hospital ID: ");
                hospID=sc.nextInt();
                // checker function for hospital ID
                System.out.print("Enter number of slots to be added: ");
                numSlots=sc.nextInt();
                for (int i=0;i<numSlots;i++){
                    System.out.print("Enter Day Number: ");
                    day=sc.nextInt();
                    System.out.print("Enter Quantity: ");
                    quantity=sc.nextInt();
                    System.out.println("Select Vaccine");
                    vax.showList();
                    choice=sc.nextInt();
                    // maybe check here as well if choice entered is correct
                    name=vax.getNameChoice(choice);
                    hosp.addSlotHospital(hospID, day, quantity, name,vax);
                }
            }
            else if (n==5){
                String id;
                int search;
                int choice;
                int pinCode;
                String vaxName;
                int hospID;
                System.out.print("Enter patient Unique ID: ");
                id=sc.next();
                System.out.println("1. Search By Area");
                System.out.println("2. Search By Vaccine");
                System.out.println("3. Exit");
                System.out.print("Enter option: ");
                search=sc.nextInt();
                if (search==1){
                    System.out.print("Enter PinCode: ");
                    pinCode=sc.nextInt();
                    hosp.hospitalPinCode(pinCode);
                    System.out.print("Enter hospital id: ");
                    hospID=sc.nextInt();
                    hosp.showAllSlots(hospID);
                    System.out.print("Choose Slot: ");
                    choice=sc.nextInt();
                    cit.addSlot(id,choice,hospID,hosp,vax);
                }
                else if (search==2){
                    System.out.print("Enter Vaccine name: ");
                    vaxName=sc.next();
                    vaccine v=vax.vaxFinder(vaxName);
                    vax.printAllHospitals(v);
                    System.out.print("Enter hospital id: ");
                    hospID=sc.nextInt();
                    hosp.showAllSlotsVax(hospID,vaxName);
                    System.out.print("Choose Slot: ");
                    choice=sc.nextInt();
                    cit.addSlot(id,choice,hospID,hosp,vax);
                }
                else if(search==3){
                    System.out.println("Leaving the REGISTRATION PORTAL");
                    continue;
                }
            }
            else if (n==6){
                int hospID;
                System.out.print("Enter Hospital ID: ");
                hospID=sc.nextInt();
                //check again for hospID existence
                hosp.showSlots(hospID);
            }
            else if (n==7){
                String id;
                System.out.print("Enter Patient ID: ");
                id=sc.next();
                citizen c=cit.searchID(id);
                cit.vaccinationStatus(c);
            }
            else if (n==8){
                System.out.println("Leaving the registration portal");
                break;
            }

            
        }
    }
}