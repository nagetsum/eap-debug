package sample.jms.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@Configuration
@EnableJms
public class JmsConfig {

    @Bean(name="jndiConnectionFactory")
    public JndiObjectFactoryBean jndiConnectionFactory() {
        JndiObjectFactoryBean connectionFactoryBean = new JndiObjectFactoryBean();
        connectionFactoryBean.setJndiName("java:/JmsXA"); // name=activemq-ra in standalone-full.xml
        return connectionFactoryBean;
    }

    @Bean(name="jndiQueue")
    public JndiObjectFactoryBean jndiQueue() {
        JndiObjectFactoryBean jndiQueueBean = new JndiObjectFactoryBean();
        jndiQueueBean.setJndiName("java:/jms/queue/HelloQueue");
        return jndiQueueBean;
    }

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(@Qualifier("jndiConnectionFactory") JndiObjectFactoryBean jndiConnectionFactoryBean,
                                                     @Qualifier("jndiQueue") JndiObjectFactoryBean jndiQueue) {
        JmsMessagingTemplate template = new JmsMessagingTemplate();
        template.setDefaultDestination((Queue) jndiQueue.getObject()); // set HelloQueue
        template.setConnectionFactory((ConnectionFactory) jndiConnectionFactoryBean.getObject());  // set activemq-ra ConnectionFactory
        return template;
    }
}
