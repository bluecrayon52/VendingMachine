/*  
 * Author: Nathaniel Clay Arnold
 * Program 3 - Product 
 * CSC230-02 Spring 2016
 */
package vendingmachine; 

public class Product{ 
   private double prodPrice; 
   private String prodName; 
   private int qty; 
   
   /*
   Initialize name with “”, quantity and price to 0
   */
   public Product(){
       prodName =""; 
       prodPrice = 0; 
       qty = 0; 
   } 
   
   /*
   Initialize fields with appropriate arguments
   */ 
   public Product(String name, double price, int quantity){
       prodName = name; 
       prodPrice = price; 
       qty = quantity; 
   } 
    
   /*
   Sets the name of the product to the value of the parameter
   */ 
   public void setName( String name){
       prodName = name; 
   }
   
   /*
   Returns the name of the product
   */
   public String getName(){
       return prodName;
   }
   
   /*
   Sets the price of the product to the value of the parameter
   */
   public void setPrice(double price){
       prodPrice = price; 
   }
   
   /*
   Returns the price of the product
   */
   public double getPrice(){
       return prodPrice; 
   }
   
   /*
   Sets the quantity of the product to the parameter
   */
   public void setQty(int quantitiy){
       qty = quantitiy; 
   }
   
   /*
   Gets the quantity of the product
   */
   public int getQty(){
       return qty; 
   }
   
   /*
   returns a String representation 
   of the product in the following format:   (Name, Price, Quantity)
   */
   
   @Override
   public String toString(){
       return "("+prodName+", " +prodPrice+", "+qty+ ")";
   }
    
   
}

