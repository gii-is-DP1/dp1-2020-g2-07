package org.springframework.samples.petclinic.model;

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.internal.Iterables;
import org.assertj.core.util.Objects;

/**
 * Abstract base class for {@link Employee} specific assertions - Generated by CustomAssertionGenerator.
 */
@javax.annotation.Generated(value="assertj-assertions-generator")
public abstract class AbstractEmployeeAssert<S extends AbstractEmployeeAssert<S, A>, A extends Employee> extends AbstractObjectAssert<S, A> {

  /**
   * Creates a new <code>{@link AbstractEmployeeAssert}</code> to make assertions on actual Employee.
   * @param actual the Employee we want to make assertions on.
   */
  protected AbstractEmployeeAssert(A actual, Class<S> selfType) {
    super(actual, selfType);
  }

  /**
   * Verifies that the actual Employee's horarios contains the given Horario elements.
   * @param horarios the given elements that should be contained in actual Employee's horarios.
   * @return this assertion object.
   * @throws AssertionError if the actual Employee's horarios does not contain all given Horario elements.
   */
  public S hasHorarios(Horario... horarios) {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // check that given Horario varargs is not null.
    if (horarios == null) failWithMessage("Expecting horarios parameter not to be null.");

    // check with standard error message, to set another message call: info.overridingErrorMessage("my error message");
    Iterables.instance().assertContains(info, actual.getHorarios(), horarios);

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual Employee's horarios contains the given Horario elements in Collection.
   * @param horarios the given elements that should be contained in actual Employee's horarios.
   * @return this assertion object.
   * @throws AssertionError if the actual Employee's horarios does not contain all given Horario elements.
   */
  public S hasHorarios(java.util.Collection<? extends Horario> horarios) {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // check that given Horario collection is not null.
    if (horarios == null) {
      failWithMessage("Expecting horarios parameter not to be null.");
      return myself; // to fool Eclipse "Null pointer access" warning on toArray.
    }

    // check with standard error message, to set another message call: info.overridingErrorMessage("my error message");
    Iterables.instance().assertContains(info, actual.getHorarios(), horarios.toArray());

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual Employee's horarios contains <b>only</b> the given Horario elements and nothing else in whatever order.
   * @param horarios the given elements that should be contained in actual Employee's horarios.
   * @return this assertion object.
   * @throws AssertionError if the actual Employee's horarios does not contain all given Horario elements.
   */
  public S hasOnlyHorarios(Horario... horarios) {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // check that given Horario varargs is not null.
    if (horarios == null) failWithMessage("Expecting horarios parameter not to be null.");

    // check with standard error message, to set another message call: info.overridingErrorMessage("my error message");
    Iterables.instance().assertContainsOnly(info, actual.getHorarios(), horarios);

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual Employee's horarios contains <b>only</b> the given Horario elements in Collection and nothing else in whatever order.
   * @param horarios the given elements that should be contained in actual Employee's horarios.
   * @return this assertion object.
   * @throws AssertionError if the actual Employee's horarios does not contain all given Horario elements.
   */
  public S hasOnlyHorarios(java.util.Collection<? extends Horario> horarios) {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // check that given Horario collection is not null.
    if (horarios == null) {
      failWithMessage("Expecting horarios parameter not to be null.");
      return myself; // to fool Eclipse "Null pointer access" warning on toArray.
    }

    // check with standard error message, to set another message call: info.overridingErrorMessage("my error message");
    Iterables.instance().assertContainsOnly(info, actual.getHorarios(), horarios.toArray());

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual Employee's horarios does not contain the given Horario elements.
   *
   * @param horarios the given elements that should not be in actual Employee's horarios.
   * @return this assertion object.
   * @throws AssertionError if the actual Employee's horarios contains any given Horario elements.
   */
  public S doesNotHaveHorarios(Horario... horarios) {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // check that given Horario varargs is not null.
    if (horarios == null) failWithMessage("Expecting horarios parameter not to be null.");

    // check with standard error message (use overridingErrorMessage before contains to set your own message).
    Iterables.instance().assertDoesNotContain(info, actual.getHorarios(), horarios);

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual Employee's horarios does not contain the given Horario elements in Collection.
   *
   * @param horarios the given elements that should not be in actual Employee's horarios.
   * @return this assertion object.
   * @throws AssertionError if the actual Employee's horarios contains any given Horario elements.
   */
  public S doesNotHaveHorarios(java.util.Collection<? extends Horario> horarios) {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // check that given Horario collection is not null.
    if (horarios == null) {
      failWithMessage("Expecting horarios parameter not to be null.");
      return myself; // to fool Eclipse "Null pointer access" warning on toArray.
    }

    // check with standard error message (use overridingErrorMessage before contains to set your own message).
    Iterables.instance().assertDoesNotContain(info, actual.getHorarios(), horarios.toArray());

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual Employee has no horarios.
   * @return this assertion object.
   * @throws AssertionError if the actual Employee's horarios is not empty.
   */
  public S hasNoHorarios() {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // we override the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting :\n  <%s>\nnot to have horarios but had :\n  <%s>";

    // check
    if (actual.getHorarios().iterator().hasNext()) {
      failWithMessage(assertjErrorMessage, actual, actual.getHorarios());
    }

    // return the current assertion for method chaining
    return myself;
  }


  /**
   * Verifies that the actual Employee's profession is equal to the given one.
   * @param profession the given profession to compare the actual Employee's profession to.
   * @return this assertion object.
   * @throws AssertionError - if the actual Employee's profession is not equal to the given one.
   */
  public S hasProfession(Profession profession) {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting profession of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";

    // null safe check
    Profession actualProfession = actual.getProfession();
    if (!Objects.areEqual(actualProfession, profession)) {
      failWithMessage(assertjErrorMessage, actual, profession, actualProfession);
    }

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual Employee's salaries contains the given EmployeeRevenue elements.
   * @param salaries the given elements that should be contained in actual Employee's salaries.
   * @return this assertion object.
   * @throws AssertionError if the actual Employee's salaries does not contain all given EmployeeRevenue elements.
   */
  public S hasSalaries(EmployeeRevenue... salaries) {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // check that given EmployeeRevenue varargs is not null.
    if (salaries == null) failWithMessage("Expecting salaries parameter not to be null.");

    // check with standard error message, to set another message call: info.overridingErrorMessage("my error message");
    Iterables.instance().assertContains(info, actual.getSalaries(), salaries);

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual Employee's salaries contains the given EmployeeRevenue elements in Collection.
   * @param salaries the given elements that should be contained in actual Employee's salaries.
   * @return this assertion object.
   * @throws AssertionError if the actual Employee's salaries does not contain all given EmployeeRevenue elements.
   */
  public S hasSalaries(java.util.Collection<? extends EmployeeRevenue> salaries) {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // check that given EmployeeRevenue collection is not null.
    if (salaries == null) {
      failWithMessage("Expecting salaries parameter not to be null.");
      return myself; // to fool Eclipse "Null pointer access" warning on toArray.
    }

    // check with standard error message, to set another message call: info.overridingErrorMessage("my error message");
    Iterables.instance().assertContains(info, actual.getSalaries(), salaries.toArray());

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual Employee's salaries contains <b>only</b> the given EmployeeRevenue elements and nothing else in whatever order.
   * @param salaries the given elements that should be contained in actual Employee's salaries.
   * @return this assertion object.
   * @throws AssertionError if the actual Employee's salaries does not contain all given EmployeeRevenue elements.
   */
  public S hasOnlySalaries(EmployeeRevenue... salaries) {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // check that given EmployeeRevenue varargs is not null.
    if (salaries == null) failWithMessage("Expecting salaries parameter not to be null.");

    // check with standard error message, to set another message call: info.overridingErrorMessage("my error message");
    Iterables.instance().assertContainsOnly(info, actual.getSalaries(), salaries);

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual Employee's salaries contains <b>only</b> the given EmployeeRevenue elements in Collection and nothing else in whatever order.
   * @param salaries the given elements that should be contained in actual Employee's salaries.
   * @return this assertion object.
   * @throws AssertionError if the actual Employee's salaries does not contain all given EmployeeRevenue elements.
   */
  public S hasOnlySalaries(java.util.Collection<? extends EmployeeRevenue> salaries) {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // check that given EmployeeRevenue collection is not null.
    if (salaries == null) {
      failWithMessage("Expecting salaries parameter not to be null.");
      return myself; // to fool Eclipse "Null pointer access" warning on toArray.
    }

    // check with standard error message, to set another message call: info.overridingErrorMessage("my error message");
    Iterables.instance().assertContainsOnly(info, actual.getSalaries(), salaries.toArray());

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual Employee's salaries does not contain the given EmployeeRevenue elements.
   *
   * @param salaries the given elements that should not be in actual Employee's salaries.
   * @return this assertion object.
   * @throws AssertionError if the actual Employee's salaries contains any given EmployeeRevenue elements.
   */
  public S doesNotHaveSalaries(EmployeeRevenue... salaries) {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // check that given EmployeeRevenue varargs is not null.
    if (salaries == null) failWithMessage("Expecting salaries parameter not to be null.");

    // check with standard error message (use overridingErrorMessage before contains to set your own message).
    Iterables.instance().assertDoesNotContain(info, actual.getSalaries(), salaries);

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual Employee's salaries does not contain the given EmployeeRevenue elements in Collection.
   *
   * @param salaries the given elements that should not be in actual Employee's salaries.
   * @return this assertion object.
   * @throws AssertionError if the actual Employee's salaries contains any given EmployeeRevenue elements.
   */
  public S doesNotHaveSalaries(java.util.Collection<? extends EmployeeRevenue> salaries) {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // check that given EmployeeRevenue collection is not null.
    if (salaries == null) {
      failWithMessage("Expecting salaries parameter not to be null.");
      return myself; // to fool Eclipse "Null pointer access" warning on toArray.
    }

    // check with standard error message (use overridingErrorMessage before contains to set your own message).
    Iterables.instance().assertDoesNotContain(info, actual.getSalaries(), salaries.toArray());

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual Employee has no salaries.
   * @return this assertion object.
   * @throws AssertionError if the actual Employee's salaries is not empty.
   */
  public S hasNoSalaries() {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // we override the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting :\n  <%s>\nnot to have salaries but had :\n  <%s>";

    // check
    if (actual.getSalaries().iterator().hasNext()) {
      failWithMessage(assertjErrorMessage, actual, actual.getSalaries());
    }

    // return the current assertion for method chaining
    return myself;
  }


  /**
   * Verifies that the actual Employee's salary is equal to the given one.
   * @param salary the given salary to compare the actual Employee's salary to.
   * @return this assertion object.
   * @throws AssertionError - if the actual Employee's salary is not equal to the given one.
   */
  public S hasSalary(Integer salary) {
    // check that actual Employee we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting salary of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";

    // null safe check
    Integer actualSalary = actual.getSalary();
    if (!Objects.areEqual(actualSalary, salary)) {
      failWithMessage(assertjErrorMessage, actual, salary, actualSalary);
    }

    // return the current assertion for method chaining
    return myself;
  }

}