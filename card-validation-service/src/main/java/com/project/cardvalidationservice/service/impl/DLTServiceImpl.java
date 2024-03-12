package com.project.cardvalidationservice.service.impl;

import com.project.cardvalidationservice.service.DLTService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DLTServiceImpl implements DLTService
{
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendToDLT(ConsumerRecord<?, ?> record)
    {
        kafkaTemplate.send("dlt", record.key().toString(), record.value().toString());
    }
}
