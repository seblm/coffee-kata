package fr.ds.katacoffee;

import java.math.BigDecimal;

public class Logic{

    private String protocolMsg;
    private double amount;

    public Logic(Order order){
        add(order);
    }

    public void add(Order order) {
        BigDecimal diff = order.drinkType.cost.subtract(order.money) ;

        if(diff.compareTo(BigDecimal.ZERO) == 1){
            protocolMsg = "M:"+ diff;
            return;
        }
        amount += order.drinkType.cost.doubleValue();

        switch (order.drinkType){
            case CHOCOLATE:
                protocolMsg=  "H";
                break;
            case TEA:
                protocolMsg= "T";
                break;
            case COFFEE:
                protocolMsg= "C";
                break;
            case ORANGE:
                protocolMsg= "O";
                break;

            default:
                throw new RuntimeException("unknown command");
        };
        protocolMsg += order.extraHot;

        if(order.numOfSugar == 1){
            protocolMsg += ":1:0";
        }else if(order.numOfSugar == 2){
            protocolMsg += ":2:0";
        }else{
            protocolMsg += "::";
        }

    }

    public String push(){
        return protocolMsg;
    }

    public String printAmountMoney() {
        return String.valueOf(amount);
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
