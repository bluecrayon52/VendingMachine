package vendingmachine; 
import java.util.Scanner; 

public class VendingMachine{ 
    private Dispenser snackMachine; 
    private Coinbox moneyBox; 
    Scanner kb = new Scanner(System.in); 
    
    public VendingMachine(){
        snackMachine = new Dispenser(); 
        moneyBox = new Coinbox(5,5,5); 
            if(!bossWork()){
                run(); 
            } 
            else{
                System.out.println("Vending Machine shutdown, Goodbye");
                System.exit(0); 
            }
    }
    
    public void run(){
       showUserChoice(); 
    }
    
    public void showUserChoice(){
        
        System.out.println(snackMachine.toString());
        System.out.println("enter money first and then select a product");
        moneyBox.displayCoins();
        String s = kb.nextLine(); 
        char c = s.charAt(0); 
        serviceCustomer(c); 
    }
    
    private void serviceCustomer(char choice){
       while(moneyBox.option(choice)){
         
       }
       if(snackMachine.option(choice)){
           if(moneyBox.getAmount() >= snackMachine.getPrice(choice)){
             snackMachine.dispense(choice); 
           }
      
       } 
    }
    
    private boolean bossWork(){
        System.out.println("This is the Boss menu for "
                            + "the Vending Machine:"); 
        int i;  
        
       do{
        System.out.print("\n1-Set Up Dispenser\n"+
                           "2-Start Machine\n"+
                           "3-Restock Product\n"+
                           "4-Change Price\n"+
                           "5-Delete Product\n"+
                           "6-Shutdown\n"+
                           "\nMake a Selection(1-6): ");
        i = kb.nextInt();
         
        switch(i){
            case 1:{
              kb.nextLine(); 
              snackMachine.setUpDispenser();
              break; 
            }
            case 2: {
              kb.nextLine();
              break; 
            }
            case 3:{
              kb.nextLine(); 
              snackMachine.restockProduct();
              break; 
            }
            case 4: {
              kb.nextLine(); 
              snackMachine.changePrice();
              break;   
            }
            case 5:{
              kb.nextLine(); 
              snackMachine.deleteProduct();
              break;
            }
            case 6:{
                
                int input = 0; 
                kb.nextLine(); 
                
               do{
                System.out.print("Are you shure you want to "
                        + "shutdown the Vending Machine?(Y,N):");
                String choice = kb.nextLine(); 
                
                     if(choice.equalsIgnoreCase("y")){
                        input = 1;     
                     }
                
                     else if(choice.equalsIgnoreCase("n")){
                        i = 0;
                        input = 1; 
                        System.out.println("Back to the Boss Menu!");
                     }
                     
                     else{ 
                        System.out.println("Invalid Input"); 
                     }
                     
               }while(input ==0); 
               
               break;  
            }
            
            default:{
                System.out.println("Invalid Input");
                break; 
            }
        }
        
    }while(i != 2 && i != 6);
    
  return i == 6; 
}   
    
}

    
