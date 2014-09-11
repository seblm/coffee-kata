package fr.ds.katacoffee;

public class Logic{

    private String protocolMsg;


    public Logic(Order order){
        switch (order.drinkType){
            case CHOCOLATE:protocolMsg="H";break;
            case TEA:protocolMsg="T";break;
                default:protocolMsg="C";break;
        };
        if(order.numOfSugar == 1){
            protocolMsg += ":1:0";
        }else if(order.numOfSugar == 2){
            protocolMsg += ":2:0";
        }else{
            protocolMsg += "::";
        }
    }

    String push(){
        return protocolMsg;
    }





    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
