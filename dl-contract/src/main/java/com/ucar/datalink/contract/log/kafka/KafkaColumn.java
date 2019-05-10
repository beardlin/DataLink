package com.ucar.datalink.contract.log.kafka;


import java.io.Serializable;

public class KafkaColumn implements Serializable {

    public KafkaColumn() {

    }

    public KafkaColumn(String columnName, String columnValue) {
        this.columnName = columnName;
        this.columnValue = columnValue;
    }

    /**
     * 列名称
     */
    private String columnName;

    /**
     * 列的值，timestamp,Datetime是一个long型的数字.
     */
    private String columnValue;


    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
        result = prime * result + ((columnValue == null) ? 0 : columnValue.hashCode());
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        KafkaColumn kafkaColumn = new KafkaColumn();
        kafkaColumn.setColumnName(columnName);
        kafkaColumn.setColumnValue(columnValue);
        return kafkaColumn;
    }

    @Override
    public String toString() {
        return "KafkaColumn{" +
                "columnName='" + columnName + '\'' +
                ", columnValue='" + columnValue + '\'' +
                '}';
    }
}
