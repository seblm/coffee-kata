package fr.xebia.photobooth.domain;


public enum Logic {
	INSTANCE;
    
    public String run(Order order){
    	//TODO
        //should check logic, rights, ...
        //throw new NotAllowedException();    
    	
		Command command = new Command(order.photoType, order.color);
		return PhotoMaker.INSTANCE.make(command);			
    }
}
