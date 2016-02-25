package vendingmachine; 
import java.util.Scanner; 

public class Dispenser{ 
    private Product[] items; 
    private int numItems; 
    Scanner kb = new Scanner(System.in); 
            
    public Dispenser(){
        items = new Product[5]; 
        numItems = 0; 
    }
    
    @Override
    public String toString(){
        
    return   "1-"+items[0].getName()+"\n"
            +"2-"+items[1].getName()+"\n"
            +"3-"+items[2].getName()+"\n"
            +"4-"+items[3].getName()+"\n"
            +"5-"+items[4].getName();
    }
    
    public boolean option(char choice){
        int ch = (int)choice; 
        return ch >= 1 && ch <= numItems; 
    }
    
    public  void changePrice(){ 
        System.out.println(toString()+"\n");
        System.out.print("Which item's price would you like"
                + "to change? ");
        int i = kb.nextInt(); 
        System.out.print("enter the new price: ");
        double j = kb.nextDouble(); 
        items[i-1].setPrice(j);  
        System.out.println("The price of "+items[i-1].getName()
        +" has been changed to "+items[i-1].getPrice());
    }
    
    public void restockProduct(){
        System.out.println(toString());
        System.out.print("Select an item to restock: ");
        int i = kb.nextInt(); 
        System.out.print("How many of this item would you "
                + "like to add? ");
        int j =kb.nextInt();
        items[i-1].setQty(items[i-1].getQty()+j);
        System.out.println("The quantity of "+items[i-1].getName()+
                " has been increased to "+items[i-1].getQty());
        
    }
    
    public double getPrice(char choice){
        if(option(choice)){
            return items[(int)choice -1].getPrice(); 
        } 
        
        return -1; 
    }
    
    public boolean inStock(char choice){
        return option(choice) && items[(int)choice -1].getQty() > 0;
         
    }
    
    public int dispense(char choice){
    if(option(choice)){
        if(items[(int)choice - 1].getQty()>0){
            items[(int)choice - 1].setQty(items[(int)choice - 1].getQty() - 1);
            return 1; 
         } 
    }
     return 0; 
    }
    
    public void setUpDispenser(){
        
        for(int i = 0; i < 5; i++){
            
            if(items[i]==null||items[i].getPrice()== 0){
           
               int c = i+1; 
               System.out.println("Set up for space "+c+":"); 
                System.out.print("Enter product name: ");
                String name = kb.nextLine(); 
             
                
                System.out.print("Enter price per unit: ");
                double price = kb.nextDouble(); 
             
                
                System.out.print("Enter quantity of stock inventory: ");
                int quantity = kb.nextInt(); 
              
                items[i] = new Product(name, price, quantity); 
                
                kb.nextLine(); 
                
                numItems++; 
            }
        
        }  
    }
    
    public void deleteProduct(){
        System.out.println(toString());
        System.out.print("Select a product to remove: ");
        int i = kb.nextInt(); 
        if(option((char)i)){ 
        items[i-1]= new Product();  
            System.out.println("The product in space "+i+" has been removed.");
        numItems--; 
        }
    }
}
