package com.validate.main;


import static org.junit.Assert.assertEquals;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author guofei
 * @date 2022/8/4 2:05 PM
 */
public class MainTest {

  private static Validator validator;

  @BeforeClass
  public static void setUpValidator() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void manufacturerIsNull() {
    Car car = new Car( null, "DD-AB-123", 4 );

    Set<ConstraintViolation<Car>> constraintViolations = validator.validate( car );

    assertEquals( 1, constraintViolations.size() );
    assertEquals( "must not be null", constraintViolations.iterator().next().getMessage() );
  }

  @Test
  public void testObjectCar(){
    TestService testService = new TestService();
    Car car = new Car( null, "DD-AB-123", 4 );
    testService.getCar(car);
  }
}
