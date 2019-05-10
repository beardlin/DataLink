package com.ucar.datalink.writer.kafka;

import com.ucar.datalink.contract.Record;
import com.ucar.datalink.domain.plugin.writer.kafka.KafkaWriterParameter;
import com.ucar.datalink.worker.api.handle.Handler;
import com.ucar.datalink.worker.api.task.TaskWriter;
import com.ucar.datalink.writer.kafka.handle.KafkaEventRecordHandler;

public class KafkaTaskWriter extends TaskWriter<KafkaWriterParameter> {

    @Override
    protected Handler getHandler(Class<? extends Record> clazz) {
        return new KafkaEventRecordHandler();
    }
}
