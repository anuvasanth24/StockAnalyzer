
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class Consumer {
    public void Consume() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "ec2-52-13-197-44.us-west-2.compute.amazonaws.com:9092");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group");
        KafkaConsumer consumer = new KafkaConsumer(props);
        consumer.subscribe(Arrays.asList("topic-stock"));
        System.out.printf("Consumer just subscribed to topic");

        while (true) {
            ConsumerRecords<String, String> recs = consumer.poll(10);
            //if (recs.count() == 0) {
               // System.out.printf("No records received");
            //} else {
                for (ConsumerRecord<String, String> rec : recs) {
                    System.out.printf("Recieved  %s", rec.value());
             //   }
            }

        }
    }

    public static void main(String[] args)
    {
        Consumer consumer = new Consumer();
        consumer.Consume();
    }
}

