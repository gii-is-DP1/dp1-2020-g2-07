package org.springframework.samples.petclinic.service;

/**
 * {@link EmailMockTest} specific assertions - Generated by CustomAssertionGenerator.
 *
 * Although this class is not final to allow Soft assertions proxy, if you wish to extend it, 
 * extend {@link AbstractEmailMockTestAssert} instead.
 */
@javax.annotation.Generated(value="assertj-assertions-generator")
public class EmailMockTestAssert extends AbstractEmailMockTestAssert<EmailMockTestAssert, EmailMockTest> {

  /**
   * Creates a new <code>{@link EmailMockTestAssert}</code> to make assertions on actual EmailMockTest.
   * @param actual the EmailMockTest we want to make assertions on.
   */
  public EmailMockTestAssert(EmailMockTest actual) {
    super(actual, EmailMockTestAssert.class);
  }

  /**
   * An entry point for EmailMockTestAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
   * With a static import, one can write directly: <code>assertThat(myEmailMockTest)</code> and get specific assertion with code completion.
   * @param actual the EmailMockTest we want to make assertions on.
   * @return a new <code>{@link EmailMockTestAssert}</code>
   */
  @org.assertj.core.util.CheckReturnValue
  public static EmailMockTestAssert assertThat(EmailMockTest actual) {
    return new EmailMockTestAssert(actual);
  }
}
