package utils;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import retailers.fenwick.Fenwick;

import javax.mail.Session;


public class FenwickChecker implements Job {

    private String email = "*********";
    private String password = "******";
    private String recipient = "**********";

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Fenwick fenwick = new Fenwick("https://www.fenwick.co.uk/women/clothing/coats-and-jackets/trench/margie-check-tie-belt-coat/0001155527-001.html?dwvar_0001155527-001_color=LightBlue%2FBeige#q=max%20mara%20weekend&start=85&sz=12&page=3");
        int size = 8;
        if (fenwick.isAvailable(size)) {
            SendEmail sendEmail = new SendEmail();
            Session session = sendEmail.setupSystemProperties(email, password);
            Double price = fenwick.findPrice();
            sendEmail.sendMail(session, email, recipient, "FENWICK", "FENWICK ITEM IS AVAILABLE IN SIZE " + size + " WITH PRICE " + price);
        }
        fenwick.closeWebDriver();
    }
}

