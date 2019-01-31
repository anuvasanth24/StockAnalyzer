import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;
import java.io.File;

public class Producer {
    public void Send () throws java.lang.InterruptedException {

        String fileName = "/Users/anupama/IdeaProjects/Producer/src/main/java/stock.csv";
        Properties props = new Properties();
        File file = new File(fileName);

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "ec2-52-13-197-44.us-west-2.compute.amazonaws.com:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);


        try {

            Scanner inputStream = new Scanner(file);

            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                ProducerRecord<String, String> data;

                data = new ProducerRecord<String, String>("topic-stock", line);

                producer.send(data);
                Thread.sleep(1L);
            }

            inputStream.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        producer.flush();
        producer.close();
    }

    public static void main(String[] args){

        Producer producer = new Producer();
        try {
            producer.Send();
        }
        catch(InterruptedException ie){}

    }
}

