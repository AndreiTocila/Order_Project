package com.project.stockservice.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface DLTService
{
    void sendToDLT(ConsumerRecord<?,?> record);
}
