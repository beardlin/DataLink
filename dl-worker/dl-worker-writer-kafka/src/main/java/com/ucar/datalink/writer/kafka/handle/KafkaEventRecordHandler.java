package com.ucar.datalink.writer.kafka.handle;

import com.ucar.datalink.contract.log.kafka.KafkaEventRecord;
import com.ucar.datalink.worker.api.handle.AbstractHandler;
import com.ucar.datalink.worker.api.task.TaskWriterContext;
import com.ucar.datalink.writer.kafka.utils.ProducerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * @author XC
 */
public class KafkaEventRecordHandler extends AbstractHandler<KafkaEventRecord> {

    private static final Logger logger = LoggerFactory.getLogger(KafkaEventRecordHandler.class);

    @Override
    protected void doWrite(List<KafkaEventRecord> records, TaskWriterContext context) {
        //
        ProducerUtils.sendMessage(records, context);

    }

}
