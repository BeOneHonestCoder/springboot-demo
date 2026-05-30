package com.net.config;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.security.principal}")
    private String clientPrincipal;

    @Value("${kafka.security.keytab-path}")
    private String keytabPath;

    @Value("${kafka.security.krb5-path}")
    private String krb5Path;

    @Value("${kafka.security.truststore-path}")
    private String truststorePath;

    @Value("${kafka.security.truststore-password}")
    private String truststorePassword;

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.schema-registry-url}")
    private String schemaRegistryUrl;

    /**
     * Injects the global Kerberos configuration path into the JVM before Kafka beans are initialized.
     */
    @PostConstruct
    public void initKerberosEnv() {
        System.setProperty("java.security.krb5.conf", krb5Path);
        System.setProperty("sun.security.krb5.debug", "true");
    }

    /**
     * Extracts common security configurations (SASL_SSL + Kerberos).
     */
    private Map<String, Object> commonSecurityProps() {
        Map<String, Object> props = new HashMap<>();

        // Basic security protocol
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(SaslConfigs.SASL_MECHANISM, "GSSAPI");
        props.put(SaslConfigs.SASL_KERBEROS_SERVICE_NAME, "kafka");

        // Dynamic JAAS configuration integration
        String jaasConfig = String.format(
                "com.sun.security.auth.module.Krb5LoginModule required " +
                        "useKeyTab=true " +
                        "storeKey=true " +
                        "keyTab=\"%s\" " +
                        "principal=\"%s\";",
                keytabPath, clientPrincipal
        );
        props.put(SaslConfigs.SASL_JAAS_CONFIG, jaasConfig);

        // SSL truststore configuration for transport encryption
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, truststorePath);
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, truststorePassword);
        props.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, "JKS");

        return props;
    }

    // ==========================================
    //               Producer Configuration
    // ==========================================

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // Producer specific configs
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        // Serialization configs
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);

        props.putAll(commonSecurityProps());

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // ==========================================
    //               Consumer Configuration
    // ==========================================

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // Consumer specific configs
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

        // Deserialization configs
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);

        props.putAll(commonSecurityProps());

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
