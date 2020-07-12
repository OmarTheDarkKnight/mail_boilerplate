package mail;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

//mvn exec:java -Dexec.mainClass="mail.ZipAndSendMail"
public class ZipAndSendMail {
    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;
    static String[] toEmails = {"email1@gmail.com", "email2@gmail.com"};
    static String fromUser = "from email address";// imap on, other devices on, no recovery phone to send email with this account
    static String password = "password";

    public static void main(String[] args) throws Exception {
        String zipFolder = System.getProperty("user.dir"); // Or any other folder that you want to zip to send in email
        String attachmentPath = zipFolder + "//" + "Mail.zip"; // Change the file name and destination as required

        // find latest folder
        // uncomment if required
//		File dir = new File(zipFolder);
//		File[] files = dir.listFiles();
//		File lastModified = Arrays.stream(files).filter(File::isDirectory).max(Comparator.comparing(File::lastModified)).orElse(null);
//		System.out.println(lastModified.getName());

        //zip
        Zip.zipFolder(zipFolder, attachmentPath);

        //mail
        Mail javaEmail = new Mail();
        javaEmail.setMailServerProperties();

        javaEmail.createEmailMessage(
                "Generic Email Subject", // subject
                "Please find the zip in attachment.", // body
                attachmentPath, // attachment path
                "Project.zip", // name of attachment
                toEmails// receivers
        );
        javaEmail.sendEmail(fromUser, password);
    }
}