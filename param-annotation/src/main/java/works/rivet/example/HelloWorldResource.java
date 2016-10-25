package works.rivet.example;

import com.codahale.metrics.annotation.Timed;
import works.rivet.example.param.MyQueryParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/greetings/")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    @GET
    @Timed
    @Path("sayHello")
    public HelloResponse sayHello(@QueryParam("q") String queryParam,
                                  @MyQueryParam("m") String myQueryParam) {
        return new HelloResponse(queryParam, myQueryParam);
    }
}
