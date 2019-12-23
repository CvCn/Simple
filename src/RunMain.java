
import java.util.ArrayList;
import java.util.Random;

public class RunMain {

    public static int random(int scope){
        return random(scope, new Random().nextBoolean());
    }

    public static int random(int scope, boolean b){
        int i = new Random().nextInt(scope);
        return b ? i : -i;
    }

    public static ArrayList<Point> genStudyData(){
        ArrayList<Point> integers = new ArrayList<>();
        for(int i=0; i<7000; i++){
            integers.add(new Point(random(50), random(50), random(50), random(50)));
        }
        return integers;
    }

    public static void main(String[] args) {
        Perception perceptron = new Perception(0.3f,20000);

        ArrayList<Point> points = genStudyData();
        perceptron.fit(points);
        perceptron.printCorrectRate();
        System.out.println(points.stream().filter(item ->item.result == 1).count());

        System.out.println(perceptron.predict(new Point(-21, -31, 0, 10)));
        System.out.println(new Point(-21, -31, 0, 10).result);
        System.out.println(perceptron.predict(new Point(1, 1, 130, -12)));
        System.out.println(new Point(1, 1, 130, -12).result);

    }
}
