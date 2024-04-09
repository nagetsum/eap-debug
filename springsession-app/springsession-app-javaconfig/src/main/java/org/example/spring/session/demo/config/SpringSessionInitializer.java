package org.example.spring.session.demo.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class SpringSessionInitializer extends AbstractHttpSessionApplicationInitializer {
    public SpringSessionInitializer() {
        super(SpringSessionConfig.class);
    }
}
