package tries.stream;

public class User {

    private int no;
    private String name;
    private String dscrip;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(int no, String name, String dscrip, Integer age) {
        this.no = no;
        this.name = name;
        this.dscrip = dscrip;
        this.age = age;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDscrip() {
        return dscrip;
    }

    public void setDscrip(String dscrip) {
        this.dscrip = dscrip;
    }

}
