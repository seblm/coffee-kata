package fr.xebia.photobooth.domain;

import java.math.BigDecimal;

public class Photo{

	public enum PhotoType {
	    IDENTITY_COLOR, IDENTITY_NB, PORTRAIT_COLOR, PORTRAIT_NB, FUN_COLOR, FUN_NB ;
	}


	PhotoType photoType;
    final BigDecimal cost;

	Photo(PhotoType photoType, double cost ){
		this.photoType = photoType;
        this.cost = new BigDecimal(cost);

	}
}
