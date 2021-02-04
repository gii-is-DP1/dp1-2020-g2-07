package org.springframework.samples.petclinic.model;

/**
 * Entry point for soft assertions of different data types.
 */
@javax.annotation.Generated(value="assertj-assertions-generator")
public class SoftAssertions extends org.assertj.core.api.SoftAssertions {

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.model.ClienteAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.model.ClienteAssert assertThat(org.springframework.samples.petclinic.model.Cliente actual) {
    return proxy(org.springframework.samples.petclinic.model.ClienteAssert.class, org.springframework.samples.petclinic.model.Cliente.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.model.EmployeeAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.model.EmployeeAssert assertThat(org.springframework.samples.petclinic.model.Employee actual) {
    return proxy(org.springframework.samples.petclinic.model.EmployeeAssert.class, org.springframework.samples.petclinic.model.Employee.class, actual);
  }

}
