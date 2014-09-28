package fr.xebia.photobooth.api;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import fr.xebia.tests.TomcatRule;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.endsWith;

public class PhotoResourceIT {	
	@ClassRule
	public static TomcatRule tomcatRule = new TomcatRule();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void should_download_picture_and_create_file() {
		given().body("http://camera1.mairie-brest.fr/axis-cgi/jpg/image.cgi")
				.contentType(JSON).log().all().
		when().post("/rest/photos/saveWithURL").
		then().statusCode(200).
				log().all().
				body(startsWith("image")).and().body(endsWith("png"));
	}
}
