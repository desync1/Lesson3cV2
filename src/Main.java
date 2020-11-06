import java.util.Map;

public class Main {
    public static void main(String[] args) {
        SomeClass sc = new SomeClass("testFName", "testLName", 22);
        Map<String, Object> map = null;
        try {
            map = Serializer.serialize(sc);
            System.out.println(map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            SomeClass sc2 = Serializer.deserialize(map, SomeClass.class);
            System.out.println(sc2.getfName());
            System.out.println(sc2.getlName());
            System.out.println(sc2.getAge());
        } catch (IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
