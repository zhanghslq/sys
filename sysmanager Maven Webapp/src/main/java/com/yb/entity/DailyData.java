package com.yb.entity;

/**
 * 每日发送简报的包装类
 * @author Administrator
 */
public class DailyData {
    private String station;
    private String dateStr;
    private Double litreAll;
    private Double litre92;
    private Double litre95;
    private Double litre98;
    private Double diesel;
    private Double shopSales;

    @Override
    public String toString() {
        return "DailyData{" +
                "station='" + station + '\'' +
                ", dateStr='" + dateStr + '\'' +
                ", litreAll=" + litreAll +
                ", litre92=" + litre92 +
                ", litre95=" + litre95 +
                ", litre98=" + litre98 +
                ", diesel=" + diesel +
                ", shopSales=" + shopSales +
                '}';
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Double getLitreAll() {
        return litreAll;
    }

    public void setLitreAll(Double litreAll) {
        this.litreAll = litreAll;
    }

    public Double getLitre92() {
        return litre92;
    }

    public void setLitre92(Double litre92) {
        this.litre92 = litre92;
    }

    public Double getLitre95() {
        return litre95;
    }

    public void setLitre95(Double litre95) {
        this.litre95 = litre95;
    }

    public Double getLitre98() {
        return litre98;
    }

    public void setLitre98(Double litre98) {
        this.litre98 = litre98;
    }

    public Double getDiesel() {
        return diesel;
    }

    public void setDiesel(Double diesel) {
        this.diesel = diesel;
    }

    public Double getShopSales() {
        return shopSales;
    }

    public void setShopSales(Double shopSales) {
        this.shopSales = shopSales;
    }

    public DailyData(String station, String dateStr, Double litreAll, Double litre92, Double litre95, Double litre98, Double diesel, Double shopSales) {

        this.station = station;
        this.dateStr = dateStr;
        this.litreAll = litreAll;
        this.litre92 = litre92;
        this.litre95 = litre95;
        this.litre98 = litre98;
        this.diesel = diesel;
        this.shopSales = shopSales;
    }

    public DailyData() {

    }
}
