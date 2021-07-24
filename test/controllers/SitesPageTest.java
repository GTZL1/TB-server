package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class SitesPageTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testIndexPage() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testRules() {
        Http.RequestBuilder request = new Http.RequestBuilder()
            .method(GET)
            .uri("/rules");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testNewAccountPage() {
        Http.RequestBuilder request = new Http.RequestBuilder()
            .method(GET)
            .uri("/newAccount");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
}
