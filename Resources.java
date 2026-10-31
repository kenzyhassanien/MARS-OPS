/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Lenovo
 */
public abstract class Resources {
    
    private String name;
    private int quantity;

    public Resources(String name, int quantity) {
        this.name = name;
        if(quantity < 0)
        {
            System.out.println("Quantity cannot be negative");
            this.quantity=0;
        }
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else
        System.out.println("quantity cannot be negative.");
    }
    
    public void addQuantity(int amount)
    {
        if (amount <= 0) {
          System.out.println("quantity cannot be negative.");
        }
        else
          quantity += amount;
    }
    
    public boolean consumeQuantity(int amount) {
        if (amount <= 0||amount>quantity) {
            return false;
        }
        quantity -= amount;
        return true;
    }
}