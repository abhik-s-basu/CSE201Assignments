import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

interface user {
    public user  addUser(String s);
    public String getIDUser();
}
interface material{
    public void view();

}
interface assessment{
    public void view(int i);
    public void viewAll(int i);
    public boolean getCloseStatus();
    public String getQuest();
    public void setCloseStatus(boolean b);
    public int viewMaxMarks();
}

class instructor implements user {
    private String ID;
    private ArrayList<assessment> ass=new ArrayList<>();
    instructor(){};
    instructor(String s){
        this.ID=s;
    }
    String getID(){
        return this.ID;
    }
    void addAssesment(assessment a){
        this.ass.add(a);
    }
    @Override
    public user addUser(String s){
        return new instructor(s);
    }
    public String getIDUser(){
        return this.getID();
    }
    
}
class student implements user{
    private String ID;
    private ArrayList<Boolean> pending=new ArrayList<>();
    private ArrayList<Integer>grades=new ArrayList<>();
    private ArrayList<String> subs=new ArrayList<>();
    private ArrayList<String> checker=new ArrayList<>();
    student(){};
    student(String s){
        this.ID=s;
    }
    String getID(){
        return this.ID;
    }
    void addAssesment(assessment a){
        this.pending.add(true);
        this.grades.add(-10000);
        this.subs.add("No Subs");
        this.checker.add("No Checker yet");
    }
    boolean getPending(int i){
        return pending.get(i);
    }
    void setPending(int i,boolean b){
        this.pending.set(i, b);
    }
    void addSubmission(String subs, int i){
        this.pending.set(i, false);
        this.subs.set(i, subs);
    }
    void viewGrades(){
        System.out.println("Graded Submissions ");
        for (int i=0;i<this.pending.size();i++){
            if ((this.grades.get(i)!=-10000)&&(this.pending.get(i)==false)){
                System.out.print("Submission: ");
                System.out.println(this.subs.get(i));
                System.out.print("Marks Scored: ");
                System.out.println(this.grades.get(i));
                System.out.print("Graded by: ");
                System.out.println(this.checker.get(i));
            }
        }
        System.out.println();
        System.out.println("Ungraded Submission");
        for (int i=0;i<this.pending.size();i++){
            if ((this.grades.get(i)==-10000)&&(this.pending.get(i)==false)){
                if (!(this.subs.get(i).equals("No Subs"))){
                    System.out.print("Submission: ");
                    System.out.println(this.subs.get(i));
                }            
            }
        }
    }
    void getSubmission(int i){
        System.out.println("Submission: "+this.subs.get(i));
        System.out.println("------------------");
    }
    void gradeSubs(int marks,int i,int idx,instructor inst){
        this.grades.set(idx,marks);
        this.pending.set(idx,false);
        this.checker.set(idx,inst.getID());
    }
    String getSub(int i){
        return this.subs.get(i);
    }
    @Override
    public user addUser(String s){
        return new student(s);
    }
    public String getIDUser(){
        return this.getID();
    }
}

class assignment implements assessment{
    private String problemStatement;
    private int maxMarks;
    private boolean closed;
    private void viewAssignment(int i){
        if (this.closed==false){
            System.out.println("ID "+ i + " Assignment: " + this.problemStatement + " Max Marks: "+this.getMaxMarks());
            System.out.println("------------------------");
        }
    }
    private void viewAllAssignment(int i){
        System.out.println("ID "+ i + " Assignment: " + this.problemStatement + " Max Marks: "+this.getMaxMarks());
        System.out.println("------------------------");
    }
    assignment(String problemStatement,int maxMarks){
        this.problemStatement=problemStatement;
        this.maxMarks=maxMarks;
        this.closed=false;
    }
    String getProblemStatement(){
        return this.problemStatement;
    }
    int getMaxMarks(){
        return this.maxMarks;
    }
    boolean getClosed(){
        return this.closed;
    }
    void setClosed(boolean b){
        this.closed=b;
    }
    @Override
    public void view(int i){
        this.viewAssignment(i);
    }
    public boolean getCloseStatus(){
        return this.getClosed();
    }
    public String getQuest(){
        return this.problemStatement;
    }
    public void setCloseStatus(boolean b){
        this.closed=b;
    }
    public void viewAll(int i){
        this.viewAllAssignment(i);
    }
    public int viewMaxMarks(){
        return this.maxMarks;
    }
    

}
class quiz implements assessment{
    private String quest;
    private int maxMarks;
    private boolean closed;
    private void viewQuiz(int i){
        if (this.closed==false){
            System.out.println("ID "+ i + " Question: " + this.quest);
            System.out.println("------------------------");
        }
    }
    private void viewAllQuiz(int i){
            System.out.println("ID "+ i + " Question: " + this.quest);
            System.out.println("------------------------");
    }
    quiz(String quest){
        this.quest=quest;
        this.maxMarks=1;
        this.closed=false;
    }
    String getQuestion(){
        return this.quest;
    }
    int getMaxMarks(){
        return this.maxMarks;
    }
    boolean getClosed(){
        return this.closed;
    }
    void setClosed(boolean b){
        this.closed=b;
    }
    @Override
    public void view(int i){
        this.viewQuiz(i);
    }
    public void viewAll(int i){
        this.viewAllQuiz(i);
    }
    public boolean getCloseStatus(){
        return this.getClosed();
    }
    public String getQuest(){
        return this.quest;
    }
    public void setCloseStatus(boolean b){
        this.closed=b;
    }
    public int viewMaxMarks(){
        return this.maxMarks;
    }
}

class comment {
    private String content;
    private String userID;
    private String time;
    comment(String content,user u,String time){
        this.content=content;
        this.userID= u.getIDUser();
        this.time=time;
    }
    void viewComment(){
        System.out.println(this.content + " - " + this.userID);
        System.out.println(this.time);
        System.out.println();
    }
}

class lectureSlides implements material{
    private String topic;
    private int num;
    private  String content[];
    private String uploader;
    private String dateOfUpload;
    lectureSlides(String topic,int num, String [] content,String uploader,String dateOfUpload){
        this.topic=topic;
        this.num=num;
        this.content=content;
        this.uploader=uploader;
        this.dateOfUpload=dateOfUpload;
    }
    private void viewSlides(){
        System.out.println("Title of Slides: " + this.topic);
        for (int i=0;i<getContent().length;i++){
            System.out.println("Slide "+(int)(i+1) + " : "+ this.getContent()[i]);
        }
        System.out.println("Number of Slides: "+ this.getNum());
        System.out.println("Date of Upload: " + this.getDateOfUpload());
        System.out.println("Uploaded by: " + this.getUploader());
        System.out.println();
    }
    String getTopic(){
        return this.topic;
    }
    int getNum(){
        return this.num;
    }
    String [] getContent(){
        return this.content;
    }
    String getUploader(){
        return this.uploader;
    }
    String getDateOfUpload(){
        return this.dateOfUpload;
    }
    @Override
    public void view(){
        this.viewSlides();
    }

}

class lectureVideos implements material{
    private String topic;
    private String name;
    private String uploader;
    private String dateOfUpload;
    lectureVideos(String topic,String name,String uploader,String dateOfUpload){
        this.topic=topic;
        this.name=name;
        this.uploader=uploader;
        this.dateOfUpload=dateOfUpload;
    }
    
    private void viewVideos(){
        System.out.println("Title of video: "+ this.getTopic());
        System.out.println("Video File: "+this.getName());
        System.out.println("Date of Upload: " + this.getDateOfUpload());
        System.out.println("Uploaded by: "+this.getUploader());
        System.out.println();
    }
    String getTopic(){
        return this.topic;
    }
    String getName(){
        return this.name;
    }
    String getUploader(){
        return this.uploader;
    }
    String getDateOfUpload(){
        return this.dateOfUpload;
    }
    @Override
    public void view(){
        this.viewVideos();
    }
}

/**
 * backpack
 */
public class backpack {

    public static void main(String[] args) {
        Scanner sc= new Scanner (System.in);
        ArrayList<instructor> instList= new ArrayList<>();
        ArrayList<student> studList= new ArrayList<>();
        ArrayList<material> mat= new ArrayList<>();
        ArrayList<assessment> assess= new ArrayList<>();
        ArrayList<comment> comm=new ArrayList<>();
        instList.add(new instructor("I0"));
        instList.add(new instructor("I1"));
        studList.add(new student("SO"));
        studList.add(new student("S1"));
        studList.add(new student("S2"));
        int n;
        int d;
        while (true){
            System.out.println();
            System.out.println("Welcome to Backpack");
            System.out.println("1. Enter as instructor");
            System.out.println("2. Enter as student");
            System.out.println("3. Exit");
            n=sc.nextInt();
            if (n==1){
                for (int i=0;i<instList.size();i++){
                    System.out.println(i + " - " + instList.get(i).getID());
                }
                System.out.print("Choose ID: ");
                d=sc.nextInt();
                while(true){
                    System.out.println();
                    System.out.println("Welcome " + instList.get(d).getID());
                    System.out.println("1. Add class material");
                    System.out.println("2. Add assessments");
                    System.out.println("3. View lecture materials");
                    System.out.println("4. View assessments");
                    System.out.println("5. Grade assessments");
                    System.out.println("6. Close assessment");
                    System.out.println("7. View comments");
                    System.out.println("8. Add comments");
                    System.out.println("9. Logout");
                    int opt=sc.nextInt();
                    if (opt == 1){
                        System.out.println("1. Add Lecture Slide");
                        System.out.println("2. Add Lecture Video");
                        int a = sc.nextInt();
                        sc.nextLine();
                        if (a==1){
                            String topic;
                            int num;
                            System.out.print("Enter topic of slides: ");
                            topic=sc.nextLine();
                            System.out.print("Enter number of slides: ");
                            num=sc.nextInt();
                            String [] content=new String[num];
                            Scanner scan=new Scanner(System.in);
                            for (int i = 0;i<content.length;i++){
                                System.out.print("Content of Slide "+ (int)(i+1) + " : ");
                                content[i]=scan.nextLine();
                            }
                            String time=datetimegiver();
                            lectureSlides ls= new lectureSlides(topic, num, content,instList.get(d).getID(),time);
                            mat.add(ls);
                        }
                        else if (a==2){
                            String topic;
                            String name;
                            System.out.print("Enter topic of video: ");
                            topic=sc.nextLine();
                            System.out.print("Enter filename of video: ");
                            name=sc.nextLine();
                            if (name.length()<=4){
                                System.out.println("error in file format. Only .mp4 extensions acceptable");
                                continue;
                            }
                            if (!(name.substring(name.length()-4)).equals(".mp4")){
                                System.out.println("error in file format. Only .mp4 extensions acceptable");
                                continue;
                            }
                            String time=datetimegiver();
                            lectureVideos lv = new lectureVideos(topic, name, instList.get(d).getID(),time);
                            mat.add(lv);
                        }
                    }
                    else if(opt==2){
                        System.out.println("1. Add Assignment");
                        System.out.println("2. Add Quiz");
                        int a=sc.nextInt();
                        sc.nextLine();
                        if (a==1){
                            String problemStatement;
                            int maxMarks;
                            System.out.print("Enter problem statement: ");
                            problemStatement=sc.nextLine();
                            System.out.print("Enter max marks: ");
                            maxMarks=sc.nextInt();
                            assignment assign= new assignment(problemStatement, maxMarks);
                            assess.add(assign);
                            for (int i=0;i<instList.size();i++){
                                instList.get(i).addAssesment(assign);
                            }
                            for (int i=0;i<studList.size();i++){
                                studList.get(i).addAssesment(assign);
                            }

                        }
                        else if (a==2){
                            String ques;
                            System.out.print("Enter quiz question: ");
                            ques=sc.nextLine();
                            quiz q= new quiz(ques);
                            assess.add(q);
                            for (int i=0;i<instList.size();i++){
                                instList.get(i).addAssesment(q);
                            }
                            for (int i=0;i<studList.size();i++){
                                studList.get(i).addAssesment(q);
                            }
                            
                        }
                    }
                    else if (opt==3){
                        for (int i=0;i<mat.size();i++){
                            mat.get(i).view();
                        }
                    }
                    else if (opt==4){
                        for (int i=0;i<assess.size();i++){
                            assess.get(i).view(i);
                        }
                    }
                    else if (opt==5){
                        int count = 0;
                        int count1=0;
                        System.out.println("List of Assessments");
                        for (int i=0;i<assess.size();i++){
                            count1++;
                            assess.get(i).viewAll(i);
                        }
                        if(count1==0){
                            System.out.println("No assessments only.");
                            continue;
                        }
                        System.out.print("Enter ID of assessment to view submission: ");
                        int id=sc.nextInt();
                        for (int i=0;i<studList.size();i++){
                            // if (studList.get(i).getPending(id)==false)
                            if(!(studList.get(i).getSub(id).equals("No Subs"))){
                                count++;
                                System.out.println(i + " " + studList.get(i).getID());
                            }
                        }
                        if (count==0){
                            System.out.println(" No submissions done.");
                            continue;
                        }
                        int choice=sc.nextInt();
                        System.out.println("Submission: ");
                        studList.get(choice).getSubmission(id);
                        System.out.print("Max Marks: "+assess.get(id).viewMaxMarks());
                        System.out.println();
                        System.out.print("Marks Scored: ");
                        int marks=sc.nextInt();//will change later
                        studList.get(choice).gradeSubs(marks,id,choice,instList.get(d));
                    }
                    else if (opt==6){
                        int count=0;
                        System.out.println("List of open assignments: ");
                        for (int i=0;i<assess.size();i++){
                            count++;
                            assess.get(i).view(i);
                        }
                        if(count==0){
                            System.out.println("No open ASSIGNMENTS. SORRY");
                            continue;
                        }
                        System.out.print("Enter ID of assignment to close: ");
                        int closer=sc.nextInt();
                        assess.get(closer).setCloseStatus(true);
                        for (int i=0;i<studList.size();i++){
                            studList.get(i).setPending(closer, false);
                        }
                    }
                    else if(opt==7){
                        for (int i=0;i<comm.size();i++){
                            comm.get(i).viewComment();
                        }
                    }
                    else if (opt==8){
                        sc.nextLine();
                        System.out.print("Enter comment: ");
                        String content=sc.nextLine();
                        String time=datetimegiver();
                        comm.add(new comment(content, instList.get(d), time));
                    }
                    else if (opt==9){
                        break;
                    }

                }
            }
            if (n==2){
                System.out.println();
                for (int i=0;i<studList.size();i++){
                    System.out.println(i + " - " + studList.get(i).getID());
                }
                System.out.print("Choose ID: ");
                d=sc.nextInt();
                while(true){
                    System.out.println();
                    System.out.println("Welcome "+studList.get(d).getID());
                    System.out.println("1. View lecture materials");
                    System.out.println("2. View assessments");
                    System.out.println("3. Submit assessment");
                    System.out.println("4. View grades");
                    System.out.println("5. View comments");
                    System.out.println("6. Add comments");
                    System.out.println("7. Logout");
                    int opt=sc.nextInt();
                    if (opt==1){
                        for (int i=0;i<mat.size();i++){
                            mat.get(i).view();
                        }
                    }
                    else if (opt==2){
                        for (int i=0;i<assess.size();i++){
                            assess.get(i).view(i);
                        }
                    }
                    else if (opt==3){
                        int count=0;
                        System.out.println("Pending assessments");
                        for (int i=0;i<assess.size();i++){
                            if ((studList.get(d).getPending(i)==true) && (assess.get(i).getCloseStatus()==false)){
                                count++;
                                assess.get(i).view(i);
                            }
                        }
                        if (count==0){
                            System.out.println("No pending assignments");
                            continue;
                        }
                        System.out.print("Enter ID of assessment: ");
                        int a=sc.nextInt();
                        if(assess.get(a).getClass().getName().equals("assignment")){
                            System.out.print("Enter filename of assignment: ");
                            String subs=sc.next();
                            if (subs.length()<=4){
                                System.out.println("Wrong file format. Try again with a different file");
                                continue;
                            }
                            if (!(subs.substring(subs.length()-4).equals(".zip"))){
                                System.out.println("Wrong file format. Try again with a different file");
                                continue;
                            }
                            studList.get(d).addSubmission(subs,a);
                        }
                        else if (assess.get(a).getClass().getName().equals("quiz")){
                            System.out.print(assess.get(a).getQuest());
                            String subs=sc.next();
                            studList.get(d).addSubmission(subs, a);
                        }
                    }
                    else if (opt ==4){
                        studList.get(d).viewGrades();
                    }
                    else if (opt==5){
                        for (int i=0;i<comm.size();i++){
                            comm.get(i).viewComment();
                        }
                    }
                    else if(opt==6){
                        sc.nextLine();
                        System.out.print("Enter comment: ");
                        String content=sc.nextLine();
                        String time=datetimegiver();
                        comm.add(new comment(content, instList.get(d), time));
                    }
                    if (opt==7){
                        break;
                    }
                }
            }
            if (n==3){
                break;
            }
        }
    }
    private static String datetimegiver() {
        LocalDateTime ldt = LocalDateTime.now();
        TimeZone tz= TimeZone.getDefault();
        String zone=tz.getDisplayName(false, 0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss ");
        String formattedDate = ldt.format(dtf) + zone;
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern(" YYYY");
        String formattedYear=ldt.format(dtf1);
        formattedDate+=formattedYear;
        return formattedDate;
    }
}


