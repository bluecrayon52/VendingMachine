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
    
    public void giveChange(int change){
      int Q = 0, D = 0, N = 0; 
      
      // exit method if no money was put in machine 
      if(change == 0){
          System.out.println("\nThere is no change to return.");
          return; 
      }
        while(change > 0){
            if(change >= 25 && numQ > 0){
                Q++;
                numQ--; 
                change -= 25;
                break; 
            }
            
            if(change >= 10 && numD > 0){
                D++; 
                numD--; 
                change -= 10;
                break;
            }
            
            if(change >= 5 && numN > 0){
                N++; 
                numN--; 
                change -= 5; 
                break;     
            }
            
      }
        amount = 0; 
        
        System.out.print("Your Change is ");
        
             if(Q > 0){
                System.out.printf("%d quarters ",Q);
                }
             
             if(D > 0){        
                System.out.printf("%d dimes ",D);
                }
             
             if(N > 0){
                System.out.printf("and %d nickels",N);
                }
        
    }
    
    public void displayCoins(){
        System.out.println("Coin Options: (Q)uarter, (D)ime, (N)ickel, (R)efund. ");
        
    }
    
    public int getAmount(){
        return amount; 
    }
    
    public boolean option(char choice){ 
        
        // accept Upper lower case char 
        Character c = choice; 
         String d = c.toString();
         String e = d.toLowerCase(); 
         
        // check if coin choice is valid 
        if(e.equals("q")|| e.equals("d") || e.equals("n")){
            
           takeCoin(choice); 
           displayAmount(); 
           return true; 
        }
        // check for refund 
        else if(e.equals("r")){
            giveChange(amount);
            return false; 
        }
        
      return false; 
                    
    }
    
    private void takeCoin(char coin){
        Character c = coin; 
         String d = c.toString();
         String e = d.toLowerCase(); 
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
    
    private void displayAmount(){
        System.out.print("Total Diposited: " + amount);
    }
}