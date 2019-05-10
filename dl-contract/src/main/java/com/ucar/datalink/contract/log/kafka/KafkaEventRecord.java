package com.ucar.datalink.contract.log.kafka;

import com.ucar.datalink.contract.RSI;
import com.ucar.datalink.contract.Record;
import com.ucar.datalink.contract.log.rdbms.EventType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xC
 */
public class KafkaEventRecord extends Record<String> implements Serializable {

    /**
     * 数据表名
     */
    private String tableName;

    /**
     * 变更数据的业务类型(I/U/D/C/A/E)
     */
    private EventType eventType;

    /**
     * 发生数据变更的业务时间.
     */
    private long executeTime;


    /**
     * 变更后非主键的其他字段
     */
    private List<KafkaColumn> columns = new ArrayList<>();


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    public List<KafkaColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<KafkaColumn> columns) {
        this.columns = columns;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public RSI RSI() {
        return null;
    }

    @Override
    public String toString() {
        return "KafkaEventRecord{" +
                "tableName='" + tableName + '\'' +
                ", eventType=" + eventType +
                ", executeTime=" + executeTime +
                ", columns=" + columns +
                '}';
    }
}
