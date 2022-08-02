package com.demo.service;

import com.demo.service.staticData.WelcomeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MockitoStaticMethodTest {
//    @Test
    public void testMockStaticMethods() {
        Assertions.assertEquals("Welcome John", WelcomeUtil.generateWelcome("John"));

        try (MockedStatic<WelcomeUtil> theMock = Mockito.mockStatic(WelcomeUtil.class)) {
            theMock.when(() -> WelcomeUtil.generateWelcome("John"))
                    .thenReturn("Guten Tag John");

            Assertions.assertEquals("Guten Tag John", WelcomeUtil.generateWelcome("John"));
        }

        Assertions.assertEquals("Welcome John", WelcomeUtil.generateWelcome("John"));
    }

}
