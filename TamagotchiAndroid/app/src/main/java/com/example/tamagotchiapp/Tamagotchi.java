package com.example.tamagotchiapp;
@SuppressWarnings("WeakerAccess")
public class Tamagotchi extends Thread {


    private int food;

    public int number;
    public volatile boolean alive;
    public String foodText = "Food Amount: ";
    public String title = "Tamagotchi";
    public Tamagotchi(Observer cb){
        callbackInterface = cb;
        setFood(10);
        this.alive = true;

    }

    Observer callbackInterface = null;

    public interface Observer{
        void StatusReport(int food);
    }

    @Override
    public void run() {
        try {
            while (alive) {
                if (food > 0 && food < 20){
                    callbackInterface.StatusReport(food);
                    food --;
                }
                else{
                    callbackInterface.StatusReport(food);
                    alive = false;
                }
                sleep(1000);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getFood() {
        return food;
    }
    public void setFood(int food) {
        this.food = food;
    }
    public void addFood(){
        this.food = food + 10;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFoodText() {
        return foodText;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}

