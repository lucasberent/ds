import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Visualizer visualizer = new Visualizer(4, 6, "Corona cases per counrty (top 10 worldwide)");
        visualizer.setVisible(true);

        Visualizer visualizer2 = new Visualizer(5, 6, "Corona deaths per counrty (top 10 worldwide)");
        visualizer2.setVisible(true);

        BarChartVisualizer visualizer3 = new BarChartVisualizer(4, 6, "Corona cases per counrty (top 10 worldwide)");
        visualizer3.setVisible(true);

        LineChartVisualizer lineChartVisualizer = new LineChartVisualizer(4, 0, "Corona cases with dates");
        lineChartVisualizer.setVisible(true);
    }
}
