package com.yb.entity;

/**
 * 用户手册数据刷新频率展示
 * @author Administrator
 */
public class RefreshData {
    private Integer id;
    private String data;
    private String frequency;
    private String refreshType;

    public RefreshData() {
    }

    public RefreshData(Integer id, String data, String frequency, String refreshType) {

        this.id = id;
        this.data = data;
        this.frequency = frequency;
        this.refreshType = refreshType;
    }

    @Override
    public String toString() {
        return "RefreshData{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", frequency='" + frequency + '\'' +
                ", refreshType='" + refreshType + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getRefreshType() {
        return refreshType;
    }

    public void setRefreshType(String refreshType) {
        this.refreshType = refreshType;
    }
}
