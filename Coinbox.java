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
        System.out.print("Enter a Coin\n"
                + "((Q)uarter, (D)ime, (N)ickel, (R)efund): ");
        
    }
    
    public int getAmount(){
        return amount; 
    }
    
    public boolean option(char choice){
        if(choice == 'Q'||choice == 'D'|| choice =='N'){
           takeCoin(choice); 
           displayAmount(); 
           return true; 
        }
        else if( choice == 'R'){
            giveChange(amount);
            return false; 
        }
        
      return false; 
                    
    }
    
    private void takeCoin(char coin){
        switch(coin){
            case 'Q':
                numQ++;
                amount+=25;
                break;
                
            case 'D':
                numD++;
                amount+=10;
                break; 
                
            case'N':
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