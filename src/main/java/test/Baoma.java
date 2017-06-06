package test;

/**
 * Created by pc on 2017-05-25.
 *
 * @author pc
 * @version 3.0.0-SNAPSHOT
 * @since 3.0.0-SNAPSHOT
 */
public class Baoma extends Car {
    public static int zero=4;
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Baoma baoma=new Baoma();
        baoma.setName("11");
        System.out.println(Baoma.zero);
    }
}
