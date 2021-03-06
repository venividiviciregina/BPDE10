package com.company;

import java.util.Random;

public class Date {
    private int day;
    private int month;
    private int year;
    private static Random rnd = new Random(System.currentTimeMillis());

    static class Interval{
        Date start;
        Date finish;

        public Interval(Date start, Date finish) {
            if (start.compareTo(finish)<=0) {
                this.start = start;
                this.finish = finish;
            } else {
                this.start = finish;
                this.finish = start;
            }
        }
        private int getDays(){
            int rez=0;
            for (int i=start.getYear();i<finish.getYear();i++){
                Date tempDate = new Date(1,1,i);
                rez+=tempDate.getDaysPerYear();
            }
            rez-=start.daysFromNY();
            rez+=finish.daysFromNY();
            return rez;
        }


    }

    public Date(int day, int month, int year){
        this.day=day;
        this.month = month;
        this.year= year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String toString(){
        String s="Date: "+day+".";
        s+=month+".";
        s+=year;
        return s;
    }

    public boolean isCorrect(){
        return  isCorrectYear()&&
                isCorrectMonth()&&
                isCorrectDay();
    }

    private boolean isCorrectYear(){
        return (year>0);
    }

    private boolean isCorrectMonth(){
        return (month>0)&&(month<=12);
    }

    private boolean isCorrectDay(){
        return ((day>0)&&(day<=getDayPerMonth()));
    }

    public int getDayPerMonth(){
        int[] days={31,28,31,30,31,30,31,31,30,31,30,31};
        if (isLeapYear()&&(month==2)){
            return 29;
        } else {
            return days[month-1];
        }
    }

    public boolean isLeapYear(){
        return (
                ((year%4==0)&&(year%100!=0)) ||
                        (year%400==0)
        );
    }

    public static boolean isLeapYear(int year){
        return (
                ((year%4==0)&&(year%100!=0)) ||
                        (year%400==0)
        );
    }

    public static Date  getRandomDate(){
        Date date;
        do {
            int year = getRandomInt(1900, 2030);
            int month = getRandomInt(1, 12);
            int day = getRandomInt(1, 31);
            date = new Date(day,month,year);
        }while (!date.isCorrect());

        return date;

    }

    private static int getRandomInt(int min, int max) {
        return  min+rnd.nextInt(max-min+1);
    }

    public  int compareTo(Date date){
        if(this.year!=date.year){
            if (this.year>date.year){
                return 1;
            } else {
                return -1;
            }
        }
        if (this.month!=date.month){
            if (this.month>date.month){
                return 1;
            } else {
                return -1;
            }
        }
        if (this.day!=date.day){
            if (this.day>date.day){
                return 1;
            } else {
                return -1;
            }
        }
        return  0;
    }

    public static int comparatorDate (Date date1, Date date2){
        if (date1.year!=date2.year){
            return date1.year-date2.year;
        }
        if (date1.month!=date2.month){
            return date1.month-date2.month;
        }
        return date1.day-date2.day;
    }
/*
    flag = true
   while (flag){
   check Scanner if value is correct flag = false
   }



 */

    public static void sort (Date[] dates){
        boolean notSortFlag = true;
        while (notSortFlag){
            notSortFlag = false;
            for (int j = 0; j < (dates.length - 1) ; j++) {
                if (comparatorDate(dates[j], dates[j + 1]) > 0) {
                    notSortFlag = true;
                    Date tmp = dates[j];
                    dates[j] = dates[j + 1];
                    dates[j + 1] = tmp;
                }
            }
        }
    }

    private static boolean isIntersect (Interval interval1,Interval interval2){
        return !((interval1.start.compareTo(interval2.finish)>0) ||
        (interval2.start.compareTo(interval1.finish)>0));
    }

    public static  boolean isIntersect (Date start1,Date finish1, Date start2,Date finish2){
        Interval interval1=new Interval(start1, finish1);
        Interval interval2=new Interval(start2, finish2);
        return isIntersect(interval1, interval2);
    }

    public int daysFromNY(){
        int rez=0;
        for (int i=1; i<this.month;i++){
            Date tempDate=new Date(1, i, this.year);
            rez+=tempDate.getDayPerMonth();
        }
        rez+=this.day;
        return rez;
    }

    public int getDaysPerYear(){
        return (isLeapYear())?366:365;
    }

    public int daysToNY(){
        return getDaysPerYear()-this.daysFromNY();
    }

    public static int getDaysBetween(Date start,Date finish){
        Interval interval = new Interval(start, finish);
        return interval.getDays();
    }


//[3][4][6][8][10][][]

    // 8-3+1
    //   0 1 2 3 4 5 6 7 8 9 10 11
}

