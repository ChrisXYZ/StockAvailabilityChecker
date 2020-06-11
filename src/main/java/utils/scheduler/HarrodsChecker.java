package utils.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import retailers.harrods.Harrods;
import utils.SendEmail;

import javax.mail.Session;


public class HarrodsChecker implements Job {

    private String email = "*********";
    private String password = "******";
    private String recipient = "**********";

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String url = "https://www.harrods.com/en-gb/shopping/weekend-max-mara-reversible-margie-coat-15215222";
        Harrods harrods = new Harrods(url);
        int size = 8;
        Double price = harrods.findPrice();
        if (harrods.isAvailable(size) && price < 610.0) {  //SPECIAL CASE TO ALSO CHECK HARRODS HAS A LOWER PRICE THEN £610.
            SendEmail sendEmail = new SendEmail();
            Session session = sendEmail.setupSystemProperties(email, password);
            sendEmail.sendMail(session, email, recipient, "HARRODS", "HARRODS ITEM IS AVAILABLE IN SIZE " + size + " WITH PRICE " + price);
        }
        harrods.closeWebDriver();
    }
}


