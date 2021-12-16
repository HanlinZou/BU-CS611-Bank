import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * BankTime class is helpful when computing time and handling time change.
 *
 * It implements the runnable interface and use Observer Pattern and
 * Singleton Pattern, so when it changes, all its dependents could
 * receive a notice.
 */
public class BankTimer implements Runnable {
    private static BankTimer timer = new BankTimer();
    private List<TimerObserver> observers;
    private Date date;
    private ConfigDao configDao = ConfigDao.getInstance();

    private BankTimer() {
        date = new Date();
        observers = new ArrayList<>();
    }

    public static BankTimer getInstance(){
        return timer;
    }

    public Date getDate() {
        return date;
    }

    public long getTimeStamp() {
        return date.getTime();
    }

    public String getTimeStr() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        return ft.format(date);
    }

    public String getDateStr() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        return ft.format(date);
    }

    public int getYear() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy");
        return Integer.parseInt(ft.format(date));
    }

    public int getMonth() {
        SimpleDateFormat ft = new SimpleDateFormat ("MM");
        return Integer.parseInt(ft.format(date));
    }

    public int getDay() {
        SimpleDateFormat ft = new SimpleDateFormat ("dd");
        return Integer.parseInt(ft.format(date));
    }

    public void addObserver(TimerObserver timerObserver){
        observers.add(timerObserver);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(configDao.getConfigInt("INTERVAL", 1) * 1000);  // update every 1 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int oldYear = getYear();
            int oldMonth = getMonth();
            int oldDay = getDay();

            date = new Date();
            // System.out.println(getTimeStr() + " " + getTimeStamp());

            for (TimerObserver observer : observers) {
                observer.onTimeChange();
                if (getYear() != oldYear) observer.onYearChange();
                if (getMonth() != oldMonth) observer.onMonthChange();
                if (getDay() != oldDay) observer.onDayChange();
            }
        }
    }
}
