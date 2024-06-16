package com.uphf.kafka;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;

public class BalancedProducer {

    static int nbPartitions = 30; //nb de paritions pour le topic 
    static String key = "my-key"; //clé par défaut
    static String topic = IKafkaConstants.TOPIC_NAME; //nom du topic
    static int partition = -1;  //indice de la partition (initialisé à -1 pour signifier aucune partition spécifique)
    static String message = String.valueOf(new Date()); //msg à envoyer initialisé à la date actuelle

    public static void main(String[] args) {

        for (String arg : args) { // On parcours tout les arguments et on configure les var
            if (arg.startsWith("--partition=")) {
                partition = Integer.parseInt(arg.substring(12));
            } else if (arg.startsWith("--topic=")) {
                topic = arg.substring(8);
            } else if (arg.startsWith("--key=")) {
                key = arg.substring(6);
            } else if (arg.startsWith("--help")) { //aide
                System.err.println("Utilisation : BalancedProducer [--partition=<partition>] [--topic=<topic>] [--help]");
                System.exit(1);
            }
        }
        
        // Création et affichage du producer
        Producer<String, String> producer = ProducerCreator.createProducer(IKafkaConstants.CLIENT_ID);
        System.out.println("Envoie des messages a :\n- topic: " + topic + "\n- partition: " + partition + "\n- key: " + key + "\n- message: " + message);

        // Envoie de 100 messages au topic Kafka
        for (int i = 0; i < 100; i++) {
            Integer part = partition == -1 ? i % nbPartitions : partition;
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, part, key, message);
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    System.err.println("Erreur lors de l'envoi du message : " + exception.getMessage());
                } else {
                    System.out.printf("Message envoyé avec succès à la partition %d%n", metadata.partition());
                }
            });
        }
        producer.close();
    }
}