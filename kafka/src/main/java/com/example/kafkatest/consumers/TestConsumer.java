package com.example.kafkatest.consumers;

        import com.example.kafkatest.model.Customer;
        import com.example.kafkatest.producers.CustomerProducer;
        import com.example.kafkatest.repository.CustomerRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.kafka.annotation.KafkaListener;
        import org.springframework.kafka.core.KafkaTemplate;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public class TestConsumer {
//    @KafkaListener(topics = "allCustomersReplyTopic", groupId = "customer_group", containerFactory = "kafkaListenerContainerCustomerFactory")
//    public void handleTestAllCustomersReply(List<Customer> customerList) {
//        System.out.println("allCustomersReply test. Recievied data: ");
//        for (Customer customer: customerList) {
//            System.out.println(customer.getId());
//        }
//    }
}
