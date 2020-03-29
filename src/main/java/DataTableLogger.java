import de.erichseifert.gral.data.DataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataTableLogger {
    private final static Logger LOGGER = LoggerFactory.getLogger(BarPlotVisualizer.class);

    public static void logDataTable(final DataTable dataTable, final String name) {
        int rows = dataTable.getRowCount();
        int cols = dataTable.getColumnCount();
        StringBuilder output = new StringBuilder();
        if (name != null && !name.isEmpty()) {
            LOGGER.debug("**** printing data table: {} **** \n", name);
        }
        output.append("\n");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                output.append(dataTable.get(j, i)).append(" ");
            }
            output.append("\n");
        }

        LOGGER.debug(output.toString());
    }
}
