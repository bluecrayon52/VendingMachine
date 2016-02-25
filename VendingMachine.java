package vendingmachine; 
import java.util.Scanner; 

public class VendingMachine{ 
    private Dispenser snackMachine; 
    private Coinbox moneyBox; 
    Scanner kb = new Scanner(System.in); 
    
    public VendingMachine(){
    snackMachine = new Dispenser(); 
    moneyBox = new Coinbox(5,5,5); 
    if(bossWork()){
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
           
       } 
    }
    
    private boolean bossWork(){
        
    }
}   
