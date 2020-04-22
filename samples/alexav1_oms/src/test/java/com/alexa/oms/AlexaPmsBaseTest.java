package com.alexa.oms;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:oms-test.properties")
@SpringBootTest
@Ignore
public class AlexaPmsBaseTest
{

}
