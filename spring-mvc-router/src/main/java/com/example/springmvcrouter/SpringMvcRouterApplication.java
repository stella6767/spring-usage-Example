package com.example.springmvcrouter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@SpringBootApplication
public class SpringMvcRouterApplication {


    private static final Logger LOG = LoggerFactory.getLogger(SpringMvcRouterApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcRouterApplication.class, args);
    }



    @Bean
    RouterFunction<ServerResponse> productListing(BookController bc) {
        return bc.bookListing();
    }

    @Bean
    RouterFunction<ServerResponse> allApplicationRoutes(BookController bc) {
        return RouterFunctions.route().add(bc.remainingProductRoutes())
                .before(req -> {
                    LOG.info("Found a route which matches " + req.uri()
                            .getPath());
                    return req;
                })
                .after((req, res) -> {
                    if (res.statusCode() == HttpStatus.OK) {
                        LOG.info("Finished processing request " + req.uri()
                                .getPath());
                    } else {
                        LOG.info("There was an error while processing request" + req.uri());
                    }
                    return res;
                })
                .onError(Throwable.class, (e, res) -> {
                    LOG.error("Fatal exception has occurred", e);
                    return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                })
                .build()
                .and(RouterFunctions.route(RequestPredicates.all(), req -> ServerResponse.notFound().build()));
    }

    public static class Error {
        private String errorMessage;

        public Error(String message) {
            this.errorMessage = message;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
