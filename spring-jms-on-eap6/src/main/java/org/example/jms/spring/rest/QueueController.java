package org.example.jms.spring.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.example.jms.spring.service.JmsQueueSender;

@RestController
@RequestMapping("/queues")
public class QueueController {

    JmsQueueSender sender;

    public QueueController(JmsQueueSender sender) {
        this.sender = sender;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessage(@RequestParam("message") String message) {
        sender.sendMessage(message);
    }
}
