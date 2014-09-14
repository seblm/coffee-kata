package fr.ds.katacoffee;


import java.util.HashMap;
import java.util.Map;

public class DrinkMaker {
    private Map<Character, Integer> drinks = new HashMap<Character, Integer>();


    public String printHowManyDrinks() {
        String msg="";
        for(Character drink:drinks.keySet()){
            msg +=drink + ":" + drinks.get(drink) + ";";
        }
        return msg;
    }

    public void make(String msg) {
        System.out.println(msg);
        if(msg.charAt(0) != 'M'){
            Character cmd = msg.charAt(0);
            Integer nb = drinks.containsKey(cmd) ? drinks.get(cmd) : 0;
            drinks.put(cmd, ++nb);
        }
    }

}
