import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Processor {
    private DataSource dataSource;

    public Processor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataTable aggregateColumnValuesOverColumnTop10(int colToAggregate, int colToAggregateOver) {
        DataTable table = new DataTable(Double.class, Integer.class, String.class);
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
        for (int i = 0; i < 10 && i < top10.size(); i++) {
            helper.add(top10.last());
            top10.remove(top10.last());
        }

        double cnt = 30.0;
        int val;
        for (String key : keySet) {
            val = result.get(key);
            if (helper.contains(val)) {
                table.add(cnt, val, key);
                cnt += 10.0;
            }
        }
        return table;
    }

    public DataTable aggregateColumnValuesOverColumnTop10BarChartData(int colToAggregate, int colToAggregateOver) {
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
        for (int i = 0; i < 10 && i < top10.size(); i++) {
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

    public DataTable calculateXYplotStats(int colToAggregate, int colToAggregateOver) throws ParseException {
        DataTable table = new DataTable(Double.class, Integer.class);
        HashMap<Double, Integer> result = new HashMap<Double, Integer>();

        Date colDate;
        int currVal, newVal;
        for (int rowCnt = 0; rowCnt < this.dataSource.getRowCount(); rowCnt++) {
            colDate = new SimpleDateFormat("dd/MM/yyyy").parse((String) this.dataSource.get(colToAggregateOver, rowCnt));

            if (result.containsKey(colDate)) {
                currVal = result.get(colDate);
                newVal = currVal + (Integer) this.dataSource.get(colToAggregate, rowCnt);
                result.put((double) (colDate.getTime()), newVal);
            } else {
                result.put((double) (colDate.getTime()), (Integer) this.dataSource.get(colToAggregate, rowCnt));
            }
        }

        Set<Double> keySet = result.keySet();
        int val;
        for (Double key : keySet) {
            val = result.get(key);
            if (val > 0) {
                table.add(key, val);
            }
        }
        return table;
    }
}
