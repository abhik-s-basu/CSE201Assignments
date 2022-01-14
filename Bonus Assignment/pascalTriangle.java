import java.util.InputMismatchException;
import java.util.concurrent.*;
import java.util.concurrent.*;

/**
 * 2020165
 */
public class pascalTriangle  {

    static class pascal extends RecursiveTask<Integer>{
        volatile int n;
        volatile int k;
        static int threshold = 5;

        public pascal (int row, int column){
            n = row;
            k = column;
        }

        public int sequentialPascal (int n, int k){
            if (n == 0 || k == 0 || n == k){
                return 1; //base case 
            }
            else return sequentialPascal(n-1, k-1) + sequentialPascal(n-1, k);//recursive subroutine
        }

        public Integer compute(){
            if (n == k ){
                return 1; // we have taken this base condition since if n and k are equal it should return 1
                         //  say if we meet a case like 15,15 then 15,14 and 14,14 should not be calculated
            }
            if (n < threshold || k < threshold){
                return sequentialPascal(n, k);
            }
            pascal upperLeft = new pascal (this.n-1,this.k-1);
            pascal upperRight = new pascal (this.n-1,this.k);
            upperLeft.fork();// will execute asynchronously, adds upperLeft to taskQueue
            return upperRight.compute() + upperLeft.join(); // the upperRight will execute sequentially
        }


    }
    public static void main(String[] args) throws IndexOutOfBoundsException{
        try{
            // we take the command line input of the total number of threads 
            // should be present in our thread pool
            int numThreads = Integer.parseInt(args[0]);
            // 
            ForkJoinPool pools = new ForkJoinPool(numThreads);
            // we create the root task of n=30 and k=10, it will make sure all sub tasks get terminated
            pascal task = new pascal(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
            int result = pools.invoke(task);
            System.out.println("Result for 30th row and 10th Column is " + result);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("enter a valid integer argument in the command line");
        }
        catch (NumberFormatException e){
            System.out.println("enter an integer and not a string");
        }
        
    }
}