package com.uphf.kafka;

public interface IKafkaConstants {
	public static String KAFKA_BROKERS = "localhost:9092";
	
	public static Integer MESSAGE_COUNT=100;
	
	public static String CLIENT_ID="client1";
	
	public static String TOPIC_NAME="HT-topic";
	
	public static String GROUP_ID_CONFIG="consumerGroup10";
	
	public static Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
	
	public static String OFFSET_RESET_EARLIER="earliest";
	
	public static Integer MAX_POLL_RECORDS=1;

	// Constants for SSL connection
	public static String SECURITY_PROTOCOL="SSL";
	public static String SSL_TRUSTSTORE_LOCATION="secrets/truststore/kafka.truststore.jks";
	public static String SSL_TRUSTSTORE_PASSWORD="ilovebastub";
	public static String SSL_KEYSTORE_LOCATION="secrets/keystore/kafka.client.keystore.jks";
	public static String SSL_KEYSTORE_PASSWORD="ilovebastub";
	public static String SSL_KEY_PASSWORD="ilovebastub";
	public static String SSL_ENDPOINT_IDENTIFICATION_ALGORITHM="";
}
