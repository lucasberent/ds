import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LineChartProcessor implements Processor {
    private DataSource dataSource;

    public LineChartProcessor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataTable aggregateColumnOverColumnTopK(int colToAggregate, int colToAggregateOver, int k) throws ParseException {
        DataTable table = new DataTable(Double.class, Integer.class);
        HashMap<Double, Integer> result = new HashMap<>();

        Double colDate;
        int currVal, newVal;
        for (int rowCnt = 0; rowCnt < this.dataSource.getRowCount(); rowCnt++) {
            colDate = (double) new SimpleDateFormat("dd/MM/yyyy")
                    .parse((String) this.dataSource.get(colToAggregateOver, rowCnt)).getTime();

            if (result.containsKey(colDate)) {
                currVal = result.get(colDate);
                newVal = currVal + (Integer) this.dataSource.get(colToAggregate, rowCnt);
                result.put(colDate, newVal);
            } else {
                result.put(colDate, (Integer) this.dataSource.get(colToAggregate, rowCnt));
            }
        }

        Set<Double> keySet = result.keySet();

        SortedMap<Integer, Double> sortedMap = new TreeMap<>();

        for (double key : keySet) {
            sortedMap.put(result.get(key), key);
        }
        List<Integer> helper = new ArrayList<Integer>();
        if (k > 0) {
            SortedSet<Integer> top10 = new TreeSet<Integer>(sortedMap.keySet());
            for (int i = 0; i < k && i < top10.size(); i++) {
                helper.add(top10.last());
                top10.remove(top10.last());
            }
        } else {
            helper.addAll(sortedMap.keySet());
        }
        int val;
        for (Double key : keySet) {
            val = result.get(key);
            if (val > 0 && helper.contains(val)) {
                table.add(key, val);
            }
        }
        return table;
    }
}
