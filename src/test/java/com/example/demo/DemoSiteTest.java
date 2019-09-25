package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DemoSiteTest {

    @InjectMocks
    DemoSite demoSite;

    @Test
    public void visitReturnsListOfPages() {
        demoSite.visit("https://www.d-gray.co.uk");
    }
}