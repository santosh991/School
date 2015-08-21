/*  package com.yahoo.petermwenda83.email.util;

      import java.io.InputStream;
	  import java.util.ArrayList;
	  import java.util.Date;
       import java.util.List;
        
       import javax.mail.MessagingException;
        
       import org.quartz.JobExecutionContext;
       import org.quartz.JobExecutionException;
       import org.springframework.core.io.ClassPathResource;
       import org.springframework.scheduling.quartz.QuartzJobBean;
        
       public class BirthdayWisherJob extends QuartzJobBean
       {
         
        private EmailService emailService;
        public void setEmailService(EmailService emailService)
        {
         this.emailService = emailService;
        }
         
        @Override
        protected void executeInternal(JobExecutionContext context) throws JobExecutionException
        {
         System.out.println("Sending Birthday Wishes... ");
         List<User> usersBornToday = getUsersBornToday();
         for (User user : usersBornToday)
         {
          try
          {
           Email email = new Email();
           email.setFrom("admin@sivalabs.com");
           email.setSubject("Happy BirthDay");
           email.setTo(user.getEmail());
           email.setText("<h1>Dear "+user.getName()+
             ",
       Many Many Happy Returns of the day :-)</h1>");
             
           byte[] data = null;
           ClassPathResource img = new ClassPathResource("HBD.gif");
           InputStream inputStream = img.getInputStream();
           data = new byte[inputStream.available()];
           while((inputStream.read(data)!=-1));
           
           Attachment attachment = new Attachment(data, "HappyBirthDay",
             "image/gif", true);
           email.addAttachment(attachment);
           
           emailService.sendEmail(email);
          }
          catch (MessagingException e)
          {
           e.printStackTrace();
          }
          catch (Exception e)
          {
           e.printStackTrace();
          }
         }
        }
         
        private List<User> getUsersBornToday()
        {
         List<User> users = new ArrayList<User>();
         User user1 = new User("Siva Prasad", "sivaprasadreddy.k@gmail.com", new Date());
         users.add(user1);
         User user2 = new User("John", "abcd@gmail.com", new Date());
         users.add(user2);
         return users;
        }
       }
       */