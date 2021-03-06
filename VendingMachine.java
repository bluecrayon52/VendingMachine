/*  
 * Author: Nathaniel Clay Arnold
 * Program 3 - VendingMachine
 * CSC230-02 Spring 2016
 */

package vendingmachine; 
import java.util.Scanner; 
import java.util.InputMismatchException; 

public class VendingMachine{ 
    private Dispenser snackMachine; 
    private Coinbox moneyBox; 
    Scanner kb = new Scanner(System.in); 
    
    /*
    Instantiates snackMachine and moneybox, 
    calls bossWork() – fill the moneybox with 5 of each type of coin
    */
    public VendingMachine(){
        snackMachine = new Dispenser(); 
        moneyBox = new Coinbox(5,5,5);
        
        // evaluate for shutdown 
            if(bossWork()){
                System.out.println("Vending Machine shutdown, Goodbye");
                System.exit(0); 
            }
    }
    /*
    Starts the vending machine user mode
    */
    public void run(){
        System.out.println("\nWelcome!");
        System.out.println("\nThis is the user mode for the Vending Machine.");
       showUserChoice(); 
    }
    
    /*
    Show the user the products, the coinbox menu, 
    and tell user what to do: “enter money first and then select a product”
    */
    private void showUserChoice(){
        
        System.out.println("\n"+snackMachine.toString());
        moneyBox.displayCoins();
        
      if(moneyBox.getAmount()== 0){
        System.out.print("\nEnter money first and then select a product: ");
      }
      else{
          System.out.print("\nSelect a Product or enter more money: ");
      }
        String s = kb.nextLine(); 
        char c = s.charAt(0); 
        serviceCustomer(c); 
    }
    
    /*
    Take money while choice is a coin, then process product selection
    */
    private void serviceCustomer(char choice){
        
      // select B to go to the Boss menu 
      if(choice == 'B'|| choice == 'b'){
          
          // ivaluate bossWork for restart  
           if(bossWork()){
               
          // shutdown
            System.out.println("Vending Machine shutdown, Goodbye");
            System.exit(0); 
            }
           
      }
      
      if(choice == 'R'|| choice == 'r'){
         
         moneyBox.giveChange(moneyBox.getAmount());  
                  showUserChoice();   
      }
      
      // check validity of coins 
       while(moneyBox.option(choice)){
         showUserChoice();  
         
       }
       
       // check validity of product choice 
       if(snackMachine.option(choice)){
           
           // check that enough money has been entered 
           if(moneyBox.getAmount() >= snackMachine.getPrice(choice)*100){
                  
                  snackMachine.dispense(choice); 
                  
                  // calculate change 
                  double calcprice = snackMachine.getPrice(choice)*100; 
                  int change = moneyBox.getAmount() - (int)calcprice; 
                  
                  // give customer change 
                  moneyBox.giveChange(change);  
                  showUserChoice();      
           }
           else{
               System.out.println("\nYou have not entered enough "
                       + "money for that selection yet!");
               showUserChoice(); 
           }
        } 
       
       else{ 
           System.out.println("\nInvalid input!");
           showUserChoice(); 
       }
    }
    
    /*
    Give the boss menu, process boss choices 
    while choice is not shutdown option.  
    Return true for shutdown option and false for start machine option
    */
    private boolean bossWork(){
        System.out.println("Welcome!");
        System.out.println("\nThis is the Boss menu for "
                            + "the Vending Machine:"); 
        int i = 0; 
        
        outerloop: 
       do{
        // show boss mnu 
        System.out.print("\n1-Set Up Dispenser\n"+
                           "2-Start Machine\n"+
                           "3-Restock Product\n"+
                           "4-Change Price\n"+
                           "5-Delete Product\n"+
                           "6-Shutdown\n"+
                           "\nMake a Selection(1-6): ");
        
        // catch non int types 
        try{
        i = kb.nextInt();
        } 
        catch(InputMismatchException e){
            System.out.println("\nInvalid Input."); 
            kb.nextLine(); 
            continue; 
        }
        
       // ivaluate boss choice   
        switch(i){
            case 1:
              kb.nextLine(); 
              snackMachine.setUpDispenser();
              break; 
            
            case 2: 
              kb.nextLine();
              run(); // start up vending machine 
              break; 
            
            case 3:
              kb.nextLine(); 
              snackMachine.restockProduct();
              break; 
            
            case 4: 
              kb.nextLine(); 
              snackMachine.changePrice();
              break;   
            
            case 5:
              kb.nextLine(); 
              snackMachine.deleteProduct();
              break;
            
            case 6:
                
                int input = 0; 
                kb.nextLine(); 
                
               do{
                   
                // double check with boss before shutdown 
                System.out.print("\nAre you shure you want to "
                        + "shutdown the Vending Machine?(Y,N):");
                String choice = kb.nextLine(); 
                
                     if(choice.equalsIgnoreCase("y")){
                        input = 1;     
                     }
                
                     else if(choice.equalsIgnoreCase("n")){
                        i = 0; // reset boss choice to stay in loop 
                        
                        input = 1; 
                        System.out.println("\nBack to the Boss Menu!");
                     }
                     
                     else{ 
                        System.out.println("\nInvalid Input"); 
                     }
                     
               }while(input == 0); 
               
               break;  
            
            // for int types not in bounds of choices 
            default:
                System.out.println("\nInvalid Input");
                break; 
            
        }
        
    }while(i != 6);
    
  return i == 6; 
}   
    
}

    
