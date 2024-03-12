package com.project.feedbackservice.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface FeedbackService
{
    void processFeedback(ConsumerRecord<String, String> record);
}
