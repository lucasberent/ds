import de.erichseifert.gral.data.DataTable;

import java.text.ParseException;

public interface Processor {
    DataTable aggregateColumnOverColumnTopK(int colToAggregate, int colToAggregateOver, int k) throws ParseException;
}
