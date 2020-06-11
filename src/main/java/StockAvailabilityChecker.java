import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import utils.FenwickChecker;
import utils.HarrodsChecker;

import java.util.Date;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class StockAvailabilityChecker {

    public static void main(String[] args) throws SchedulerException {
//        Fenwick fenwick = new Fenwick("https://www.fenwick.co.uk/women/clothing/coats-and-jackets/trench/margie-check-tie-belt-coat/0001155527-001.html?dwvar_0001155527-001_color=LightBlue%2FBeige#q=max%20mara%20weekend&start=85&sz=12&page=3");
//        System.out.println(fenwick.isAvailable(8));
//        System.out.println(fenwick.findPrice());
//
//        Harrods harrods = new Harrods("https://www.harrods.com/en-gb/shopping/weekend-max-mara-reversible-margie-coat-15215222");
//        System.out.println(harrods.isAvailable(8));
//        System.out.println(harrods.findPrice());

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail fenwickJob = newJob(FenwickChecker.class)
                .withIdentity("FenwickCheck", "Retailers")
                .build();
        Trigger fenwicktrigger = newTrigger()
                .withIdentity("Fenwick1hr", "Retailers")
                .withSchedule(simpleSchedule()
                        .withIntervalInMinutes(60)
                        .repeatForever())
                .startAt(evenMinuteDate(new Date()))
                .build();
        sched.scheduleJob(fenwickJob, fenwicktrigger);
        sched.start();

        JobDetail harrodsJob = newJob(HarrodsChecker.class)
                .withIdentity("HarrodsCheck", "Retailers")
                .build();
        Trigger harrodsTrigger = newTrigger()
                .withIdentity("Harrods1hr", "Retailers")
                .withSchedule(simpleSchedule()
                        .withIntervalInMinutes(60)
                        .repeatForever())
                .startAt(evenMinuteDate(new Date()))
                .build();
        sched.scheduleJob(harrodsJob, harrodsTrigger);
        sched.start();

    }
}
