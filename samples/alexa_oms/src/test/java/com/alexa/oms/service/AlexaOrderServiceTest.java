package com.alexa.oms.service;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alexa.oms.AlexaPmsBaseTest;

public class AlexaOrderServiceTest extends AlexaPmsBaseTest
{
    @Autowired(required = true)
    AlexaOrderService alexaOrderService;

    @Test
    @Ignore
    public void testGetOrderStatusString()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testGetTopOrders()
    {

        try
        {
            String response = alexaOrderService.getTopOrders(1, 2);
            System.out.println("response");
            
        }catch(Exception e)
        {
            e.printStackTrace();
            fail("exception occured");
        }
    }

    @Test
    @Ignore
    public void testGetBottomOrders()
    {
        fail("Not yet implemented");
    }

    @Test
    @Ignore
    public void testGetOrderDetailsString()
    {
        fail("Not yet implemented");
    }

}
