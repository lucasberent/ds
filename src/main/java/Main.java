import de.erichseifert.gral.data.DataSource;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        DataSource dataSource = new InputDataReader().readFile("data.csv");

        BarPlotVisualizer visualizer = new BarPlotVisualizer(4, 6, "Corona cases per counrty (top 10 worldwide)", dataSource);
        visualizer.setVisible(true);

        BarPlotVisualizer visualizer2 = new BarPlotVisualizer(5, 6, "Corona deaths per counrty (top 10 worldwide)", dataSource);
        visualizer2.setVisible(true);

        PieChartVisualizer visualizer3 = new PieChartVisualizer(4, 6, "Corona cases per counrty (top 10 worldwide)", dataSource);
        visualizer3.setVisible(true);

        LineChartVisualizer lineChartVisualizer = new LineChartVisualizer(4, 0, "New Corona cases worldwide per dates", dataSource);
        lineChartVisualizer.setVisible(true);
    }
}
