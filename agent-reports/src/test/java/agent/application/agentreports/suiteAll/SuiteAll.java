package agent.application.agentreports.suiteAll;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import agent.application.agentreports.controller.AgentReportsControllerIntegrationTest;
import agent.application.agentreports.service.AgentReportsServiceIntegrationTest;


@RunWith(Suite.class)
@SuiteClasses({ AgentReportsControllerIntegrationTest.class, AgentReportsServiceIntegrationTest.class})
public class SuiteAll{

}
