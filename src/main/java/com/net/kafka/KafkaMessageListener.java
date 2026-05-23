package com.net.kafka;

import com.net.avro.ActivityLogEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class KafkaMessageListener {

    @KafkaListener(topics = "orders", groupId = "order-group")
    public void handleOrder(ConsumerRecord<String, ActivityLogEvent> record) {
        ActivityLogEvent eventLog = record.value();

        System.out.printf("Received log: key=%s, type=%s, status=%s, url=%s, time=%s%n",
                record.key(),
                eventLog.getActivityType(),
                eventLog.getOperationStatus(),
                eventLog.getRequestUrl(),
                eventLog.getTimestamp());
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
