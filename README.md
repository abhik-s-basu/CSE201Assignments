# CSE201 Assignments Basic Details

<ins> <h2> <b> Assignment 1 </b> </h2> </ins>

<h3>This file has 5 classes and each class has all the objects as private.Inorder to access any element getter and setter functions have been used.</h3> <br>
<h4>The classes are as follows:</h4>
1. <b>vaccine</b> - class which has got all the details of the vaccine which exists <br>
2. <b>hospital</b> - class which has got all details of the hospital and the slots and other such relevant information <br>
3. <b>slots</b> - helper class to facilitate in making/adding slots in hospital class <br>
4. <b>citizen</b> - class which stores all the relevant information with respect to the citizen including their vaccine details <br>
5. <b>cowin </b> - this is the main portal where all the input is being handled. it is through this class that all other classes are invoked. This is the class                         where all the inputs happen <br>


<h4>Classes referenced by each class:</h4>

1. <b>vaccine</b> -> hospital
2. <b>hospital</b> -> slots, vaccine
3. <b>slots</b> ->  NO OTHER CLASS
4. <b>citizen</b> -> vaccine,hospital
5. <b>cowin</b> -> vaccine,hospital,citizen


<ins> <h2> <b> Assignment 2 </b> </h2> </ins>

<h3>This file has 8 classes and 3 interfaces and each class has all the objects as private.Inorder to access any element getter and setter functions have been used.</h3> <br>
<h4>The classes are as follows:</h4>
1. <b>instructor</b> - class which stores relevant information related to the instructors of the course <br>
2. <b>student</b> - class which stores relevant information related to the students of the course like grading,submitting of assignments etc. <br>
3. <b>lectureVideo</b> - helper class to facilitate in add lecture video to lecture materials which is accesible by everyone<br>
4. <b>lectureSlides</b> - helper class to facilitate in add lecture slides to lecture materials which is accesible by everyone <br>
5. <b> assignment </b> - helper class to facilitate in adding assignments to the backpack portal and other related operations <br>
6. <b> quiz </b> - helper class to facilitate in adding quizzes to the backpack portal and other related operations <br>
7. <b> comment </b> - this is the class where the comments portal is technically maintained and view and add comment operations are done here <br>
8. <b>backpack </b> - this is the main portal where all the input is being handled. it is through this class that all other classes are invoked. This is the class                         where all the inputs happen and data validation happens <br>


<h4>Interfaces used in the programme </h4>

1. <b>user</b> -> implemented by classes <em>instructor</em> and <em>student</em>
2. <b>material</b> -> implemented by classes <em>lectureSlides</em> and <em>lectureVideo</em>
3. <b>assessments</b> ->  implemented by classes <em>quiz</em> and <em>assignment</em>
