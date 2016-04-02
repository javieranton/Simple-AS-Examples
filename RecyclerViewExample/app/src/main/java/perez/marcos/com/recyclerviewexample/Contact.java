package perez.marcos.com.recyclerviewexample;

/**
 * Created by marcos on 06/07/2015.
 */
public class Contact {
    private int icon;
    private String name;
    private String phone;

    Contact(int icon, String name, String phone){
        this.icon = icon;
        this.name = name;
        this.phone = phone;

    }
    Contact(){

    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
