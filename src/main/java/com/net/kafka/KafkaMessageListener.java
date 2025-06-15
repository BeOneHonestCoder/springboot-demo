package com.net.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class KafkaMessageListener {

    @KafkaListener(topics = "orders", groupId = "order-group")
    public void handleOrder(ConsumerRecord<String, String> record) {
        System.out.printf("Received order: key=%s, value=%s%n",
                record.key(), record.value());
    }

//    @KafkaListener(topics = "${kafka.topic}")
//    public void consume(String payload) {
//        MarkitData data =JSON.parse(payload);
//        insertDB(data);
//    }

//    @KafkaListener(topics = "${kafka.topic}", concurrency = "4")
//    public void consume(List<String> payloads, Acknowledgment ack) {
//        List<MarkitData> batch = payloads.stream()
//                .parallel()  // 并行解析
//                .map(p -> mapper.readValue(p, type))
//                .collect(Collectors.toList());
//
//        CompletableFuture.runAsync(() -> batchInsertDB(batch), dbExecutor)
//                .thenRun(ack::acknowledge);
//    }

}
