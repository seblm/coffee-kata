package fr.xebia.photobooth.api;

import fr.xebia.photobooth.domain.Order;
import fr.xebia.tests.TomcatRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class PhotoResourceIT {	
	@ClassRule
	public static TomcatRule tomcatRule = new TomcatRule();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void should_download_picture_and_create_file() {
		given().port(tomcatRule.port())
                .body("http://camera1.mairie-brest.fr/axis-cgi/jpg/image.cgi")
				.contentType(JSON).log().all().
		when().post("/rest/photos/saveWithURL").
		then().statusCode(200).
				log().all().
				body(startsWith("image")).and().body(endsWith("png"));
	}

    @Test
    public void should_return_true_if_valid_order() {
        Order order = new Order("COLOR", "PORTRAIT", "0.0");

        given().port(tomcatRule.port())
               .body(order)
               .contentType(JSON).log().all().
        when().post("/rest/photos/check").
        then()
              .statusCode(200).log().all()

              .body(equalTo("true"));
    }

}
