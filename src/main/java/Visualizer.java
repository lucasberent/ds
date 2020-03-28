import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.data.statistics.Statistics;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.graphics.Location;
import de.erichseifert.gral.plots.BarPlot;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.GraphicsUtils;
import de.erichseifert.gral.util.MathUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static de.erichseifert.gral.plots.XYPlot.AXIS_X;

public class Visualizer extends JFrame {
    public Visualizer(int col, int row, String title) throws IOException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(getContentPane().getMinimumSize());
        setSize(800, 400);
        DataSource dataSource = new InputDataReader().readFile("data.csv");
        Processor processor = new Processor(dataSource);

        DataTable top10DeathsPerCountryTable = processor.aggregateColumnValuesOverColumnTop10(col, row);
        this.drawBarPlot(top10DeathsPerCountryTable, title);
    }

    private void drawBarPlot(final DataTable dataTable, String title) {
        BarPlot barPlot = new BarPlot(dataTable);
        barPlot.setInsets(new Insets2D.Double(40.0, 40.0, 40.0, 40.0));
        barPlot.setBarWidth(5.0);
        BarPlot.BarRenderer pointRenderer = (BarPlot.BarRenderer) barPlot.getPointRenderers(dataTable).get(0);
        pointRenderer.setColor(
                new LinearGradientPaint(0f, 0f, 0f, 1f, new float[]{0.0f, 1.0f},
                        new Color[]{Color.RED, GraphicsUtils.deriveBrighter(Color.RED)}
                )
        );

        pointRenderer.setBorderStroke(new BasicStroke(5f));
        pointRenderer.setBorderColor(
                new LinearGradientPaint(0f, 0f, 0f, 1f,
                        new float[]{0.0f, 1.0f},
                        new Color[]{GraphicsUtils.deriveBrighter(Color.RED), Color.RED}
                )
        );

        barPlot.getAxisRenderer(AXIS_X).setLabelDistance(1.5);
        barPlot.getAxisRenderer(AXIS_X).setTicksVisible(false);
        pointRenderer.setValueVisible(true);
        pointRenderer.setValueColumn(2);
        pointRenderer.setValueLocation(Location.CENTER);
        pointRenderer.setValueColor(GraphicsUtils.deriveDarker(Color.BLACK));
        pointRenderer.setValueFont(Font.decode(null).deriveFont(Font.BOLD));
        pointRenderer.setValueDistance(10.0);

        barPlot.getAxis(BarPlot.AXIS_Y).setRange(0.0,
                MathUtils.ceil(dataTable.getStatistics().get(Statistics.MAX) * 1.1, 25.0));
        barPlot.getAxisRenderer(BarPlot.AXIS_Y).setTickAlignment(0.0);
        barPlot.getAxisRenderer(BarPlot.AXIS_Y).setMinorTicksVisible(false);
        barPlot.getAxisRenderer(BarPlot.AXIS_Y).setIntersection(-4.4);
        barPlot.getAxisRenderer(BarPlot.AXIS_Y).setTickLabelsOutside(false);
        setTitle(title);
        setSize(500, 500);
        add(new InteractivePanel(barPlot));
    }
}
