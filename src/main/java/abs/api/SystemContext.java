package abs.api;

/**
 * A system context is an entry-point to an actor context. The default
 * constructor creates a context with default configuration.
 *
 * @author Behrooz Nobakht
 * @since 1.0
 */
public class SystemContext implements Context {

	/** Constant <code>context</code> */
	public static final Context context;

	static {
		context = new LocalContext();
	}

	/**
	 * <p>
	 * Constructor for SystemContext.
	 * </p>
	 */
	public SystemContext() {
	}

	/** {@inheritDoc} */
	@Override
	public Actor newActor(String name, Object object) {
		return context.newActor(name, object);
	}

	/** {@inheritDoc} */
	@Override
	public Router router() {
		return context.router();
	}

	/** {@inheritDoc} */
	@Override
	public Notary notary() {
		return context.notary();
	}

	/** {@inheritDoc} */
	@Override
	public Inbox inbox(Reference reference) {
		return context.inbox(reference);
	}

	/** {@inheritDoc} */
	@Override
	public Opener opener(Reference reference) {
		return context.opener(reference);
	}

	/** {@inheritDoc} */
	@Override
	public void stop() throws Exception {
		context.stop();
	}

}
