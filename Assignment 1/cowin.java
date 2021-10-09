import java.util.*;
/**
 * main
 */
class vaccine {
    private String name;
    private int numDose;
    private int gap;
    private ArrayList<vaccine> vaxList= new ArrayList<>();
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
}

class hospital {
    private String name;
    private int pinCode;
    private int id;
    private ArrayList<hospital> hospitalList= new ArrayList<>();
    private slots slo= new slots();
    public vaccine vax= new vaccine();
    private ArrayList<slots> slotList= new ArrayList<>();
    hospital(){};
    void addHospital(String name,int pinCode){
        hospital temp=new hospital();
        temp.setName(name);
        temp.setpinCode(pinCode);
        temp.genID();
        temp.allocID();
        temp.displayDeet();
        addHospitalList(temp);
    }
    void addSlotHospital(int id,int day,int quantity,String name){
        hospital h=hospitalFinder(id);//need to check for null cases
        if (h==null){
            System.out.println("Wrong ID entered");
            return;
        }
        // System.out.println(vax.getList().size());
        slots s = slo.addSlot(day, quantity,name);
        h.getSlotList().add(s);
        System.out.println("Slot added by Hospital "+h.getID()+" for Day "+ s.getDay() +" , "+" Available Quantity: "+s.getQuantity()+" , "+"of Vaccine "+s.getVaxName());
    }
    void setName(String name){
        this.name=name;
    }
    void setpinCode(int pinCode){
        this.pinCode=pinCode;
    }
    void genID(){
        this.id= new Random().nextInt(900000) + 100000;
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
    void showSlots(int id){
        hospital h=new hospital();
        h.showSlotList(hospitalFinder(id));
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
}

class citizen{
    private String name;
    private int age;
    private String id;
    private String vaxStatus;
    void addCitizen(String name, int age, String id){
        citizen temp=new citizen();
        temp.setName(name);
        temp.setAge(age);
        temp.setID(id);
        temp.showDeets();
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
}
public class cowin {
    public static void main(String[] args) {
        vaccine vax= new vaccine();
        hospital hosp= new hospital();
        slots slo= new slots();
        citizen cit=new citizen();
        Scanner sc= new Scanner(System.in);
        int n=0;
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
                System.out.println("Unique ID: ");
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
                    hosp.addSlotHospital(hospID, day, quantity, name);
                }
            }
            else if (n==6){
                int hospID;
                System.out.print("Enter Hospital ID: ");
                hospID=sc.nextInt();
                //check again for hospID existence
                hosp.showSlots(hospID);
            }

            
        }
    }
}