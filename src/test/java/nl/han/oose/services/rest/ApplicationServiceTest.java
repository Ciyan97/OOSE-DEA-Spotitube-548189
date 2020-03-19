package nl.han.oose.services.rest;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class ApplicationServiceTest {

    @Test
    public void getClassesTest() {
        ApplicationService applicationService = new ApplicationService();

        Set<Class<?>> set = applicationService.getClasses();

        Assert.assertFalse(set.isEmpty());
    }
}