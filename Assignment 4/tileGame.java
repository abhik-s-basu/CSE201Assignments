import java.util.*;
/**
 * tileGame
 */
class softToys implements Cloneable{
    private String name;
    softToys(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}

class player{
    private bucket b;
    player(){
        b= new bucket();
    }
    bucket getBucket(){
        return b;
    }
    
}

class bucket{
    private ArrayList<softToys> b;
    bucket(){
        this.b=new ArrayList<softToys>();
    }
    ArrayList<softToys> getBucket(){
        return b;
    }
    void addToy (softToys e){
        b.add(e);
    }
    void showBucket(){
        for(int i=0;i<this.b.size();i++){
            System.out.print(b.get(i).getName() + " | ");
        }
        System.out.println();
    }

}
class tile {
    private softToys toys;
    private int position;
    tile(softToys toys,int position){
        this.toys = toys;
        this.position = position;
    }
    int getPosition(){
        return this.position;
    }
    softToys getToy(){
        return this.toys;
    }
}

class calculator <T> {
    calculator(){
    }
    boolean checkAns(T obj1, T obj2){
        if (obj1 instanceof Integer){
            return obj1==obj2;
        }
        else if (obj1 instanceof String){
            return ((String)obj1).equals((String)obj2);
        }
        else return false;
    }
}

public class tileGame {

    public static void main(String[] args){
        Scanner sc = new Scanner (System.in);
        Random rand= new Random();
        tile[] carpet = new tile[20];
        carpet[0]=new tile(new softToys("Honey"),1);
        carpet[1]=new tile(new softToys("Pookie"),2);
        carpet[2]=new tile(new softToys("Snowball"),3);
        carpet[3]=new tile(new softToys("Cuddles"),4);
        carpet[4]=new tile(new softToys("Fudge"),5);
        carpet[5]=new tile(new softToys("Ted"),6);
        carpet[6]=new tile(new softToys("Wally"),7);
        carpet[7]=new tile(new softToys("Donald Duck"),8);
        carpet[8]=new tile(new softToys("Tuffy"),9);
        carpet[9]=new tile(new softToys("Squishy"),10);
        carpet[10]=new tile(new softToys("Minion"),11);
        carpet[11]=new tile(new softToys("Jigglypuff"),12);
        carpet[12]=new tile(new softToys("Mr Cuddlesworth"),13);
        carpet[13]=new tile(new softToys("Booboo"),14);
        carpet[14]=new tile(new softToys("Fuzzy Wuzzy"),15);
        carpet[15]=new tile(new softToys("Pikachu"),16);
        carpet[16]=new tile(new softToys("Captain America"),17);
        carpet[17]=new tile(new softToys("Thor"),18);
        carpet[18]=new tile(new softToys("Iron Man"),19);
        carpet[19]=new tile(new softToys("Doctor Strange"),20);
        player p= new player();
        System.out.print("Hit enter to initialise the game");
        sc.nextLine();
        System.out.println("Game is ready");
        for (int i=1;i<=5;i++){
            // int dice = sc.nextInt();
            int dice=rand.nextInt(25);
            // sc.nextLine();
            System.out.print("Hit enter for hop "+ (int)(i));
            sc.nextLine();
            boolean errProne=true;
            // System.out.println(dice);
            while(errProne==true){
                try{
                    if(carpet[dice].getPosition()%2==0){
                        System.out.println("You landed on tile " + (int)carpet[dice].getPosition());
                        try{
                            softToys e=(softToys)carpet[dice].getToy().clone();
                            p.getBucket().addToy(e);
                            System.out.println("You won a " + e.getName() +" soft toy");
                            errProne=false;
                        }
                        catch (CloneNotSupportedException e){
                            System.out.println("Clone not suppported, sorry");
                            break;
                        }
                    }
                    else if(carpet[dice].getPosition()%2==1){
                            System.out.println("You landed on tile " + (int)carpet[dice].getPosition());
                            System.out.println("Question answer round. integer or string?");
                            String ans= sc.nextLine();
                            try{
                                if (ans.toLowerCase().equals("integer")){
                                    final calculator<Integer> calc = new calculator<Integer>();
                                    boolean errHandler=true;
                                    // int rand1 = 0;
                                    int dividend = 0;
                                    int divisor = 0;
                                    int answer = 0;
                                    while (errHandler == true){
                                        try{
                                            divisor = rand.nextInt();
                                            dividend = divisor*rand.nextInt();
                                            answer= (dividend/divisor);
                                            errHandler=false;
                        
                                        }
                                        catch(ArithmeticException e1){
                                            System.out.println("dividing again");
                                        }
                                    }
                                    System.out.println("Calculate the result of division of " + dividend  + " and " + divisor);
                                    boolean inpErr=true;
                                    while(inpErr==true){
                                        try{
                                            int a=sc.nextInt();
                                            sc.nextLine();
                                            if (calc.checkAns(a,answer)){
                                                System.out.println("Correct Answer");
                                                try{
                                                    softToys e1=(softToys)carpet[dice].getToy().clone();
                                                    p.getBucket().addToy(e1);
                                                    System.out.println("You won a " + e1.getName() +" soft toy");
                                                    errProne=false;
                                                    inpErr=false;
                                                    // sc.nextLine();
                                                }   
                                                catch (CloneNotSupportedException err){
                                                    System.out.println("Clone not suppported, sorry");
                                                    break;
                                                }
                                            }
                                            else {
                                                System.out.println("Wrong answer, no toy for you");
                                                errProne=false;
                                                inpErr=false;
                                            }
                                        }
                                        catch (InputMismatchException e){
                                            System.out.println("Wrong answer as well as wrong input; Enter integer pls");
                                            sc.nextLine();
                                        }
                                    }
                                }
                                else if (ans.toLowerCase().equals("string")){
                                    final calculator<String> calc = new calculator<String>();
                                    final String alphalist= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                                    String s1 = "";
                                    String s2 = "";
                                    for (int j=0;j<4;j++){
                                        s1 += alphalist.charAt(rand.nextInt(52));
                                        s2 += alphalist.charAt(rand.nextInt(52));
                                    }
                                    String answer = s1+s2;
                                    System.out.println("Calculate the concatenation of "+ s1 + " and " + s2 );
                                    String a=sc.nextLine();
                                    if(calc.checkAns(a,answer)==true){
                                        System.out.println("Correct Answer");
                                        try{
                                            softToys e1=(softToys)carpet[dice].getToy().clone();
                                            p.getBucket().addToy(e1);;
                                            System.out.println("You won a " + e1.getName() +" soft toy");
                                            errProne=false;
                                            // sc.nextLine();
                                        }
                                        catch (CloneNotSupportedException err){
                                            System.out.println("Clone not suppported, sorry");
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.println("Wrong answer, no toy for you");
                                        errProne=false;
                                    }
                                }
                                else {
                                    System.out.println("Wrong input, try again");
                                }
                            }
                            catch(InputMismatchException e){
                                System.out.println("Wrong input. Try again");
                            }    
                    }
                }
                catch (IndexOutOfBoundsException e){
                    // e.printStackTrace();;
                    // System.out.println(dice);
                    System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
                    errProne=false;
                    continue;
                }
                catch (NullPointerException e){
                    System.out.println("null pointer exception");
                    errProne=false;
                    continue;
                }
            }
            
        }
        System.out.println("Game Over");
        System.out.println("SOFT TOYS WON ARE:");
        p.getBucket().showBucket();
    }
}