package org.github.anasoid.demo.docker.gradle;

import org.github.anasoid.demo.docker.gradle.ci.HelloService;
import org.junit.Assert;
import org.junit.Test;


public class HelloServiceTest {

  @Test
  public void testTrue() {
    HelloService service = new HelloService();

    boolean result = service.doGet(true);
    Assert.assertTrue(result);
  }


}
