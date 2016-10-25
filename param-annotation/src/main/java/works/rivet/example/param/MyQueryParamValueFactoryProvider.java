package works.rivet.example.param;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.internal.inject.ExtractorException;
import org.glassfish.jersey.server.ParamException;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.glassfish.jersey.server.internal.inject.AbstractValueFactoryProvider;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractor;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractorProvider;
import org.glassfish.jersey.server.internal.inject.ParamInjectionResolver;
import org.glassfish.jersey.server.model.Parameter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
final class MyQueryParamValueFactoryProvider extends AbstractValueFactoryProvider {

    @Singleton
    static final class InjectionResolver extends ParamInjectionResolver<MyQueryParam> {

        /**
         * Create new {@link MyQueryParam &#64;FlippedParam} injection resolver.
         */
        public InjectionResolver() {
            super(MyQueryParamValueFactoryProvider.class);
        }
    }

    private static final class MyQueryParamValueFactory extends AbstractContainerRequestValueFactory<Object> {

        private final MultivaluedParameterExtractor<?> extractor;
        private final boolean decode;

        MyQueryParamValueFactory(MultivaluedParameterExtractor<?> extractor, boolean decode) {
            this.extractor = extractor;
            this.decode = decode;
        }

        public Object provide() {
            try {
                return extractor.extract(getContainerRequest().getUriInfo().getQueryParameters(decode));
            } catch (ExtractorException e) {
                throw new ParamException.QueryParamException(e.getCause(),
                                                             extractor.getName(), extractor.getDefaultValueString());
            }
        }
    }

    /**
     * Injection constructor.
     *
     * @param mpep    multivalued map parameter extractor provider.
     * @param locator HK2 service locator.
     */
    @Inject
    public MyQueryParamValueFactoryProvider(MultivaluedParameterExtractorProvider mpep, ServiceLocator locator) {
        super(mpep, locator, Parameter.Source.QUERY);
    }

    @Override
    public AbstractContainerRequestValueFactory<?> createValueFactory(Parameter parameter) {
        String parameterName = parameter.getSourceName();
        if (parameterName == null || parameterName.length() == 0) {
            // Invalid query parameter name
            return null;
        }

        MultivaluedParameterExtractor e = get(parameter);
        if (e == null) {
            return null;
        }

        return new MyQueryParamValueFactory(e, !parameter.isEncoded());
    }}
