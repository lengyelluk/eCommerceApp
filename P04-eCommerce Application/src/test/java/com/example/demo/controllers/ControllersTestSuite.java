package com.example.demo.controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CartControllerTest.class,
        LoginControllerTest.class,
        OrderControllerTest.class,
        UserControllerTest.class
})

public class ControllersTestSuite {
}
