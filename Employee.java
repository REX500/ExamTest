package AppLayer;

/**
 * Created by Filip on 25-04-2016.
 */
public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String mail;
    private String city;
    private String address;
    private String zip;
    private String phoneNum;
    private String bankAcc;
    private String CPR;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
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

    public Employee(int id,String firstName, String lastName, String mail, String city, String address, String zip, String phoneNum, String bankAcc, String CPR){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.city = city;
        this.address = address;
        this.zip = zip;
        this.phoneNum = phoneNum;
        this.bankAcc = bankAcc;
        this.CPR = CPR;
    }

    public Employee(){

    }

}
