package com.yb.entity;

/**
 * @author Administrator
 */
public class Interval {
    private Integer today_one;
    private Integer one_two;
    private Integer two_three;
    private Integer three_four;
    private Integer four_five;
    private Integer five_six;
    private Integer six_seven;
    private Integer seven_eight;
    private Integer eight_nine;
    private Integer nine_ten;

    @Override
    public String toString() {
        return "Interval{" +
                "today_one=" + today_one +
                ", one_two=" + one_two +
                ", two_three=" + two_three +
                ", three_four=" + three_four +
                ", four_five=" + four_five +
                ", five_six=" + five_six +
                ", six_seven=" + six_seven +
                ", seven_eight=" + seven_eight +
                ", eight_nine=" + eight_nine +
                ", nine_ten=" + nine_ten +
                '}';
    }

    public Integer getToday_one() {
        return today_one;
    }

    public void setToday_one(Integer today_one) {
        this.today_one = today_one;
    }

    public Integer getOne_two() {
        return one_two;
    }

    public void setOne_two(Integer one_two) {
        this.one_two = one_two;
    }

    public Integer getTwo_three() {
        return two_three;
    }

    public void setTwo_three(Integer two_three) {
        this.two_three = two_three;
    }

    public Integer getThree_four() {
        return three_four;
    }

    public void setThree_four(Integer three_four) {
        this.three_four = three_four;
    }

    public Integer getFour_five() {
        return four_five;
    }

    public void setFour_five(Integer four_five) {
        this.four_five = four_five;
    }

    public Integer getFive_six() {
        return five_six;
    }

    public void setFive_six(Integer five_six) {
        this.five_six = five_six;
    }

    public Integer getSix_seven() {
        return six_seven;
    }

    public void setSix_seven(Integer six_seven) {
        this.six_seven = six_seven;
    }

    public Integer getSeven_eight() {
        return seven_eight;
    }

    public void setSeven_eight(Integer seven_eight) {
        this.seven_eight = seven_eight;
    }

    public Integer getEight_nine() {
        return eight_nine;
    }

    public void setEight_nine(Integer eight_nine) {
        this.eight_nine = eight_nine;
    }

    public Integer getNine_ten() {
        return nine_ten;
    }

    public void setNine_ten(Integer nine_ten) {
        this.nine_ten = nine_ten;
    }

    public Interval(Integer today_one, Integer one_two, Integer two_three, Integer three_four, Integer four_five, Integer five_six, Integer six_seven, Integer seven_eight, Integer eight_nine, Integer nine_ten) {

        this.today_one = today_one;
        this.one_two = one_two;
        this.two_three = two_three;
        this.three_four = three_four;
        this.four_five = four_five;
        this.five_six = five_six;
        this.six_seven = six_seven;
        this.seven_eight = seven_eight;
        this.eight_nine = eight_nine;
        this.nine_ten = nine_ten;
    }

    public Interval() {

    }
}
