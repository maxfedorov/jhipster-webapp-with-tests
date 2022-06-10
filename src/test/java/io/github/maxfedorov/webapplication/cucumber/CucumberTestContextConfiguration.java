package io.github.maxfedorov.webapplication.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import io.github.maxfedorov.webapplication.IntegrationTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@IntegrationTest
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
