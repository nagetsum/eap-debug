package sample.jms.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sample.jms.service.JmsQueueSender;

@RestController
@RequestMapping("/queues")
public class QueueController {

    JmsQueueSender sender;

    public QueueController(JmsQueueSender sender) {
        this.sender = sender;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessage(@RequestParam("message") String message) {
        sender.sendMessage(message);
    }
}
