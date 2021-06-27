import java.util.*;

public class STL {
    public static void main(String[] args) {
//        ArrayList<Integer> arr = new ArrayList<>();
//        arr.add(10);
//        arr.add(20);
//        arr.add(30);
//
//        System.out.println(arr.get(1));
//
//        arr.set(0, 1);
//
//        for(int x: arr){
//            System.out.print(x);
//            System.out.print(" ");
//        }
//
//        System.out.println("");
//
//        arr.remove(1);
//
//        for(int x: arr){
//            System.out.print(x);
//            System.out.print(" ");
//        }

//        TreeSetImplementation tsl = new TreeSetImplementation();
//        tsl.print();

        TreeMapImpl tml = new TreeMapImpl();
        tml.print();
    }
}

class TreeSetImplementation{
    public void print(){
        TreeSet<Integer> x = new TreeSet<>();
        x.add(2);
        x.add(1);
        x.add(3);

        System.out.println(x);

        x.remove(2);
        System.out.println(x);

        x.add(3);
        System.out.println(x);

        System.out.println(x.contains(1));
        System.out.println(x.contains(2));

        System.out.println(x.higher(2));
    }
}

class TreeMapImpl{
    public void print(){
        TreeMap<String, Integer> x = new TreeMap<>();
        x.put("Soham", 10);
        x.put("Rajesh", 20);
        System.out.println(x);

        x.replace("Soham", x.get("Soham") + 20);

        System.out.println(x);

        x.remove("Soham");

        System.out.println(x);

        System.out.println(x.containsKey("Soham"));
        System.out.println(x.containsKey("Rajesh"));


    }
}