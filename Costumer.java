package AppLayer;

/**
 * Created by Filip on 25-04-2016.
 */

public class Costumer {
    private String firstName;
    private String lastName;
    private String mail;
    private String city;
    private String address;
    private String zip;
    private String phoneNum;
    private String bankAcc;
    private String cpr;

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }



    public String getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Costumer(String cpr,String firstName, String lastName, String mail, String city, String address, String zip, String phoneNum, String bankAcc){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.city = city;
        this.address = address;
        this.zip = zip;
        this.phoneNum = phoneNum;
        this.bankAcc = bankAcc;
        this.cpr = cpr;
    }

    public Costumer(){

    }
}

