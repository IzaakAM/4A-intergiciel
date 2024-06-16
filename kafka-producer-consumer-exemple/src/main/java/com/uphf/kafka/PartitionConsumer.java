package com.uphf.kafka;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import java.time.Duration;
import java.util.Collections;
import java.util.Objects;

public class PartitionConsumer {

    static String key = "my-key"; //clé par défaut
    static String topic = IKafkaConstants.TOPIC_NAME; //nom du topic
    static int partition = -1; //indice de la partition (initialisé à -1 pour signifier aucune partition spécifique)
    static String groupId = IKafkaConstants.GROUP_ID_CONFIG; //identifiant du groupe

    public static void main(String[] args) {

        for (String arg : args) { // On parcours tout les arguments et on configure les var
            if (arg.startsWith("--partition=")) {
                partition = Integer.parseInt(arg.substring(12));
            } else if (arg.startsWith("--topic=")) {
                topic = arg.substring(8);
            } else if (arg.startsWith("--key=")) {
                key = arg.substring(6);
            } else if (arg.startsWith("--help")) { //aide
                System.err.println("Usage : PartitionConsumer [--partition=<partition>] [--topic=<topic>] [--group-id=<group-id>] [--help]");
                System.exit(1);
            }
        }

        // Création et d'un consumer
        Consumer<String, String> consumer = ConsumerCreator.createConsumer(groupId);

        // Si la partition est -1, on s'abonne au topic, sinon on assigne la partition
        if(partition == -1) {
            consumer.subscribe(Collections.singletonList(topic));
        } else {
            TopicPartition topicPartition = new TopicPartition(topic, partition);
            consumer.assign(Collections.singletonList(topicPartition));
        }

        // Affichage de la configuration
        System.out.println("Recois les messages de :\n- Topic : " + topic + "\n- Partition : " + partition + "\n- Key : " + key + "\n- Group ID : " + groupId);

        // Boucle infinie pour recevoir les messages
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                // Affichage des messages consommés
                System.out.printf("offset = %d, clé = %s, valeur = %s, partition = %d%n", record.offset(), record.key(), record.value(), record.partition());
            }
        }
    }
}