package com.intellicloudpps.crimerepoart.Healper;

public class Constant {
    public static final String EMAILPATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static final String PASSWORDPATTERN = ("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$");
    public static final String NAMEPATTERN = "([a-zA-Z])"+  //any letter
            ".{3,10}"; //at least 15 characters;
    public static final String MOBILEPATTERN = "(?=.*[0-9])"+ ".{10,}" ;//at least 1 digit;



    public static final String CONFIRMPASSWORDSHOULDNOTBEEMPTY = "Confirm Password Should not Be Empty";
    public static final String NEWPASSWORDANDCONFORMPASSWORDDOESNOTMATCHED = " New Password And Confirm Password Dosn't Matched";
    public static final String EMAILSHOULDNOTBEEMPTY = "Email Should Not Be Empty";
    public static final String CITYSHOULDNOTBEEMPTY = "City Should Not Be Empty";
    public static final String PASSWORDSHOULDNOTBEEMPTY = "Password Should Not Be Empty";
    public static final String PASSWORDSENTTOEMAIL = "Password Sent To Email";
    public static final String USEONLYALPHABETS = "Use Only Alphabets";
    public static final String USEONLYNUMBERS = "Use Only Numbers";
    public static final String NEWPASSWORDSHOULDNOTBEEMPTY = "New Password Should Not Be Empty";
    public static final String FULLNAMESHOULDNOTBEEMPTY = "FullName should Not Be Empty";
    public static final String MOBILESHOULDNOTBEEMPTY = "Mobile Number Should Not Be Empty";
    public static final String ADDRESSSHOULDNOTBEEMPTY = "Address should Not Be Empty";
    public static final String OLDPASSWORDSHOULDNOTBEEMPTY = "Old Password Should Not Be Empty";
    public static final String NEWPASSWORDTOWEEK = "New Password Is To Week";
    public static final String NAMESHOULDNOTBEEMPTY = "Name Should Not Be Empty";
    public static final String MESSAGESHOULDNOTBEEMPTY = "Message Should Not Be Empty";
    public static final String PASSWORDISTOOWEEK = "Password Is To Week";
    public static final String INVALIDEMAILADDRESS = "Invalid Email Address";
    public static final String EMAIL = "email";
    public static final String DOYOUWONTTOLOGOUTTHISAPPLICATION = "Do you want to Logout this application ?";
    public static final String IMAGE = "image";
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final String CRIMEREPOART = "e Crime Repoart";
    public static final String SHOW = "Show";
    public static final String HIDE = "Hide";
    public static final String USERTYPE = "usertype";
    public static final String FULLNAME = "fullname";
    public static final String MOBILE = "mobile";
    public static final String CITY = "city";
    public static final String ADDRESS = "address";
    public static final String ACCESSKEY ="1234" ;
    public static final String RESULT ="result" ;
    public static final String SUCCESS ="success" ;
    public static final String MAIN = "main";
    public static final String SUCCESSFULLYINSERTINGTHENEWS = "Successfully Inserting The News";
    public static final String NOTINSERTINGTHENEWS = "Not Inserting The News";
    public static final String PROFILESUCESSFULLYUPDATED = "Profile Updated Sucessfully";
    public static final String PROFILESUCESSFULLYNOTUPDATED = "Profile Not Updated ";
    public static final String PASSWORDSUCESSFULLYUPDATED ="Password Updated Sucessfully" ;
    public static final String PASSWORDSUCESSFULLYNOTUPDATED ="Password Not Updated " ;
    public static final  String CRIMEID ="crimeid" ;
    public static final String STATUS ="status" ;
    public static String nodata="No Data Found";



    //new crime page
    public static final String TITLESHOULDNOTBEEMPTY = "Title Should Not Be Empty";
    public static final String DDESCRIPTIONSHOULDNOTBEEMPTY = "Description Should Not Be Empty";
    public static final String SELECTDATE = "Select Date";
    public static final String SELECTTIME = "Select Time";
    public static final String SELECTCATEGORY = "Select Categoty";
    public static final String AREASHOULDDNOTBEEMPTY = "Area Should Not Be Empty";
    public static final String PINCODESHOULDDNOTBEEMPTY = "PinCode Should Not Be Empty";
    public static final String REPOARTSUBMITTEDSUCESSFULLY ="Repoart Submitted Sucessfully";



    public static final String REPOARTUPDATEDSUCESSFULLY ="Repoart Updated Sucessfully";
    public static final String COMMENTSHOULDNOTBEEMPTY = "Comment Should Not Be Empty";



}
