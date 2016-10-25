package works.rivet.example;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import works.rivet.example.param.MyQueryParamBinder;


public class ParamAnnotationApplication extends Application<Configuration> {
    public static void main(String[] args)
            throws Exception {
        new ParamAnnotationApplication().run(args);
    }

    public void run(Configuration configuration, Environment environment)
            throws Exception {
        environment.jersey().register(new MyQueryParamBinder());
        environment.jersey().packages("works.rivet.example.param");

        environment.jersey().register(new HelloWorldResource());
    }
}
