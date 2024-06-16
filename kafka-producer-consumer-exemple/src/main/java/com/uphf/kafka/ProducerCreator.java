package com.uphf.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;


public class ProducerCreator {

	public static Producer<String, String> createProducer(String clientId) {

		// Objet Properties pour sauvegarder la configuration du Consumer
		Properties props = new Properties();

		//Configuration du Producer (adresse, id, sérialiseurs)
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		// On sécurise la connexion via SSL
		props.put("security.protocol", IKafkaConstants.SECURITY_PROTOCOL);
		props.put("ssl.truststore.location",IKafkaConstants.SSL_TRUSTSTORE_LOCATION);
		props.put("ssl.truststore.password",IKafkaConstants.SSL_TRUSTSTORE_PASSWORD);
		props.put("ssl.keystore.location",IKafkaConstants.SSL_KEYSTORE_LOCATION);
		props.put("ssl.keystore.password",IKafkaConstants.SSL_KEYSTORE_PASSWORD);
		props.put("ssl.key.password",IKafkaConstants.SSL_KEY_PASSWORD);
		props.put("ssl.endpoint.identification.algorithm",IKafkaConstants.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM);
		
		return new KafkaProducer<>(props);
	}
}