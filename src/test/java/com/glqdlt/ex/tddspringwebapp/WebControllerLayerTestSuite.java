package com.glqdlt.ex.tddspringwebapp;

import com.glqdlt.ex.tddspringwebapp.controller.SimpleRestControllerTest;
import com.glqdlt.ex.tddspringwebapp.controller.SimpleWebControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author glqdlt
 * @since 0.0.1
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({SimpleRestControllerTest.class, SimpleWebControllerTest.class})
public class WebControllerLayerTestSuite {
}
