package com.ucar.datalink.writer.kafka.utils;

import com.alibaba.fastjson.JSON;
import com.ucar.datalink.contract.log.kafka.KafkaEventRecord;
import com.ucar.datalink.contract.log.rdbms.RdbEventRecord;
import com.ucar.datalink.domain.plugin.writer.kafka.KafkaWriterParameter;
import com.ucar.datalink.worker.api.task.TaskWriterContext;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.Properties;

/**
 * @author XC
 */
public class ProducerUtils {

    private static Properties properties;

    public static void sendMessage(List<RdbEventRecord> records, TaskWriterContext context) {

        String topic = getParameter(context).getTopic();

        try {
            Producer<String, String> producer = new KafkaProducer<>(getProperties(context));

            for (RdbEventRecord record: records) {
                String content = JSON.toJSONString(record);
                producer.send(new ProducerRecord<>(topic, "key", content));
            }

            producer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static Properties getProperties(TaskWriterContext context) {

        if (properties == null) {
            String servers = getParameter(context).getServers();

            properties = new Properties();
            properties.put("bootstrap.servers", servers);
            properties.put("acks", "all");
            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        }

        return properties;
    }

    static KafkaWriterParameter getParameter(TaskWriterContext context) {
        return (KafkaWriterParameter) context.getWriterParameter();
    }


}
