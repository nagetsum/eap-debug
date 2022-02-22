package org.example.springboot.kafka.kafkademo;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final KafkaTemplate<Object, Object> template;

    public Controller(KafkaTemplate<Object, Object> template) {
        this.template = template;
    }

    @PostMapping(path = "/send/topic1/{what}")
    public void sendFoo(@PathVariable String what) {
        this.template.send("topic1", what);
    }
}
