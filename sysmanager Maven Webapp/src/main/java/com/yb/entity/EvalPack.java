package com.yb.entity;


/**
 * Dashboard需要用到的评价
 * @author Administrator
 */
public class EvalPack {
    public String name;
    public String content;
    private String stationName;
    public Integer star;


    @Override
    public String toString() {
        return "EvalPack{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", stationName='" + stationName + '\'' +
                ", star=" + star +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public EvalPack(String name, String content, String stationName, Integer star) {

        this.name = name;
        this.content = content;
        this.stationName = stationName;
        this.star = star;
    }

    public EvalPack() {

    }
}
