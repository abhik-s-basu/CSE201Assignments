import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
class player{
    private String name;
    private int point;
    private int currFloor=-1;
    private int moves=0;
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
    void incrementMoves(){
        this.moves++;
    }
    int getMoves(){
        return this.moves;
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
class python extends snake{
    python(){
        super();
    }
    @Override
    protected void update (player p){
        System.out.println("Player position Floor - "+p.getCurrFloor());
        userUpdate(p, 13, -3);
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
class stairs extends ladder{
    stairs(){
        super();
    }
    @Override
    protected void update(player p){
        System.out.println("Player position Floor - "+p.getCurrFloor());
        userUpdate(p, 19, 3);
        System.out.println(p.getName() + " has reached the stairs floor");
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
        floor[] floorList= new floor[21];
        ArrayList<player> players= new ArrayList<>();
        for (int i=0;i<floorList.length;i++){
            if (!(i==2 || i==8 || i==5 || i==11 || i==14 || i==17)){
                floorList[i]=new empty();
            }
        }
        floorList[2]=new elevator();
        floorList[5]= new normalSnake();
        floorList[8]=new normalLadder();
        floorList[11]= new kingCobra();
        floorList[14]= new stairs();
        floorList[17]= new python();
        for (int i=0; i < floorList.length;i++){
            if (!(i==2 || i==8 || i==5 || i==11 || i==14 || i==17)){
                System.out.println("Floor " + i + " -> " + "empty");
            }
            else{
                System.out.println("Floor " + i + " -> " + floorList[i].getClass().toString().substring(5).toUpperCase());
            }
        }
        dice d= new dice(2);
        Scanner sc=new Scanner (System.in);
        System.out.print("Enter the total no. of players who will play the game: ");
        int num=sc.nextInt();
        sc.nextLine();
        for(int i=0;i<num;i++){
            System.out.println("Enter the player "+ (int)(i+1) + "'s name and hit enter: ");
            String name=sc.nextLine();
            player user=new player(name);
            players.add(user);
        }        
        System.out.println("The game setup is ready");
        for (int i=0;i<players.size();i++){
            System.out.println("------------------------------------");
            System.out.println("Game is starting now for " + players.get(i).getName() );
            while(players.get(i).getCurrFloor()!=20){ //condition to be changed later
                System.out.print("Hit enter to roll the dice");
                sc.nextLine();
                d.roll();
                System.out.println("Dice gave " + d.getFaceValue());
                players.get(i).incrementMoves();
                if ((players.get(i).getCurrFloor()==-1)&&(players.get(i).getCurrFloor()+d.getFaceValue()!=0)){
                    System.out.println("Game cannot start until you get 1");
                }
                else if (players.get(i).getCurrFloor()+d.getFaceValue()>20){
                    System.out.println("Player cannot move");
                }
                else {
                    players.get(i).setCurrFloor(players.get(i).getCurrFloor()+d.getFaceValue());
                    floorList[players.get(i).getCurrFloor()].update(players.get(i));
                }
            }
            System.out.println("Game Over for " + players.get(i).getName());
            System.out.println(players.get(i).getName() + " accumulated " + players.get(i).getPoint() + " points.");
        }
        sort(players);
        System.out.println("##########################################");
        for(int i=0;i<players.size();i++){
            System.out.println("#" + (int)(i+1)+ " "+players.get(i).getName() + " scored " + players.get(i).getPoint() + " in " + players.get(i).getMoves() +" moves");
        }
        System.out.println("##########################################");
        if (num>1){
            System.out.println("»»——⍟——«« »»——⍟——«« »»——⍟——««");
            System.out.println("(＾O＾) "+"Congratulations to " + players.get(0).getName() + " (＾O＾)");
            System.out.println(".·´¯`(>▂<)´¯`·. " + "Sorry to " +players.get(players.size()-1).getName() + " .·´¯`(>▂<)´¯`·.");
            System.out.println("»»——⍟——«« »»——⍟——«« »»——⍟——««");
            System.out.println(" G A M E  O V E R");
        }
        else{
            System.out.println(" G A M E  O V E R");
        }
        
    }
    static void sort (ArrayList<player> p){
        for (int i=0;i<p.size()-1;i++){
            for (int j=0;j<p.size()-i-1;j++){
                if (p.get(j).getPoint()<p.get(j+1).getPoint()){
                    Collections.swap(p, j+1, j);
                }
                else if (p.get(j).getPoint()==p.get(j+1).getPoint()){
                    if (p.get(j).getMoves()>p.get(j+1).getMoves()){
                    Collections.swap(p, j+1, j);
                    }
                }
            }
        }
    }

}