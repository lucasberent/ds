import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.plots.PiePlot;
import de.erichseifert.gral.plots.legends.ValueLegend;
import de.erichseifert.gral.ui.InteractivePanel;
import sun.print.IPPPrintService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BarChartVisualizer extends JFrame {
    public BarChartVisualizer(int col, int row, String title) throws IOException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(getContentPane().getMinimumSize());
        setSize(800, 400);
        DataSource dataSource = new InputDataReader().readFile("data.csv");
        Processor processor = new Processor(dataSource);

        DataTable top10DeathsPerCountryTable = processor.aggregateColumnValuesOverColumnTop10BarChartData(col, row);
        this.drawPiePlot(top10DeathsPerCountryTable, title);
    }

    private void drawPiePlot(final DataTable dataTable, String title) {
        setTitle(title);
        PiePlot piePlot = new PiePlot(dataTable);
        piePlot.setLegendVisible(true);
        piePlot.setInsets(new Insets2D.Double(20.0,40.0,40.0,40.0));
        PiePlot.PieSliceRenderer pointRenderer = (PiePlot.PieSliceRenderer) piePlot.getPointRenderer(dataTable);
        pointRenderer.setInnerRadius(1.5);
        pointRenderer.setGap(0.2);
        pointRenderer.setValueColor(Color.BLACK);

        ValueLegend legend = (ValueLegend) piePlot.getLegend();
        legend.setLabelColumn(1);
        setSize(1000,1000);
        add(new InteractivePanel(piePlot));
    }
}
