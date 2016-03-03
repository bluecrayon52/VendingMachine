/*  
 * Author: Nathaniel Clay Arnold
 * Program 3 - Coinbox 
 * CSC230-02 Spring 2016
 */

package vendingmachine; 
import java.util.Scanner; 

public class Coinbox{ 
    private int numQ, numD, numN, amount; 
    Scanner kb = new Scanner(System.in); 
    
    /*
    Initialize number of each coin, set amount to 0
    */
    public Coinbox(int Q, int D, int N){
        numQ = Q;
        numD = D;
        numN = N; 
        amount = 0; 
    }
    
    /*
    PRE: Change is > 0. Assume correct change is always available.
    POST: change is distributed and coins in box are appropriately reduced.  
    Amount is reset to 0.  Displays statement showing coin distribution.
    */
    public void giveChange(int change){
      int Q = 0, D = 0, N = 0; 
      
      // exit method if no money was put in machine or no change is needed  
      if(change == 0){
          System.out.println("\nThere is no change to return.");
          amount = 0; 
          return; 
      }
       
       // give best possible change distribution 
        while(change > 0){
            
            if(change >= 25 && numQ > 0){
                Q++;
                numQ--; 
                change -= 25;
                continue; 
            }
            
            if(change >= 10 && numD > 0){
                D++; 
                numD--; 
                change -= 10;
               continue; 
            }
            
            if(change >= 5 && numN > 0){
                N++; 
                numN--; 
                change -= 5; 
                   
            }
            
      }
        // clear amount for next transaction 
        amount = 0; 
        
        // print out change distribution 
        System.out.print("\nYour Change is ");
        
             if(Q > 0){
                System.out.printf("%d quarter(s) ",Q);
                }
             
             if(D > 0){        
                System.out.printf("%d dime(s) ",D);
                }
             
             if(N > 0){
                System.out.printf("%d nickel(s)",N);
                }
             
             System.out.println();
    }
    
    /*
    Display Coinbox menu
    */
    public void displayCoins(){
        System.out.println("Coin Options: (Q)uarter, "
                + "(D)ime, (N)ickel, (R)efund. ");
        
    }
    
    /*
    Return amount deposited for current transaction
    */
    public int getAmount(){
        return amount; 
    }
    
    /*
    Evaluates choice. If choice is a coin (Q, D, N), then takeCoin(), 
    displayAmount(), and return true. If choice is (R), 
    then giveChange()and return false. Any other choice, return false.
    */
    public boolean option(char choice){ 
        
        // accept Upper lower case char 
        Character c = choice; 
         String d = c.toString();
         String e = d.toLowerCase(); 
         
        // check if coin choice is valid
        switch (e) {
            case "q":
            case "d":
            case "n":
                takeCoin(choice);
                displayAmount();
                return true;
                
            //refund 
            case "r":
                giveChange(amount);
          }
        
      return false; 
                    
    }
    
    /*
    PRE: coin must be a (Q)uarter, (D)ime, or (N)ickel  
    POST: increment appropriate coin in box and total amount for  transaction
    */
    private void takeCoin(char coin){
        Character c = coin; 
         String d = c.toString();
         String e = d.toLowerCase(); 
         
       // Add coin value to amount 
        switch(e){
            case "q":
                numQ++;
                amount+=25;
                break;
                
            case "d":
                numD++;
                amount+=10;
                break; 
                
            case "n":
                numN++;
                amount+=5;
                break; 
                
            default:
                break;      
        }
        
    }
    
    /*
    Display total deposited for current transaction
    */
    private void displayAmount(){
        System.out.print("\nTotal Diposited: " + amount+"\n");
    }
}