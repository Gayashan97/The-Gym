public class Date {

    private int day;
    private int month;
    private int year;

    public Date(int year, int month, int day) throws IllegalArgumentException{
        if (!isValid(year, month, day)) throw new IllegalArgumentException();
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static boolean isValid(int year, int month, int day) {
        if (year < 0) return false;
        if ((month < 1) || (month > 12)) return false;
        if ((day < 1) || (day > 31)) return false;
        switch (month) {
            case 1:
                return true;
            case 2:
                return day <= 29;
            case 3:
                return true;
            case 4:
                return day < 31;
            case 5:
                return true;
            case 6:
                return day < 31;
            case 7:
                return true;
            case 8:
                return true;
            case 9:
                return day < 31;
            case 10:
                return true;
            case 11:
                return day < 31;
            default:
                return true;
        }
    }

    @Override
    public String toString() {
        return getDay() +" - "+ getMonth() +" - "+ getYear();
    }


}
