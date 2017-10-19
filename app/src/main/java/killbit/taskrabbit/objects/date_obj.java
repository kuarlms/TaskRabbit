package killbit.taskrabbit.objects;

/**
 * Created by kural on 10/19/17.
 */

public class date_obj {
    String month,day, date;

    public date_obj(String month, String day, String date) {
        this.month = month;
        this.day = day;
        this.date = date;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
