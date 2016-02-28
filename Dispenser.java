/*  
 * Author: Nathaniel Clay Arnold
 * Program 3 - Dispenser
 * CSC230-02 Spring 2016
 */

package vendingmachine; 
import java.util.Scanner; 
import java.util.InputMismatchException;


public class Dispenser{ 
    private Product[] items; 
    private int numItems; 
    Scanner kb = new Scanner(System.in); 
    
    //allocates the array (size 5) and initialize numItems to 0
    public Dispenser(){
        items = new Product[5]; 
        numItems = 0; 
        
        // pass each product to the constructor 
        for(int i = 0; i < 5; i++){
            items[i] = new Product(); 
        }
    }
    
    //returns a String that contains the products in a numbered list
    @Override
    public String toString(){
        
    return   "1-"+items[0].toString()+"\n"
            +"2-"+items[1].toString()+"\n"
            +"3-"+items[2].toString()+"\n"
            +"4-"+items[3].toString()+"\n"
            +"5-"+items[4].toString();
    }
    
    /*
    Evaluates choice.  
    If choice is valid (>=1 and <=numItems), return true.  
    Otherwise return false.
    */
    public boolean option(char choice){
        //convert char to an int 
        int ch = (int)choice - 48; 
        return ch >= 1 && ch <= numItems; 
    }
    
    /*
    Display products (use toString) 
    and allow BOSS to change price of any item in the dispenser
    */
    public void changePrice(){ 
        // exit this method if there are no products 
        if(numItems == 0){
            System.out.println("\nThere are no products "
                    + "to change the price of.");
            System.out.println("\nBack to the Boss menu!");
            return; 
        }
        
        System.out.println("\n"+toString()); 
        char ch; // used to ivaluate string choice 
        int changeMore = 1; // controls outer do while loop 
      
        
        do{ // while changeMore == 1, or as long as user doesn't enter B. 
            do{ // while unput is out of bounds 
                System.out.print("\nSelect an Item to change it's price"
                             + "(or enter B to go back to the Boss menu): ");
                
               String choice = kb.nextLine(); 
               
               // exit this method and return to bossWork 
               if(choice.equalsIgnoreCase("b")){ 
                   System.out.println("\nBack to Boss menu!");
                   return; 
               }
                ch = choice.charAt(0); 
        
                if(!option(ch)){ // check valid bounds of unput 
                    System.out.println("\nInvalid Input");
                }
        }while(!option(ch)); 
        
        int i = (int)ch -48; // convert char to an int 
        double newPrice =0; 
        int valid; // controls mismatch do while loop 
        
        do{
        System.out.print("\nenter the new price (or B for Boss menu): ");
        
        
            try{
            // set new price 
            newPrice = kb.nextDouble(); 
            items[i-1].setPrice(newPrice); 
            kb.nextLine(); // clear scanner buffer 
            valid = 0; // turn off do while loop 
            }
        
            catch(InputMismatchException e){
                
                //exit this method and return to bossWork
            if(kb.nextLine().equalsIgnoreCase("b")){
                System.out.println("\nBack to the Boss menu!");
                return; 
                }
            //turn on do while loop 
            System.out.println("\nInvalid input!");  
            valid = 1; 
            }
            
        }while(valid == 1); 
        
        System.out.println("\nThe price of "+items[i-1].getName()
        +" has been changed to "+items[i-1].getPrice());
        
    }while(changeMore  == 1);// always true 
    }
    
    /*
    Display products and allow BOSS to add stock 
    to any product in the dispenser
    */
    public void restockProduct(){
        
        // exit this method if there are no products 
        if(numItems == 0){
            System.out.println("\nThere are no products to restock.");
            System.out.println("\nBack to the Boss menu!");
            return; 
        }
        System.out.println("\n"+toString());
        char ch; // used to ivaluate string choice 
        int restockMore = 1; // controls outer do while loop 
        
        do{ // while restockMore ==1, or as long as user does't enter B 
            do{ // while unput is out of bounds 
                System.out.print("\nSelect an item to restock"
                        + "(or enter B to go back to the Boss menu): ");
        
                String choice = kb.nextLine(); 
              
                    // exit this method and return to bossWork 
                    if(choice.equalsIgnoreCase("b")){
                    System.out.println("\nBack to Boss Menu");
                    return; 
                    }
        
                    ch = choice.charAt(0); 
        
                    if(!option(ch)){ // check valid bounds of input 
                    System.out.println("\n\nInvalid Input");
                    }
                
                }while(!option(ch)); 
        
        int i = (int)ch -48; // convert char to an int 
        int newStock = 0; 
        int valid; // controls mismatch do while loop 
        
        do{
            System.out.print("\nEnter the number of item would you "
                + "like to add (or B for the Boss menu): ");
        
            try{
            // set new quantity 
            newStock =kb.nextInt();
            items[i-1].setQty(items[i-1].getQty()+newStock);
            kb.nextLine(); // clear scanner buffer 
             valid = 0; // turn off do while loop 
            }
        
            catch(InputMismatchException e){
                
                 // exit this method and return to bossWork
                 if(kb.nextLine().equalsIgnoreCase("b")){
                    System.out.println("\nBack to the Boss menu!");
                    return; 
                    }   
                 // turn on do while loop 
                 System.out.println("\nInvalid input!");
                 valid = 1; 
                 }
        
        }while(valid == 1); 
        
            System.out.println("\n"+toString());
            System.out.println("\nThe quantity of "+items[i-1].getName()+
                " has been increased to "+items[i-1].getQty());
        
    }while(restockMore ==1);// always true    
    }
    
    /*
    Return the price of the selected product.  
    If choice is not valid, return -1.
    */
    public double getPrice(char choice){
        if(option(choice)){
            return items[(int)choice -49].getPrice(); 
        } 
        
        return -1; 
    }
    
    /*
    Returns true if the choice is valid and is in stock,
    false otherwise
    */
    public boolean inStock(char choice){
        return option(choice) && items[(int)choice -49].getQty() > 0;
         
    }
    
    /*
    If item is not out of stock, one piece is removed 
    from the dispenser and given to the user, 1 is returned.  
    If item is out of stock, or invalid, 0 is returned
    */
    public int dispense(char choice){
    if(option(choice)){
        if(inStock(choice)){
         items[(int)choice - 49].setQty(items[(int)choice - 49].getQty() - 1);
          return 1; 
         } 
    }
     return 0; 
    }
    
    /*
    If there is room for more products, 
    allow BOSS to enter name, price and inventory for a new product 
    and place the item in the Dispenser, increment numItems
    */
    public void setUpDispenser(){
        
        // check if machine is full
        int count = 0; 
        for(int i = 0; i <5; i++){
            if(items[i].getQty()!=0){
             count++;    
            }  
        }
        
        // if machine is full 
        if(count == 5){
            System.out.println("\nThere is currently no space in the "
                    + "Vending Machine.");
            System.out.println("Please remove a product "
                    + "to add something new.");
            // exit this method and return to bossWork 
            System.out.println("\nBack to Boss menu!");
            return; 
        }
        
        /* 
        set up incrementally to avoid discrepency 
        in numItems as a boundary 
        */ 
        outerloop:
        for(int i = 0; i < 5; i++){
           
            /*
            if space is vacant or pruduct is empty, allow setup to overwrite
             instead of having to delete an empty pruduct 
             that is being discontinued 
            */  
            int c = i+1; // to print correct number
            
            // if a space is empty but labeled for a product 
          if( items[i].getQty() == 0 && !items[i].getName().matches("")){
                int valid; // controls input validity loop 
                kb.nextLine(); // clear scanner buffer 
                
                do{
                   
                valid = 0; 
                // notify user of label and give options
                System.out.println("\nSpace "+c+" is empty but set up for "
                        +items[i].getName()+".");
                System.out.print("\nEnter Y to proceed with overwriting\n"
                        + "N to move to the next available space\n"
                        + "or B to go back to the Boss menu: ");
                
                // allow overwriting 
                if(kb.nextLine().equalsIgnoreCase("y")){
                    System.out.println("\nProceeding!");
                }
                // break for loop and increment 
                else if(kb.nextLine().equalsIgnoreCase("n")){
                    System.out.println("\nMoving on to the next "
                            + "available space!");
                    continue outerloop; // increment for loop to next space
                }
                // exit this method and return to bossWork
                else if(kb.nextLine().equalsIgnoreCase("b")){
                    System.out.println("\nBack to the Boss Menu!");
                    return; 
                }
                // turn on do while loop 
                else{
                    System.out.println("\nInvalid input!");
                    valid = 1; 
                }
                
            }while(valid == 1); 
                 
          }    
            if(items[i].getQty()== 0){
               
               // display products 
               System.out.println(toString());
               
                
               System.out.println("\nSet up for space "+c+":"); 
               System.out.print("\nEnter product name "
                              + "(or B to go Back to the Boss menu): ");
               
               String name = kb.nextLine();
               
                // exit this method and return to bossWork 
                if(name.equalsIgnoreCase("b")){
                    System.out.println("\nBack to Boss menu!");
                    return; 
                }
               
               // set the name of the product
                items[i].setName(name); 
                numItems++; // increment items
                
                
                double price = 0; 
               int valid; // controls input mismatch loop 
                
            do{// while valid == 1 
                System.out.print("\nEnter price per unit"
                        + " (or B for the Boss menu): ");
                
                try{
                 // set the price of the product   
                 price = kb.nextDouble();
                 items[i].setPrice(price); 
                 valid = 0;
                 kb.nextLine(); 
                }
                
                catch(InputMismatchException e){
                 
               //exit this method and return to bossWork 
               if(kb.nextLine().equalsIgnoreCase("b")){
                   System.out.println("\nBack to the Boss menu!");
                   return;
                   }
                    // turn on do while loop 
                    System.out.println("\nInvalid input!");   
                    valid = 1; 
                    
                }
            }while(valid == 1);
            
            int quantity = 0; 
             
            do{ // while valide == 1
                System.out.print("\nEnter quantity of stock inventory "
                        + "(or B for the Boss menu): ");
                
                try{
                // set quantity of product     
                quantity = kb.nextInt(); 
                items[i].setQty(quantity);
                kb.nextLine();  // clear scanner buffer 
                }
                
                catch(InputMismatchException e){
                    
                //exit this method and return to bossWork 
                if(kb.nextLine().equalsIgnoreCase("b")){
                    System.out.println("\nBack to the Boss menu!");
                    return; 
                    }
                    // turn on do while loop 
                    System.out.println("\nInvalid input!");
                    valid = 1; 
                }
            }while(valid == 1); 
            
                
                System.out.println("\nSpace "+c+" is now set up with "
                        + items[i].getName()+".");
                
            }
        
        } // end of for loop, machine is full   
         
        System.out.println(toString());
        System.out.println("\nThe Vending Machine is now full."); 
        System.out.println("\nBack to the Boss menu!");
    }
    
    /*
    Display products and allow BOSS to select and remove 
    a product from the dispenser, decrement numItems
    */
    public void deleteProduct(){
        System.out.println(toString());
        
        // exit this method if no items are in vending machine
        if(numItems == 0){
            System.out.println("\nThe Vending Machine is empty, "
                    + "there are no pruducts to delete.");
            System.out.println("\nBack to the Boss menu!");
            return; 
            }
        
        int deleteMore =1;
        
        do{
             
        System.out.print("\nSelect a product to remove"
                + " (or press B to go back to the Boss Menu): ");
        
            String choice = kb.nextLine(); 
            
            //exit this method and return to bossWork 
            if(choice.equalsIgnoreCase("b")){
                System.out.println("\nBack to the Boss menu!");
                return; 
            }
            
            char ch = choice.charAt(0); // convert string to char 
            
            if(option(ch)){ // validate char 
                
                int i = (int)ch - 48; // convert char to int 
                
                // check for complete emptyness 
                if(items[i-1].getName().equals("")&&
                   items[i-1].getPrice()== 0 &&
                   items[i-1].getQty()== 0){
                        System.out.println("\nThere is no product "
                                + "in this location."); 
                }
                
                else{ // allow any fragmented data to be deleted 
                    
                   items[i-1]= new Product();  
                   System.out.println("\nThe product in space "+i+" "
                        + "has been removed.");
                   numItems--; 
                    
                   // empty notification and short cercuit to bossWork 
                    if(numItems == 0){
                        System.out.println("\n"+toString());
                        System.out.println("\nThe Vending Machine "
                                            + "is now empty");
                        
                        //exit this method and return to bossWork 
                        System.out.println("\nBack to the Boss menu!");
                        return; 
                    }
                    
                    // shift last product to any gap in array 
                    else if(i <= numItems){
                        items[i-1] = items[numItems];    
                        
                        // empty last product space 
                        items[numItems]=new Product(); 
                        System.out.println("\n"+toString()); 
                    } 
                          
                }
            }
            
            else{
                
                System.out.println("\nInvalid Input!");
            }
            
        }while(deleteMore == 1); //always true 

    }
}