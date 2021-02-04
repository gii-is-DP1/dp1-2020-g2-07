package org.springframework.samples.petclinic.model;

/**
 * Entry point for assertions of different data types. Each method in this class is a static factory for the
 * type-specific assertion objects.
 */
@javax.annotation.Generated(value="assertj-assertions-generator")
public class Assertions {

  /**
   * Creates a new instance of <code>{@link org.springframework.samples.petclinic.model.ClienteAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public static org.springframework.samples.petclinic.model.ClienteAssert assertThat(org.springframework.samples.petclinic.model.Cliente actual) {
    return new org.springframework.samples.petclinic.model.ClienteAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link org.springframework.samples.petclinic.model.EmployeeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public static org.springframework.samples.petclinic.model.EmployeeAssert assertThat(org.springframework.samples.petclinic.model.Employee actual) {
    return new org.springframework.samples.petclinic.model.EmployeeAssert(actual);
  }

  /**
   * Creates a new <code>{@link Assertions}</code>.
   */
  protected Assertions() {
    // empty
  }
}
