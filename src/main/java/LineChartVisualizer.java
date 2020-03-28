import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.plots.PiePlot;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.areas.LineAreaRenderer2D;
import de.erichseifert.gral.plots.axes.AxisRenderer;
import de.erichseifert.gral.plots.axes.LogarithmicRenderer2D;
import de.erichseifert.gral.plots.legends.ValueLegend;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LineChartVisualizer extends JFrame {
    public LineChartVisualizer(int col, int row, String title) throws IOException, ParseException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(getContentPane().getMinimumSize());
        setSize(800, 400);
        DataSource dataSource = new InputDataReader().readFile("data.csv");
        Processor processor = new Processor(dataSource);

        DataTable top10DeathsPerCountryTable = processor.calculateXYplotStats(col, row);
        this.drawLinePlot(top10DeathsPerCountryTable, title);
    }

    private void drawLinePlot(final DataTable dataTable, String title) {
        setTitle(title);
        XYPlot xyPlot = new XYPlot(dataTable);
        setSize(1000, 1000);

        // Chart spacing
        double insetsTop = 10.0,
                insetsLeft = 60.0,
                insetsBottom = 60.0,
                insetsRight = 10.0;
        xyPlot.setInsets(new Insets2D.Double(
                insetsTop, insetsLeft, insetsBottom, insetsRight));

        xyPlot.getAxisRenderer(XYPlot.AXIS_Y).setTicksVisible(true);
        xyPlot.getAxisRenderer(XYPlot.AXIS_Y).setTickLabelDistance(0.5);
        xyPlot.getAxisRenderer(XYPlot.AXIS_Y).setLabelDistance(1.25);
        xyPlot.getAxisRenderer(XYPlot.AXIS_Y).setTickLabelsOutside(false);

        xyPlot.getAxisRenderer(XYPlot.AXIS_X).setTicksVisible(true);
        xyPlot.getAxisRenderer(XYPlot.AXIS_X).setTickLabelDistance(0.5);
        xyPlot.getAxisRenderer(XYPlot.AXIS_X).setLabelDistance(0.75);
        xyPlot.getAxisRenderer(XYPlot.AXIS_X).setTickLabelFormat(new SimpleDateFormat("dd/MM/yyyy"));

        AxisRenderer rendererX = new LogarithmicRenderer2D();
        xyPlot.setAxisRenderer(XYPlot.AXIS_Y, rendererX);

        add(new InteractivePanel(xyPlot));
    }
}
