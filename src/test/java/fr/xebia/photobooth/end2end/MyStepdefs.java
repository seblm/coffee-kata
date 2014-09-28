package fr.xebia.photobooth.end2end;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import cucumber.api.PendingException;
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

    @After
    public void stopWebDriver() {
        phantomJsTest.getDriver().close();
    }

    @Before
    public void startTomcat() throws Throwable {
        tomcatRule.ifPresent(TomcatRule::before);
    }

    @After
    public void stopTomcat() {
        tomcatRule.ifPresent(rule -> {
            rule.after();
            try {
                while (!portIsNotBind(8080)) {
                    System.out.println("tomcat arrive pas à s'éteindre");
                    Thread.sleep(100);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Given("^I go to homepage$")
    public void I_go_to_homepage() {
        phantomJsTest.goTo("/");
        assertThat(phantomJsTest.title()).isEqualTo("XebiaMaton");
    }
    
    @Given("^I fill video url")
    public void I_fill_video_url() {
        phantomJsTest.await().atMost(5, TimeUnit.SECONDS).until("#urlVideo").areDisplayed();
    	phantomJsTest.fill("#urlVideo").with("http://webcam.hahd.fr/mjpg/video.mjpg?camera=1");
    	//phantomJsTest.fill("#urlPhoto").with("todo");
    }
    
    @Given("^I click on start Video")
    public void I_click_on_start_video() {
        phantomJsTest.click(".startIPWebcam");
    }
    @And("^video is displayed")
    public void video_is_displayed() {
        phantomJsTest.click(".startIPWebcam");
    	phantomJsTest.await().atMost(5, TimeUnit.SECONDS).until(".ipWebcamResult").with("src").contains("");
    }

    @When("^I click on \"([^\"]*)\" button$")
    public void I_click_on_button(String buttonCSSClass) throws Throwable {
    	phantomJsTest.takeScreenShot();
        phantomJsTest.click("." + buttonCSSClass);
    }

    @Then("^My picture should be displayed$")
    public void My_picture_should_be_displayed() {
        assertThat(phantomJsTest.find(".snapshotResult").getAttribute("src")).isNotEmpty();
    }

    private Optional<TomcatRule> tomcatRuleOrEmptyIfAlreadyStarted() throws IOException {
        if (portIsNotBind(8080)) {
            return Optional.of(new TomcatRule());
        } else {
            return Optional.empty();
        }
    }

    private boolean portIsNotBind(int port) throws IOException {
        try (ServerSocket ignored = new ServerSocket(port)) {
            return true;
        } catch (BindException e) {
            return false;
        }
    }

    @Given("^my picture is displayed$")
    public void my_picture_is_displayed() throws Throwable {
        I_go_to_homepage();
        I_fill_video_url();
        I_click_on_start_video();
        video_is_displayed();
        I_click_on_button("snapshot");

        My_picture_should_be_displayed();
    }

    @Given("^a user wanted a full color portrait of himself$")
    public void a_user_wanted_a_full_color_portrait_of_himself() throws Throwable {
        my_picture_is_displayed();
    }

    @When("^the user take the picture$")
    public void the_user_take_the_picture() throws Throwable {
        I_click_on_button("ok");
    }

    @Then("^the photomaton should print the full color portrait of the user$")
    public void the_photomaton_should_print_the_full_color_portrait_of_the_user() throws Throwable {
        Thread.sleep(5000);
        phantomJsTest.find(".link").getAttribute("href").startsWith("http://localhost:8080/image");
    }
}
