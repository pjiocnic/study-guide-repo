package com.example.emailvalidator;

import org.apache.commons.validator.routines.EmailValidator;
import javax.mail.internet.InternetAddress;
import javax.naming.directory.*;
import javax.naming.NamingException;
import java.net.InetAddress;
import java.util.Hashtable;

public class EmailValidatorUtil {

    public static boolean isFormatValid(String email) {
        return EmailValidator.getInstance(true).isValid(email);
    }

    public static boolean isFormatValidFallback(String email) {
        try {
            InternetAddress addr = new InternetAddress(email);
            addr.validate();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isDomainResolvable(String domain) {
        try {
            InetAddress.getByName(domain);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean hasMXRecord(String domain) {
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            DirContext dirContext = new InitialDirContext(env);
            Attributes attrs = dirContext.getAttributes(domain, new String[]{"MX"});
            Attribute attr = attrs.get("MX");
            return attr != null && attr.size() > 0;
        } catch (NamingException ex) {
            return false;
        }
    }

    public static String extractDomain(String email) {
        int atIndex = email.lastIndexOf("@");
        return (atIndex > 0) ? email.substring(atIndex + 1) : "";
    }

    public static boolean isEmailValid(String email) {
        if (!isFormatValid(email)) return false;

        String domain = extractDomain(email);
        if (domain.isEmpty()) return false;

        return isDomainResolvable(domain) && hasMXRecord(domain);
    }
} 

