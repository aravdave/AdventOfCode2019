// Arav Dave
// 4/14/2020
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {

        //This block gets the start and end numbers in the range from the user.
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the first number in the range please. ");
        int firstNum = keyboard.nextInt();
        System.out.print("Enter the last number in the range please. ");
        int secondNum = keyboard.nextInt();

        //These are variables to count the quantity of numbers that qualify the requirements for part one and two.
        int numOfViable = 0;
        int numOfExclusivePairNum = 0;

        //The for loop goes through all the possible numbers from the beginning to the end of the range.
        for(int a = firstNum; a <= secondNum; a++) {
            System.out.println(a);

            //Made each number a string to covert each character in the string to an integer type.
            String number = a + "";

            //These three integers are used for part two to ensure that the pair of adjacent matching numbers are not part of a larger group of matching numbers. 
            int numBefore = 0;
            int numBeforeBefore = 0;
            int numBeforeBeforeBefore = 0;

            //These variables are useful to check if certain reqs are true.
            boolean pair = false;
            boolean exclusivePair = false;
            boolean stop = false;

            //Allows me to speed up the first for loop by changing a to the next possible qualifying number.
            String partialNum = "";

            //This for loop allows me to go through every digit in the number and compare each digit to those before it.
            for (int b = 0; b < number.length(); b++) {
                String newNum = "";
                int numInNum = Character.getNumericValue(number.charAt(b));

                //This if statement ensures that the digits are equivalent or ascending in the number
                if(numInNum > numBefore || numInNum == numBefore) {
                    partialNum = partialNum.concat(Integer.toString(numInNum));

                    //Checks to see if there's a pair of matching digits
                    if (numInNum == numBefore) {
                        pair = true;
                        /*Makes exclusivePair true if the two matching digits are the last in the number.
                          Typically, the matching pair make pair=true when the digit after the exclusive pair is going through the second for loop,
                          which wouldn't be possible if the exclusive pair is the last two digits of the number.
                        */
                        if (((number.length()-1) == b) && (numBeforeBefore != numBefore)) {
                            exclusivePair = true; }
                    }
                    //Checks to see if there's an exclusive pair the two digits before this digi(numInNum).
                    else if ((numBeforeBeforeBefore != numBeforeBefore) && (numBeforeBefore == numBefore)) {
                        exclusivePair = true; }
                }
                //Makes sure the number does not qualify for numOfViable++ or numOfExclusivePairNum++
                // even if the conditional for those are true when the number does not follow the reqs (requirements).
                else {
                    //Makes the first for loop way more efficient by changing a to the next possible qualifying number.
                    newNum = partialNum;                    
                    for(int c = partialNum.length(); c < number.length(); c++){
                        newNum = newNum.concat(Integer.toString(numBefore));
                    }
                    a = (Integer.parseInt(newNum) - 1);
                    stop = true;
                    break;
                }
                //Shifts the values of these numbers to the left once.
                numBeforeBeforeBefore = numBeforeBefore;
                numBeforeBefore = numBefore;
                numBefore = numInNum;
            }
            //Makes sure that the number did not cause the second for loop to break because of an unmet req.
            if ((pair == true) && (stop == false)){
                numOfViable++;
                if (exclusivePair) {
                    numOfExclusivePairNum++; }
            }
        }
        System.out.println("\nQuanity of applicable numbers for part one: " + numOfViable);
        System.out.println("Quanity of applicable numbers for part two: " + numOfExclusivePairNum);
        keyboard.close();
    }
}