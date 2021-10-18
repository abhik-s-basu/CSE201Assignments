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

