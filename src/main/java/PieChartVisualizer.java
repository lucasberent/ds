import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.graphics.Location;
import de.erichseifert.gral.plots.PiePlot;
import de.erichseifert.gral.plots.legends.ValueLegend;
import de.erichseifert.gral.ui.InteractivePanel;

import javax.swing.*;
import java.awt.*;

public class PieChartVisualizer extends JFrame {
    public PieChartVisualizer(int col, int row, String title, DataSource dataSource) {
        DataTable top10DeathsPerCountryTable = new PieChartProcessor(dataSource).aggregateColumnOverColumnTopK(col, row, 10);
        DataTableLogger.logDataTable(top10DeathsPerCountryTable, title);
        this.drawPiePlot(top10DeathsPerCountryTable, title);
    }

    private void drawPiePlot(final DataTable dataTable, String title) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(getContentPane().getMinimumSize());
        setSize(800, 400);
        setTitle(title);
        PiePlot piePlot = new PiePlot(dataTable);
        piePlot.setLegendVisible(true);
        piePlot.setInsets(new Insets2D.Double(20.0, 40.0, 40.0, 40.0));
        PiePlot.PieSliceRenderer pointRenderer = (PiePlot.PieSliceRenderer) piePlot.getPointRenderer(dataTable);
        pointRenderer.setInnerRadius(1.5);
        pointRenderer.setGap(0.2);
        pointRenderer.setValueColor(Color.BLACK);
        pointRenderer.setValueVisible(true);
        pointRenderer.setValueLocation(Location.NORTH);

        ValueLegend legend = (ValueLegend) piePlot.getLegend();
        legend.setLabelColumn(1);
        setSize(1000, 1000);
        add(new InteractivePanel(piePlot));
    }
}
