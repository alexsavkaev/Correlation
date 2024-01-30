import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class PearsonsCorrelation {
    public static double correlation(int[] dataSet1, int[] dataSet2){
        if(dataSet1.length != dataSet2.length){
            throw new IllegalArgumentException("Input arrays must have the same length");
        }
        int n = dataSet1.length;
        double mean1 = Arrays.stream(dataSet1).average().orElse(0);
        double mean2 = Arrays.stream(dataSet2).average().orElse(0);
        double[] deviation1 = IntStream.range(0, n)
                .mapToDouble(i -> dataSet1[i] - mean1)
                .toArray();
        double[] deviation2 = IntStream.range(0, n)
                .mapToDouble(i -> dataSet2[i] - mean2)
                .toArray();
        double numerator = IntStream.range(0, n)
                .mapToDouble(i -> deviation1[i] * deviation2[i])
                .sum();
        double denominator1 = Math.sqrt(DoubleStream.of(deviation1)
                .mapToObj(i -> i * i)
                .reduce(0.0, Double::sum));
        double denominator2 = Math.sqrt(DoubleStream.of(deviation2)
                .mapToObj(i -> i * i)
                .reduce(0.0, Double::sum));
        double denominator = denominator1 * denominator2;
        if(denominator == 0) return 0;
        return numerator / denominator;
    }


}
