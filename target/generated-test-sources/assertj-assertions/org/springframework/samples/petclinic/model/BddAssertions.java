package org.springframework.samples.petclinic.model;

/**
 * Entry point for BDD assertions of different data types.
 */
@javax.annotation.Generated(value="assertj-assertions-generator")
public class BddAssertions {

  /**
   * Creates a new instance of <code>{@link org.springframework.samples.petclinic.model.ClienteAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public static org.springframework.samples.petclinic.model.ClienteAssert then(org.springframework.samples.petclinic.model.Cliente actual) {
    return new org.springframework.samples.petclinic.model.ClienteAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link org.springframework.samples.petclinic.model.EmployeeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public static org.springframework.samples.petclinic.model.EmployeeAssert then(org.springframework.samples.petclinic.model.Employee actual) {
    return new org.springframework.samples.petclinic.model.EmployeeAssert(actual);
  }

  /**
   * Creates a new <code>{@link BddAssertions}</code>.
   */
  protected BddAssertions() {
    // empty
  }
}
