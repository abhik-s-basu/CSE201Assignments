import java.util.ArrayList;
import java.util.Scanner;
class player{
    private String name;
    private int point;
    private int currFloor=-1;
    player(String name){
        this.name=name;
    }
    int getPoint(){
        return this.point;
    }
    void setPoint(int a){
        this.point=a;
    }
    String getName(){
        return this.name;
    }
    void setCurrFloor(int floor){
        this.currFloor=floor;
    }
    int getCurrFloor(){
        return this.currFloor;
    }
}
class floor{
    floor(){};
    protected void userUpdate(player p,int currFloor, int points){
        p.setCurrFloor(currFloor);
        p.setPoint(p.getPoint()+points);
    }
    protected void update (player p){
        System.out.println("Player position Floor - "+p.getCurrFloor());
        p.setPoint(p.getPoint()+1);
        System.out.println(p.getName() + " has reached an empty floor");
        System.out.println("Total Points "+ p.getPoint());
    }
}
class empty extends floor {
    empty(){
        super();
    }
}
class snake extends floor{
    snake(){
        super();
    }
    @Override
    protected void update (player p){
        System.out.println("Total Points "+ p.getPoint());
        super.update(p);
    }
}
class ladder extends floor{
    ladder(){
        super();
    }
    @Override
    protected void update (player p){
        System.out.println("Total Points "+ p.getPoint());
        super.update(p);
    }
}
class normalSnake extends snake{
    normalSnake(){
        super();
    }
    @Override
    protected void update(player p){
        System.out.println("Player position Floor - "+p.getCurrFloor());
        userUpdate(p, 1, -2);
        System.out.println(p.getName() + " has reached a normal snake floor");
        super.update(p);
    }

}
class kingCobra extends snake{
    kingCobra(){
        super();
    }
    @Override
    protected void update (player p){
        System.out.println("Player position Floor - "+p.getCurrFloor());
        userUpdate(p, 3, -4);
        System.out.println(p.getName() + " has reached a King Cobra floor");
        super.update(p);
    }
}
class normalLadder extends ladder{
    normalLadder(){
        super();
    }
    @Override
    protected void update(player p){
        System.out.println("Player position Floor - "+p.getCurrFloor());
        userUpdate(p, 12, 2);
        System.out.println(p.getName() + " has reached a ladder floor");
        super.update(p);
    }
}
class elevator extends ladder{
    elevator(){
        super();
    }
    @Override
    protected void update (player p){
        System.out.println("Player position Floor - "+p.getCurrFloor());
        userUpdate(p, 10, 4);
        System.out.println(p.getName() + " has reached the elevator floor");
        super.update(p);
    }
}
class dice{
    private final int numFaces; //maximum face value
    private int faceValue; 
    public dice(int numFaces) {
        this.numFaces = numFaces;
        roll();
    }
    public void roll() {
    double curr_faceValue = 1 + (Math.random()*2);
    int faceValue=(int)curr_faceValue;
    setFaceValue(faceValue);
    }
    private void setFaceValue (int value) {
        if (value <= numFaces)
            this.faceValue = value;
    }
    int getFaceValue(){
        return this.faceValue;
    }
}
/**
 * board
 */
public class board {
    public static void main(String[] args) {
        floor[] floorList= new floor[14];
        for (int i=0;i<floorList.length;i++){
            if (!(i==2 || i==8 || i==5 || i==11)){
                floorList[i]=new empty();
            }
        }
        floorList[2]=new elevator();
        floorList[5]= new normalSnake();
        floorList[8]=new normalLadder();
        floorList[11]= new kingCobra();
        dice d= new dice(2);
        Scanner sc=new Scanner (System.in);
        System.out.println("Enter the player name and hit enter");
        String name=sc.nextLine();
        player user=new player(name);
        System.out.println("The game setup is ready");
        while(user.getCurrFloor()!=13){ //condition to be changed later
            System.out.print("Hit enter to roll the dice");
            sc.nextLine();
            d.roll();
            System.out.println("Dice gave " + d.getFaceValue());
            if ((user.getCurrFloor()==-1)&&(user.getCurrFloor()+d.getFaceValue()!=0)){
                System.out.println("Game cannot start until you get 1");
            }
            else if (user.getCurrFloor()+d.getFaceValue()>13){
                System.out.println("Player cannot move");
            }
            else {
                user.setCurrFloor(user.getCurrFloor()+d.getFaceValue());
                floorList[user.getCurrFloor()].update(user);
            }
        }
        System.out.println("Game Over");
        System.out.println(user.getName() + " accumulated " + user.getPoint() + " points.");
    }
}