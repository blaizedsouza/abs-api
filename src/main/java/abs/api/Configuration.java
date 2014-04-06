package abs.api;

/**
 * An configuration specifies different ingredients of an instance of
 * {@link abs.api.Context} to be created.
 *
 * <p>
 * Note that all of the specified classes to be used for the creation of
 * the context are expected to have a <i>default</i> constructor.
 *
 * @author Behrooz Nobakht
 * @since 1.0
 * @version $Id: $Id
 */
public interface Configuration {

	/**
	 * Provides the router of the context.
	 *
	 * @return the {@link abs.api.Router} of the context
	 */
	Router getRouter();

	/**
	 * Provides the type of the opener of the context
	 *
	 * @return the {@link java.lang.Class} of the {@link abs.api.Opener}
	 *         of the context
	 */
	Class<? extends Opener> getOpener();

	/**
	 * Provides the type of the inbox(es) of the context
	 *
	 * @return the {@link java.lang.Class} of the {@link abs.api.Inbox}
	 *         of the context
	 */
	Class<? extends Inbox> getInbox();

	/**
	 * Provides the type of notary of the context
	 *
	 * @return the {@link java.lang.Class} of the {@link abs.api.Notary}
	 *         of the context
	 */
	Class<? extends Notary> getNotary();

	/**
	 * Provides the reference factory of the context.
	 * 
	 * @return the {@link ReferenceFactory} of the context
	 */
	ReferenceFactory getReferenceFactory();

	/**
	 * Creates an instance of
	 * {@link abs.api.Configuration.ConfigurationBuilder} to build an
	 * instance of {@link abs.api.Configuration}.
	 *
	 * @return an instance of builder for a
	 *         {@link abs.api.Configuration}
	 */
	static ConfigurationBuilder newConfiguration() {
		return new ConfigurationBuilder();
	}

	/**
	 * A simple builder pattern for {@link Configuration}
	 */
	static class ConfigurationBuilder {

		private Router envelopeRouter = null;
		private Class<? extends Opener> envelopeOpenerClass = DefaultOpener.class;
		private Class<? extends Inbox> inboxClass = AsyncInbox.class;
		private Class<? extends Notary> notaryClass = LocalNotary.class;
		private ReferenceFactory referenceFactory = ReferenceFactory.DEFAULT;

		ConfigurationBuilder() {
		}

		public ConfigurationBuilder withEnvelopeRouter(Router router) {
			this.envelopeRouter = router;
			return this;
		}

		public ConfigurationBuilder withEnvelopeOpener(Class<? extends Opener> opener) {
			this.envelopeOpenerClass = opener;
			return this;
		}

		public ConfigurationBuilder withInbox(Class<? extends Inbox> inbox) {
			this.inboxClass = inbox;
			return this;
		}

		public ConfigurationBuilder withNotary(Class<? extends Notary> notary) {
			this.notaryClass = notary;
			return this;
		}

		public ConfigurationBuilder withReferenceFactory(ReferenceFactory referenceFactory) {
			this.referenceFactory = referenceFactory;
			return this;
		}

		public Configuration build() {
			return new SimpleConfiguration(envelopeRouter, envelopeOpenerClass, inboxClass,
					notaryClass, referenceFactory);
		}

		private static class SimpleConfiguration implements Configuration {

			private final Router envelopeRouter;
			private final Class<? extends Opener> envelopeOpenerClass;
			private final Class<? extends Inbox> inboxClass;
			private final Class<? extends Notary> notaryClass;
			private final ReferenceFactory referenceFactory;

			public SimpleConfiguration(Router envelopeRouter,
					Class<? extends Opener> envelopeOpenerClass,
					Class<? extends Inbox> inboxClass, Class<? extends Notary> notaryClass,
					ReferenceFactory referenceFactory) {
				this.envelopeRouter = envelopeRouter;
				this.envelopeOpenerClass = envelopeOpenerClass;
				this.inboxClass = inboxClass;
				this.notaryClass = notaryClass;
				this.referenceFactory = referenceFactory;
			}

			@Override
			public Router getRouter() {
				return this.envelopeRouter;
			}

			@Override
			public Class<? extends Opener> getOpener() {
				return this.envelopeOpenerClass;
			}

			@Override
			public Class<? extends Inbox> getInbox() {
				return this.inboxClass;
			}

			@Override
			public Class<? extends Notary> getNotary() {
				return this.notaryClass;
			}

			@Override
			public ReferenceFactory getReferenceFactory() {
				return referenceFactory;
			}

		}

	}

}
