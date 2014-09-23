package fr.xebia.photobooth;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.xebia.tests.PhantomJsTest;
import fr.xebia.tests.TomcatRule;

import static org.assertj.core.api.Assertions.assertThat;

public class MyStepdefs {

    private final PhantomJsTest phantomJsTest;
    private Optional<TomcatRule> tomcatRule;
    
    public MyStepdefs() throws IOException {
        phantomJsTest = new PhantomJsTest("http://localhost:8080");
        tomcatRule = tomcatRuleOrEmptyIfAlreadyStarted();
    }

    @Before
    public void startWebDriver() {
        phantomJsTest.starting();
    }

    @Before
    public void startTomcat() throws Throwable {
        tomcatRule.ifPresent(TomcatRule::before);
    }

    @After
    public void stopTomcat() {
        tomcatRule.ifPresent(TomcatRule::after);
    }

    @Given("^I go to homepage$")
    public void I_go_to_homepage() {
        phantomJsTest.goTo("/");
        assertThat(phantomJsTest.title()).isEqualTo("XebiaMaton");
    }
    
    @Given("^I fill video url")
    public void I_fill_video_url() {
    	phantomJsTest.fill("#urlVideo").with("http://webcam.hahd.fr/mjpg/video.mjpg?camera=1");
    	//phantomJsTest.fill("#urlPhoto").with("todo");    	
    }    
    
    @Given("^I click on start Video")
    public void I_click_on_start_video() {
    	phantomJsTest.click(".startIPWebcam");    	
    }
    @And("^video is displayed")
    public void video_is_dosplayed() {
    	phantomJsTest.click(".startIPWebcam");
    	phantomJsTest.await().atMost(5, TimeUnit.SECONDS).until(".ipWebcamResult").with("src").contains("");
    }

    @When("^I click on snapshot button$")
    public void I_click_on_snapshot_button() {
    	phantomJsTest.takeScreenShot();
        phantomJsTest.click(".snapshotButton");
    }

    @Then("^My picture should be displayed$")
    public void My_picture_should_be_displayed() {
        assertThat(phantomJsTest.find(".snapshotResult").getAttribute("src")).isNotEmpty();
    }
    private Optional<TomcatRule> tomcatRuleOrEmptyIfAlreadyStarted() throws IOException {
        try (ServerSocket ignored = new ServerSocket(8080)) {
            return Optional.of(new TomcatRule());
        } catch (BindException e) {
            return Optional.<TomcatRule>empty();
        }
    }
}
