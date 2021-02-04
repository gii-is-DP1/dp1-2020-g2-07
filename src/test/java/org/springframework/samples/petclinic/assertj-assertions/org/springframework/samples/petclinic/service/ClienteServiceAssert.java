package org.springframework.samples.petclinic.service;

/**
 * {@link ClienteService} specific assertions - Generated by CustomAssertionGenerator.
 *
 * Although this class is not final to allow Soft assertions proxy, if you wish to extend it, 
 * extend {@link AbstractClienteServiceAssert} instead.
 */
@javax.annotation.Generated(value="assertj-assertions-generator")
public class ClienteServiceAssert extends AbstractClienteServiceAssert<ClienteServiceAssert, ClienteService> {

  /**
   * Creates a new <code>{@link ClienteServiceAssert}</code> to make assertions on actual ClienteService.
   * @param actual the ClienteService we want to make assertions on.
   */
  public ClienteServiceAssert(ClienteService actual) {
    super(actual, ClienteServiceAssert.class);
  }

  /**
   * An entry point for ClienteServiceAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
   * With a static import, one can write directly: <code>assertThat(myClienteService)</code> and get specific assertion with code completion.
   * @param actual the ClienteService we want to make assertions on.
   * @return a new <code>{@link ClienteServiceAssert}</code>
   */
  @org.assertj.core.util.CheckReturnValue
  public static ClienteServiceAssert assertThat(ClienteService actual) {
    return new ClienteServiceAssert(actual);
  }
}
