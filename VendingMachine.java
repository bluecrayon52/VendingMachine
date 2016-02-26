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
        System.out.println("This is the boss menu:"); 
        int i;  
        
       do{
        System.out.println("1-Set Up Dispenser\n"+
                           "2-Start Machine\n"+
                           "3-Restock Product\n"+
                           "4-Change Price\n"+
                           "5-Delete Product\n"+
                           "6-Shutdown");
        i = kb.nextInt();
         
        switch(i){
            case 1:{
              snackMachine.setUpDispenser();
              break; 
            }
            case 2: {
              break; 
            }
            case 3:{
              snackMachine.restockProduct();
              break; 
            }
            case 4: {
              snackMachine.changePrice();
              break;   
            }
            case 5:{
              snackMachine.deleteProduct();
            }
            case 6:{
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

    
