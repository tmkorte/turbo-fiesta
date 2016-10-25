package works.rivet.example.param;

import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;

import javax.inject.Singleton;
import javax.ws.rs.QueryParam;


public class MyQueryParamBinder extends AbstractBinder {
    protected void configure() {
        bind(MyQueryParamValueFactoryProvider.class)
                .to(ValueFactoryProvider.class)
                .in(Singleton.class);


        bind(MyQueryParamValueFactoryProvider.InjectionResolver.class)
                .to(new TypeLiteral<InjectionResolver<QueryParam>>() {
                })
                .in(Singleton.class);
    }
}
