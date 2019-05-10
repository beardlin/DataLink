package com.ucar.datalink.domain.plugin.writer.kafka;

import com.google.common.collect.Sets;
import com.ucar.datalink.domain.media.MediaSourceType;
import com.ucar.datalink.domain.plugin.PluginWriterParameter;

import java.util.Set;

/**
 * @author XC
 */
public class KafkaWriterParameter extends PluginWriterParameter {

    private String servers;
    private String topic;

    @Override
    public String initPluginName() {
        return "writer-kafka";
    }

    @Override
    public String initPluginClass() {
        return "com.ucar.datalink.writer.kafka.KafkaTaskWriter";
    }

    @Override
    public String initPluginListenerClass() {
        return "com.ucar.datalink.writer.kafka.KafkaTaskWriterListener";
    }

    @Override
    public Set<MediaSourceType> initSupportedSourceTypes() {
        return Sets.newHashSet(MediaSourceType.KAFKA);
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
