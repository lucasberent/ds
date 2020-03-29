import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;

import java.util.*;

public class PieChartProcessor implements Processor {
    private DataSource dataSource;

    public PieChartProcessor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataTable aggregateColumnOverColumnTopK(int colToAggregate, int colToAggregateOver, int k) {
        DataTable table = new DataTable(Integer.class, String.class);
        HashMap<String, Integer> result = new HashMap<String, Integer>();

        String colName;
        int currVal, newVal;
        for (int rowCnt = 0; rowCnt < this.dataSource.getRowCount(); rowCnt++) {
            colName = (String) this.dataSource.get(colToAggregateOver, rowCnt);

            if (result.containsKey(colName)) {
                currVal = result.get(colName);
                newVal = currVal + (Integer) this.dataSource.get(colToAggregate, rowCnt);
                result.put(colName, newVal);
            } else {
                result.put(colName, (Integer) this.dataSource.get(colToAggregate, rowCnt));
            }
        }

        Set<String> keySet = result.keySet();
        SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();

        for (String key : keySet) {
            sortedMap.put(result.get(key), key);
        }

        SortedSet<Integer> top10 = new TreeSet<Integer>(sortedMap.keySet());
        List<Integer> helper = new ArrayList<Integer>();
        for (int i = 0; i < k && i < top10.size(); i++) {
            helper.add(top10.last());
            top10.remove(top10.last());
        }

        int val;
        for (String key : keySet) {
            val = result.get(key);
            if (helper.contains(val)) {
                table.add(val, key);
            }
        }
        return table;
    }
}
