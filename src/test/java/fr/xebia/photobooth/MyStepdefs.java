package fr.xebia.photobooth;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.xebia.tests.PhantomJsTest;
import fr.xebia.tests.TomcatRule;

import static org.assertj.core.api.Assertions.assertThat;

public class MyStepdefs {

    private final PhantomJsTest phantomJsTest;
    private final TomcatRule tomcatRule;

    public MyStepdefs() {
        phantomJsTest = new PhantomJsTest("http://localhost:8080");
        tomcatRule = new TomcatRule();
    }

    @Before
    public void startWebDriver() {
        phantomJsTest.starting();
    }

    @Before
    public void startTomcat() throws Throwable {
        tomcatRule.before();
    }

    @After
    public void stopTomcat() throws Throwable {
        tomcatRule.after();
    }

    @Given("^I go to homepage$")
    public void I_go_to_homepage() {
        phantomJsTest.goTo("/");
        assertThat(phantomJsTest.title()).isEqualTo("Photo Machine");
    }

    @When("^I click on snapshot button$")
    public void I_click_on_snapshot_button() {
        phantomJsTest.click(".snapshotButton");
    }

    @Then("^My picture should be displayed$")
    public void My_picture_should_be_displayed() {
        assertThat(phantomJsTest.find(".snapshotResult").getAttribute("src")).isNotEmpty();
    }
}
