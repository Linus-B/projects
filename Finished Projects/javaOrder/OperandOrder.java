// Written by Linus Bendel-Stenzel Feb 14th, 2023
public class OperandOrder {

    // Creating what is essentially a global variable for our purposes.
    public static int myVariable = 4;

    // Adds 2 to the global variable, and returns 3. 
    public static int add2Return3(){
        myVariable += 2;
        return 3;
    }

    public static void main(String[] args){
        System.out.println("Variable starts as: " + myVariable);
        // When adding the function to my variable, myvariable will be different depending
        // on if it was incremented by the function or not. 
        myVariable = myVariable + add2Return3();
        System.out.println("If left to right, it will be 7. If right to left, it will be 9. ");
        System.out.println("Variable is now: " + myVariable);
        System.out.println("Now I'll switch the order, and see what happens");
        myVariable = 4;
        myVariable = add2Return3() + myVariable;
        System.out.println("Variable is now: " + myVariable);

    }
}