package com.yb.entity;

/**
 * @author zhs
 */
public class CouponDaySend {
    private String day;
    private Integer station_id;
    private String oils;
    private Double oilMoney;
    private Double oilLitre;
    private Double real_pay;
    private Double price1;
    private Double price2;
    private Double direct_discount;
    private Double oil_discount;
    private Double notoil_discount;
    private Double ordinary_discount;

    @Override
    public String toString() {
        return "CouponDaySend{" +
                "day='" + day + '\'' +
                ", station_id=" + station_id +
                ", oils='" + oils + '\'' +
                ", oilMoney=" + oilMoney +
                ", oilLitre=" + oilLitre +
                ", real_pay=" + real_pay +
                ", price1=" + price1 +
                ", price2=" + price2 +
                ", direct_discount=" + direct_discount +
                ", oil_discount=" + oil_discount +
                ", notoil_discount=" + notoil_discount +
                ", ordinary_discount=" + ordinary_discount +
                '}';
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getStation_id() {
        return station_id;
    }

    public void setStation_id(Integer station_id) {
        this.station_id = station_id;
    }

    public String getOils() {
        return oils;
    }

    public void setOils(String oils) {
        this.oils = oils;
    }

    public Double getOilMoney() {
        return oilMoney;
    }

    public void setOilMoney(Double oilMoney) {
        this.oilMoney = oilMoney;
    }

    public Double getOilLitre() {
        return oilLitre;
    }

    public void setOilLitre(Double oilLitre) {
        this.oilLitre = oilLitre;
    }

    public Double getReal_pay() {
        return real_pay;
    }

    public void setReal_pay(Double real_pay) {
        this.real_pay = real_pay;
    }

    public Double getPrice1() {
        return price1;
    }

    public void setPrice1(Double price1) {
        this.price1 = price1;
    }

    public Double getPrice2() {
        return price2;
    }

    public void setPrice2(Double price2) {
        this.price2 = price2;
    }

    public Double getDirect_discount() {
        return direct_discount;
    }

    public void setDirect_discount(Double direct_discount) {
        this.direct_discount = direct_discount;
    }

    public Double getOil_discount() {
        return oil_discount;
    }

    public void setOil_discount(Double oil_discount) {
        this.oil_discount = oil_discount;
    }

    public Double getNotoil_discount() {
        return notoil_discount;
    }

    public void setNotoil_discount(Double notoil_discount) {
        this.notoil_discount = notoil_discount;
    }

    public Double getOrdinary_discount() {
        return ordinary_discount;
    }

    public void setOrdinary_discount(Double ordinary_discount) {
        this.ordinary_discount = ordinary_discount;
    }

    public CouponDaySend(String day, Integer station_id, String oils, Double oilMoney, Double oilLitre, Double real_pay, Double price1, Double price2, Double direct_discount, Double oil_discount, Double notoil_discount, Double ordinary_discount) {

        this.day = day;
        this.station_id = station_id;
        this.oils = oils;
        this.oilMoney = oilMoney;
        this.oilLitre = oilLitre;
        this.real_pay = real_pay;
        this.price1 = price1;
        this.price2 = price2;
        this.direct_discount = direct_discount;
        this.oil_discount = oil_discount;
        this.notoil_discount = notoil_discount;
        this.ordinary_discount = ordinary_discount;
    }

    public CouponDaySend() {

    }
}
