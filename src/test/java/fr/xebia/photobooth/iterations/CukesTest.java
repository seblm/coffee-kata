package fr.xebia.photobooth.iterations;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/fr/xebia/photobooth/iterations/iteration0",
                "src/test/resources/fr/xebia/photobooth/iterations/iteration1",
                "src/test/resources/fr/xebia/photobooth/iterations/iteration2",
                "src/test/resources/fr/xebia/photobooth/iterations/iteration3",
                "src/test/resources/fr/xebia/photobooth/iterations/iteration4",
                "src/test/resources/fr/xebia/photobooth/iterations/iteration5"
        }, monochrome = true, format = {"pretty", "html:target/cucumber", "rerun:target/rerun.txt"})
public class CukesTest {
}
