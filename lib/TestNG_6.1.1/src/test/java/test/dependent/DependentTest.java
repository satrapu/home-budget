package test.dependent;

import org.testng.annotations.ExpectedExceptions;
import org.testng.annotations.Test;

import test.BaseTest;

public class DependentTest extends BaseTest {

  @Test
  public void simpleSkip() {
    addClass(SampleDependent1.class.getName());
    run();
    String[] passed = {
    };
    String[] failed = {
      "fail"
    };
    String[] skipped = {
      "shouldBeSkipped"
    };
    verifyTests("Passed", passed, getPassedTests());
    verifyTests("Failed", failed, getFailedTests());
    verifyTests("Skipped", skipped, getSkippedTests());
  }

  @Test
  public void dependentMethods() {
    addClass(SampleDependentMethods.class.getName());
    run();
    String[] passed = {
        "oneA", "oneB", "secondA", "thirdA", "canBeRunAnytime"
    };
    String[] failed = {
    };
    String[] skipped = {
    };
    verifyTests("Passed", passed, getPassedTests());
    verifyTests("Failed", failed, getFailedTests());
    verifyTests("Skipped", skipped, getSkippedTests());
  }

  @Test
  public void dependentMethodsWithSkip() {
    addClass(SampleDependentMethods4.class.getName());
    run();
    String[] passed = {
        "step1",
    };
    String[] failed = {
        "step2",
    };
    String[] skipped = {
        "step3"
    };
    verifyTests("Passed", passed, getPassedTests());
    verifyTests("Failed", failed, getFailedTests());
    verifyTests("Skipped", skipped, getSkippedTests());
  }

  @Test
  @ExpectedExceptions({ org.testng.TestNGException.class })
  public void dependentMethodsWithNonExistentMethod() {
    addClass(SampleDependentMethods5.class.getName());
    run();
    String[] passed = {
        "step1", "step2"
    };
    String[] failed = {
    };
    String[] skipped = {
    };
    verifyTests("Passed", passed, getPassedTests());
    verifyTests("Failed", failed, getFailedTests());
    verifyTests("Skipped", skipped, getSkippedTests());
  }

  @Test(expectedExceptions = org.testng.TestNGException.class )
  public void dependentMethodsWithCycle() {
    addClass(SampleDependentMethods6.class.getName());
    run();
  }

  @Test(expectedExceptions = org.testng.TestNGException.class )
  public void dependentGroupsWithCycle() {
    addClass("test.dependent.SampleDependentMethods7");
    run();
  }

  @Test
  public void multipleSkips() {
    addClass(MultipleDependentSampleTest.class.getName());
    run();
    String[] passed = {
        "init",
    };
    String[] failed = {
        "fail",
    };
    String[] skipped = {
        "skip1", "skip2"
    };
    verifyTests("Passed", passed, getPassedTests());
    verifyTests("Failed", failed, getFailedTests());
    verifyTests("Skipped", skipped, getSkippedTests());
  }
} // DependentTest


