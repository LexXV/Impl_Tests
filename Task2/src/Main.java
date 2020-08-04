import java.io.InputStream;
import java.util.ArrayList;
import java.util.PriorityQueue;

/* Make the function to compare points characteristics in ridges array */
class Point implements Comparable<Point>{
    public String pointName;
    public int nr;
    double dist = Double.POSITIVE_INFINITY;
    int parent;

    public ArrayList<GraphRidge> graphRidges = new ArrayList<>();

    public Point(String var, int nr) { // Initialize points characteristics
        this.pointName = var;
        this.nr = nr;
    }

    public void addGraphRidge(GraphRidge graphRidge) { // Add values to array
        this.graphRidges.add(graphRidge);
    }

    public int compareTo(Point point) { // Compare points
        return Double.compare(dist, point.dist);
    }
}

/* Initialize ridges characteristics */
class GraphRidge {
    public final double weight;
    public final int target;

    GraphRidge(int target, double weight) {
        this.target = target;
        this.weight = weight;
    }
}

/* Head method */
public class Main {
    static Point[] NAME;

    public static void main(String[] args) throws Exception {
        int cities;
        FastReader in = FastReader.reader;
        /* Initialize input values */
        int r;
        int p;
        int numTests = in.nextInt();
        while (numTests > 0){
            cities = in.nextInt();
            NAME = new Point[cities + 1];
            for (int i = 1; i <= cities; i++){
                String city = in.nextString();
                NAME[i] = new Point(city, i);
                p = in.nextInt();
                for (int j = 0; j < p; j++){
                    int a = in.nextInt();
                    int b = in.nextInt();
                    NAME[i].addGraphRidge(new GraphRidge(a, b));
                }
            }
            r = in.nextInt();
            for (int i = 0; i < r; i++) {
                String city1 = in.nextString();
                String city2 = in.nextString();
                Point a = find(city1, NAME);
                Point b = find(city2, NAME);
                ComparePaths(a, b);
                System.out.println((int)b.dist);
                for (int j = 1; j < NAME.length; j++){
                    NAME[j].dist = Double.POSITIVE_INFINITY;
                    NAME[j].parent = -1;
                }
            }
            numTests--;
        }
    }

    /* Make function to find the position of point */
    public static Point find(String name, Point[] X){
        for (int i = 1; i < X.length; i++){
            if (X[i].pointName.equals(name)){
                return X[i];
            }
        }
        return null;
    }

    /* Make the function to compare paths using Dijkstra's algorithm */
    public static void ComparePaths(Point start, Point end){
        start.dist = 0;
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(start);
        while (!queue.isEmpty()){
            Point z = queue.poll();
            for (GraphRidge point : z.graphRidges){
                Point n = NAME[point.target];
                if (n.dist > z.dist + point.weight){
                    queue.remove(n.dist);
                    n.dist = z.dist + point.weight;
                    n.parent = z.nr;
                    queue.add(n);
                }
            }
            if (z == end){
                break; // Break loop at end point
            }
        }
    }
}

/* Make function to read and declare the input */
final class FastReader{

    public static final FastReader reader = new FastReader(System.in);

    private final InputStream in;

    private final byte[] buffer = new byte[16];
    private int pos, count;

    public FastReader(InputStream in) {
        this.in = in;
        pos = count = 0;
    }

    public int nextInt() {
        int c;
        while ((c = read()) < '0');
        int result = c - '0';
        while ((c = read() - '0') >= 0) {
            result = 10 * result + c;
        }
        return result;
    }

    public String nextString(){
        StringBuilder s = new StringBuilder();
        int c;
        while ((c = read()) >= 'A') {
            s.append((char)c);
        }
        return s.toString();

    }
    private void fillBuffer() {
        try {
            count = in.read(buffer, pos = 0, buffer.length);
        } catch (Exception e){}
    }

    public int read() {
        if (pos == count) {
            fillBuffer();
        }
        return buffer[pos++];
    }
}
