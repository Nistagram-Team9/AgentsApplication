package agent.application.shoppingservice.suiteAll;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import agent.application.shoppingservice.controller.ShoppingOrderControllerIntegrationTest;
import agent.application.shoppingservice.service.ShoppingOrderServiceIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ ShoppingOrderControllerIntegrationTest.class, ShoppingOrderServiceIntegrationTest.class})
public class SuiteAll{

}
