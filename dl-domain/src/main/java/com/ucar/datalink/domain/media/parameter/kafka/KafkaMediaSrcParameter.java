package com.ucar.datalink.domain.media.parameter.kafka;

import com.ucar.datalink.domain.media.parameter.MediaSrcParameter;

public class KafkaMediaSrcParameter extends MediaSrcParameter {

    private String servers;
    private String topic;

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
