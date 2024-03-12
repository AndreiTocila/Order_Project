package com.project.stockservice.exception;

import com.project.stockservice.service.DLTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.RecordDeserializationException;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaErrorHandler implements CommonErrorHandler
{
    private final DLTService dltService;

    @Override
    public boolean handleOne(Exception exception, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container)
    {
        handle(exception, consumer, record);
        return true;
    }

    @Override
    public void handleOtherException(Exception exception, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener)
    {
        handle(exception, consumer);
    }
    private void handle(Exception exception, Consumer<?, ?> consumer)
    {
        log.error("Exception thrown", exception);
        if (exception instanceof RecordDeserializationException ex)
        {
            consumer.seek(ex.topicPartition(), ex.offset() + 1L);
            consumer.commitSync();
        } else
        {
            log.error("Exception not handled", exception);
        }
    }

    private void handle(Exception exception, Consumer<?, ?> consumer, ConsumerRecord<?, ?> record)
    {
        log.error("Exception thrown", exception);
        dltService.sendToDLT(record);
        consumer.seek(new TopicPartition(record.topic(), record.partition()),record.offset() + 1L);
        log.info("Record moved to DLT");
    }
}
