package com.project.feedbackservice.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface DLTService
{
    void sendToDLT(ConsumerRecord<?,?> record);
}
