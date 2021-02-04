package org.springframework.samples.petclinic.service;

/**
 * Like {@link SoftAssertions} but as a junit rule that takes care of calling
 * {@link SoftAssertions#assertAll() assertAll()} at the end of each test.
 * <p>
 * Example:
 * <pre><code class='java'> public class SoftlyTest {
 *
 *     &#064;Rule
 *     public final JUnitBDDSoftAssertions softly = new JUnitBDDSoftAssertions();
 *
 *     &#064;Test
 *     public void soft_bdd_assertions() throws Exception {
 *       softly.assertThat(1).isEqualTo(2);
 *       softly.assertThat(Lists.newArrayList(1, 2)).containsOnly(1, 2);
 *       // no need to call assertAll(), this is done automatically.
 *     }
 *  }</code></pre>
 */
@javax.annotation.Generated(value="assertj-assertions-generator")
public class JUnitSoftAssertions extends org.assertj.core.api.JUnitSoftAssertions {

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.AdminMockTestAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.AdminMockTestAssert assertThat(org.springframework.samples.petclinic.service.AdminMockTest actual) {
    return proxy(org.springframework.samples.petclinic.service.AdminMockTestAssert.class, org.springframework.samples.petclinic.service.AdminMockTest.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.AdminServiceAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.AdminServiceAssert assertThat(org.springframework.samples.petclinic.service.AdminService actual) {
    return proxy(org.springframework.samples.petclinic.service.AdminServiceAssert.class, org.springframework.samples.petclinic.service.AdminService.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.AuthoritiesServiceAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.AuthoritiesServiceAssert assertThat(org.springframework.samples.petclinic.service.AuthoritiesService actual) {
    return proxy(org.springframework.samples.petclinic.service.AuthoritiesServiceAssert.class, org.springframework.samples.petclinic.service.AuthoritiesService.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.BalanceMockTestAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.BalanceMockTestAssert assertThat(org.springframework.samples.petclinic.service.BalanceMockTest actual) {
    return proxy(org.springframework.samples.petclinic.service.BalanceMockTestAssert.class, org.springframework.samples.petclinic.service.BalanceMockTest.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.BalanceServiceAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.BalanceServiceAssert assertThat(org.springframework.samples.petclinic.service.BalanceService actual) {
    return proxy(org.springframework.samples.petclinic.service.BalanceServiceAssert.class, org.springframework.samples.petclinic.service.BalanceService.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.BonoServiceAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.BonoServiceAssert assertThat(org.springframework.samples.petclinic.service.BonoService actual) {
    return proxy(org.springframework.samples.petclinic.service.BonoServiceAssert.class, org.springframework.samples.petclinic.service.BonoService.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.CircuitMockTestAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.CircuitMockTestAssert assertThat(org.springframework.samples.petclinic.service.CircuitMockTest actual) {
    return proxy(org.springframework.samples.petclinic.service.CircuitMockTestAssert.class, org.springframework.samples.petclinic.service.CircuitMockTest.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.CircuitoServiceAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.CircuitoServiceAssert assertThat(org.springframework.samples.petclinic.service.CircuitoService actual) {
    return proxy(org.springframework.samples.petclinic.service.CircuitoServiceAssert.class, org.springframework.samples.petclinic.service.CircuitoService.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.CitaMockedServiceTestsAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.CitaMockedServiceTestsAssert assertThat(org.springframework.samples.petclinic.service.CitaMockedServiceTests actual) {
    return proxy(org.springframework.samples.petclinic.service.CitaMockedServiceTestsAssert.class, org.springframework.samples.petclinic.service.CitaMockedServiceTests.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.CitaServiceAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.CitaServiceAssert assertThat(org.springframework.samples.petclinic.service.CitaService actual) {
    return proxy(org.springframework.samples.petclinic.service.CitaServiceAssert.class, org.springframework.samples.petclinic.service.CitaService.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.ClientMockTestAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.ClientMockTestAssert assertThat(org.springframework.samples.petclinic.service.ClientMockTest actual) {
    return proxy(org.springframework.samples.petclinic.service.ClientMockTestAssert.class, org.springframework.samples.petclinic.service.ClientMockTest.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.ClienteServiceAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.ClienteServiceAssert assertThat(org.springframework.samples.petclinic.service.ClienteService actual) {
    return proxy(org.springframework.samples.petclinic.service.ClienteServiceAssert.class, org.springframework.samples.petclinic.service.ClienteService.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.EmailMockTestAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.EmailMockTestAssert assertThat(org.springframework.samples.petclinic.service.EmailMockTest actual) {
    return proxy(org.springframework.samples.petclinic.service.EmailMockTestAssert.class, org.springframework.samples.petclinic.service.EmailMockTest.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.EmailServiceAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.EmailServiceAssert assertThat(org.springframework.samples.petclinic.service.EmailService actual) {
    return proxy(org.springframework.samples.petclinic.service.EmailServiceAssert.class, org.springframework.samples.petclinic.service.EmailService.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.EmployeeMockTestAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.EmployeeMockTestAssert assertThat(org.springframework.samples.petclinic.service.EmployeeMockTest actual) {
    return proxy(org.springframework.samples.petclinic.service.EmployeeMockTestAssert.class, org.springframework.samples.petclinic.service.EmployeeMockTest.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.EmployeeServiceAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.EmployeeServiceAssert assertThat(org.springframework.samples.petclinic.service.EmployeeService actual) {
    return proxy(org.springframework.samples.petclinic.service.EmployeeServiceAssert.class, org.springframework.samples.petclinic.service.EmployeeService.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.HorarioMockedServiceTestsAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.HorarioMockedServiceTestsAssert assertThat(org.springframework.samples.petclinic.service.HorarioMockedServiceTests actual) {
    return proxy(org.springframework.samples.petclinic.service.HorarioMockedServiceTestsAssert.class, org.springframework.samples.petclinic.service.HorarioMockedServiceTests.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.HorarioServiceAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.HorarioServiceAssert assertThat(org.springframework.samples.petclinic.service.HorarioService actual) {
    return proxy(org.springframework.samples.petclinic.service.HorarioServiceAssert.class, org.springframework.samples.petclinic.service.HorarioService.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.RoomMockTestAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.RoomMockTestAssert assertThat(org.springframework.samples.petclinic.service.RoomMockTest actual) {
    return proxy(org.springframework.samples.petclinic.service.RoomMockTestAssert.class, org.springframework.samples.petclinic.service.RoomMockTest.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.SalaServiceAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.SalaServiceAssert assertThat(org.springframework.samples.petclinic.service.SalaService actual) {
    return proxy(org.springframework.samples.petclinic.service.SalaServiceAssert.class, org.springframework.samples.petclinic.service.SalaService.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.TokenMockTestAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.TokenMockTestAssert assertThat(org.springframework.samples.petclinic.service.TokenMockTest actual) {
    return proxy(org.springframework.samples.petclinic.service.TokenMockTestAssert.class, org.springframework.samples.petclinic.service.TokenMockTest.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.UserMockTestAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.UserMockTestAssert assertThat(org.springframework.samples.petclinic.service.UserMockTest actual) {
    return proxy(org.springframework.samples.petclinic.service.UserMockTestAssert.class, org.springframework.samples.petclinic.service.UserMockTest.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.UserServiceAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.UserServiceAssert assertThat(org.springframework.samples.petclinic.service.UserService actual) {
    return proxy(org.springframework.samples.petclinic.service.UserServiceAssert.class, org.springframework.samples.petclinic.service.UserService.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.exceptions.DuplicatedCircuitoNameExceptionAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.exceptions.DuplicatedCircuitoNameExceptionAssert assertThat(org.springframework.samples.petclinic.service.exceptions.DuplicatedCircuitoNameException actual) {
    return proxy(org.springframework.samples.petclinic.service.exceptions.DuplicatedCircuitoNameExceptionAssert.class, org.springframework.samples.petclinic.service.exceptions.DuplicatedCircuitoNameException.class, actual);
  }

  /**
   * Creates a new "soft" instance of <code>{@link org.springframework.samples.petclinic.service.exceptions.DuplicatedSalaNameExceptionAssert}</code>.
   *
   * @param actual the actual value.
   * @return the created "soft" assertion object.
   */
  @org.assertj.core.util.CheckReturnValue
  public org.springframework.samples.petclinic.service.exceptions.DuplicatedSalaNameExceptionAssert assertThat(org.springframework.samples.petclinic.service.exceptions.DuplicatedSalaNameException actual) {
    return proxy(org.springframework.samples.petclinic.service.exceptions.DuplicatedSalaNameExceptionAssert.class, org.springframework.samples.petclinic.service.exceptions.DuplicatedSalaNameException.class, actual);
  }

}
