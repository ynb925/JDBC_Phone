package co.example;

/**
 * Створити базу даних з таблицею phone і колонками int id, varchar(255) userName, varchar(15) phoneNumber;
 * Налаштувати коннекшн з базою даних у застосунку Java.
 * Створити клас Phone з параметрами int id, String userName, String phoneNumber.
 */

public class Phone {

    private long id;
    private String userName;
    private String phoneNumber;

    public Phone(long id, String userName, String phoneNumber) {
        this.id = id;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public Phone() {
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

