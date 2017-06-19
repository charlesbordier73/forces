package outlook;

import com.sun.mail.util.MailSSLSocketFactory;
import helpers.DialogHelper;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.Properties;

public class CheckingMails {

    final String host = "imap-mail.outlook.com";// host address and protocol : here imap
    //final String user = "polytechpi@outlook.fr";// mail address
    //final String password = "Polytech2017";// Password
    final String user = "test.tgi-montpellier@justice.fr";// mail address
    final String password = "AzertyQsdfg123";// Password

    private static CheckingMails instance;

    private CheckingMails(){}

    public static CheckingMails getInstance(){
        if (instance == null){
            instance = new CheckingMails();
        }
        return instance;
    }

    /**
     * Checks if any unread email is available on the host / user / password given in beginning of class
     * If any unread email is present, requires a parsing of this email
     */
    public void check()
    {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        try {

            //create properties field

            Properties properties = new Properties();
            properties.setProperty("mail.store.protocol", "imap");
            properties.setProperty("mail.imap.user", user);
            properties.setProperty("mail.imap.host", host);
            properties.setProperty("mail.imap.port", "993");

            properties.setProperty("mail.imaps.socketFactory.class", SSL_FACTORY);
            properties.setProperty("mail.imaps.socketFactory.fallback", "false");
            properties.setProperty("mail.imaps.socketFactory.port", "993");
            properties.put("mail.imaps.host", "imap-mail.outlook.com");
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.imaps.ssl.trust", "*");
            properties.put("mail.imaps.ssl.socketFactory", sf);
            System.out.println("Retrieving emails from " + user +" ...");
            Session emailSession = Session.getDefaultInstance(properties);
            //create the POP3 store object and connect with the imap server
            Store store = emailSession.getStore("imaps");
            store.connect(host, user, password);
            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.search(
                    new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            System.out.println("Messages in inbox : " + messages.length);
            if (messages.length == 0){
                DialogHelper.dialogPop("Récupération des Emails", "Pas de nouvelles demandes ou nouveaux retours de mission", "Ok");
            }
            int i =1;
            for (Message message : messages) {
                writePart(message);
            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    public static void main(String[] args) {

        String host = "imap-mail.outlook.com";// host address and protocol : here imap
        String username = "polytechpi@outlook.fr";// mail address
        String password = "Polytech2017";// Password

        check(host, username, password);

    } */


    /*
   * This method checks for content-type
   * based on which, it processes and
   * fetches the content of the message
   */
    public void writePart(Part p) throws Exception {

        if (p instanceof Message)
            EmailParserRequest.getInstance().setSender((Message)p);
            String emailType = ((Message)p).getSubject();
           // writeEnvelope((Message) p);

        System.out.println("----------------------------");

        //check if the content is plain text
        if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) p.getContent();
            int count = mp.getCount();
            for (int i = 0; i < count; i++)
                if(mp.getBodyPart(i).isMimeType("text/plain")){
                    System.out.println(emailType);
                    if (emailType.contains("REQUEST")){
                        EmailParserRequest.getInstance().parseEmailContent((String)mp.getBodyPart(i).getContent());
                    }
                    if (emailType.contains("REPORT")){
                        EmailParserMissionReport.getInstance().parseEmailContent((String)mp.getBodyPart(i).getContent());
                    }

            }
        } else {
            System.out.println(emailType);
            if (emailType.contains("REQUEST")){
                EmailParserRequest.getInstance().parseEmailContent((String)p.getContent());
            }
            if (emailType.contains("REPORT")){
                EmailParserMissionReport.getInstance().parseEmailContent((String)p.getContent());
            }
        } /*
        //check if the content has attachment
        else
        //check if the content is a nested message
        else if (p.isMimeType("message/rfc822")) {
            System.out.println("This is a Nested Message");
            System.out.println("---------------------------");
            writePart((Part) p.getContent());
        }
        else {
            Object o = p.getContent();
            if (o instanceof String) {
                System.out.println("This is a string");
                System.out.println("---------------------------");
                System.out.println((String) o);
            }
            else if (o instanceof InputStream) {
                System.out.println("This is just an input stream");
                System.out.println("---------------------------");
                InputStream is = (InputStream) o;
                is = (InputStream) o;
                int c;
                while ((c = is.read()) != -1)
                    System.out.write(c);
            }
            else {
                System.out.println("This is an unknown type");
                System.out.println("---------------------------");
                System.out.println(o.toString());
            }
        } */

    }

    /*
    * This method would print FROM,TO and SUBJECT of the message, for tests purposes mainly
    */
    public void writeEnvelope(Message m) throws Exception {
        System.out.println("This is the message envelope");
        System.out.println("---------------------------");
        Address[] a;

        // FROM
        if ((a = m.getFrom()) != null) {
            for (int j = 0; j < a.length; j++)
                System.out.println("FROM: " + a[j].toString());
        }

        // TO
        if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
            for (int j = 0; j < a.length; j++)
                System.out.println("TO: " + a[j].toString());
        }

        // SUBJECT
        if (m.getSubject() != null)
            System.out.println("SUBJECT: " + m.getSubject());

    }
}