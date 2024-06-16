package com.uphf.kafka;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;


public class ConsumerCreator {

	public static Consumer<String, String> createConsumer(String groupId) {

		// Objet Properties pour sauvegarder la configuration du Consumer
		final Properties props = new Properties();

		// Configuration du Consumer (adresse, id, désérialiseurs, nombre de messages à récupérer)
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, IKafkaConstants.MAX_POLL_RECORDS);
		
		// Désactivation de l'auto-commit des offsets
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, IKafkaConstants.OFFSET_RESET_EARLIER);

		// On sécurise la connexion via SSL
		props.put("security.protocol", IKafkaConstants.SECURITY_PROTOCOL);
		props.put("ssl.truststore.location",IKafkaConstants.SSL_TRUSTSTORE_LOCATION);
		props.put("ssl.truststore.password",IKafkaConstants.SSL_TRUSTSTORE_PASSWORD);
		props.put("ssl.keystore.location",IKafkaConstants.SSL_KEYSTORE_LOCATION);
		props.put("ssl.keystore.password",IKafkaConstants.SSL_KEYSTORE_PASSWORD);
		props.put("ssl.key.password",IKafkaConstants.SSL_KEY_PASSWORD);
		props.put("ssl.endpoint.identification.algorithm",IKafkaConstants.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM);
		
		return new KafkaConsumer<>(props);
	}

}
