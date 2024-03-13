/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author HP
 *
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@ManagedBean
@RequestScoped
public class FormBean {
    
    private static final Logger logger = Logger.getLogger(FormBean.class.getName());

    // Business Owner Details
    private String applicantCitizenship;
    private String phoneNumber;
    private String emailAddress;
    private String applicantProvince;

    // Business Details
    private String businessType;
    private String companyName;
    private String tinNumber;
    private String registrationDate;
    private String businessProvince;
    
     private String purposeOfImport;
    private String productCategory;
    private String weight;
    private String siUnit;
    private String quantityOfProduct;
    private String productDescription;


    // Getters and Setters
    public String getApplicantCitizenship() {
        return applicantCitizenship;
    }

    public void setApplicantCitizenship(String applicantCitizenship) {
        this.applicantCitizenship = applicantCitizenship;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getApplicantProvince() {
        return applicantProvince;
    }

    public void setApplicantProvince(String applicantProvince) {
        this.applicantProvince = applicantProvince;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public void setTinNumber(String tinNumber) {
        this.tinNumber = tinNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getBusinessProvince() {
        return businessProvince;
    }

    public void setBusinessProvince(String businessProvince) {
        this.businessProvince = businessProvince;
    }

    
    
 
   
    public String getPurposeOfImport() {
        return purposeOfImport;
    }

    public void setPurposeOfImport(String purposeOfImport) {
        this.purposeOfImport = purposeOfImport;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSiUnit() {
        return siUnit;
    }

    public void setSiUnit(String siUnit) {
        this.siUnit = siUnit;
    }

    public String getQuantityOfProduct() {
        return quantityOfProduct;
    }

    public void setQuantityOfProduct(String quantityOfProduct) {
        this.quantityOfProduct = quantityOfProduct;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
  
// Method to submit form
    

// ...

public String submitForm() {
        // Configure the email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Configure the sender's credentials
        final String username = "davidvuningoma.com"; 
        final String password = "swjovrbvmqdurrxc"; 
        // Create a session with the SMTP server
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("davidvuningoma@gmail.com"));

            // Set the email subject
            message.setSubject("Form Submission");

            // Create the email content
            String content = "Applicant Citizenship: " + applicantCitizenship + "<br/>"
                    + "Phone Number: " + phoneNumber + "<br/>"
                    + "Email Address: " + emailAddress + "<br/>"
                    + "Applicant Province: " + applicantProvince + "<br/>"
                    + "Business Type: " + businessType + "<br/>"
                    + "Company Name: " + companyName + "<br/>"
                    + "TIN Number: " + tinNumber + "<br/>"
                    + "Registration Date: " + registrationDate + "<br/>"
                    + "Business Province: " + businessProvince + "<br/>"
                    + "Purpose of Import: " + purposeOfImport + "<br/>"
                    + "Product Category: " + productCategory + "<br/>"
                    + "Weight: " + weight + "<br/>"
                    + "SI Unit: " + siUnit + "<br/>"
                    + "Quantity of Product: " + quantityOfProduct + "<br/>"
                    + "Product Description: " + productDescription + "<br/>";

            // Set the email content as HTML
            message.setContent(content, "text/html");

            // Send the email
            Transport.send(message);

            // Add a FacesMessage to display a success message on the page
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Success", "Form submitted successfully."));

            // Return the navigation outcome
            return "confirmation";
         } catch (MessagingException e) {
        logger.log(Level.SEVERE, "An exception occurred while sending the email", e);
        return "error";
    } catch (Exception e) {
        logger.log(Level.SEVERE, "An unexpected exception occurred", e);
        return "error";
    }
    }

}