package fr.xebia.photobooth.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.xebia.photobooth.domain.Photo.PhotoType;

import java.util.HashMap;
import java.util.Map;

public enum PhotoMaker {
	INSTANCE;
	
    static final Logger log = LoggerFactory.getLogger("PhotoMaker");

    public String make(Command command) {
        //TODO do something
        log.info(command.photoType.toString());
        
        increaseNbPhotos(command);
        
        return "YOUR PHOTO IS READY";
    }

    private void increaseNbPhotos(Command command){
        PhotoType photoType = command.photoType;
        Integer nb = soldPhotos.containsKey(photoType) ? soldPhotos.get(photoType) : 0;
        soldPhotos.put(photoType, ++nb);        
    }


    public String printHowManyPhotosWhereSold() {
        String msg="";
        for(PhotoType photo: soldPhotos.keySet()){
            msg +=photo + ":" + soldPhotos.get(photo) + ";";
        }
        return msg;
    }    
    
    private Map<PhotoType, Integer> soldPhotos = new HashMap<PhotoType, Integer>();
}
