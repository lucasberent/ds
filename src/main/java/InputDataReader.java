import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.io.data.DataReader;
import de.erichseifert.gral.io.data.DataReaderFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class InputDataReader {
    private String fileName;
    private DataSource dataSource;

    public DataSource readFile(String fileName) throws IOException {
        FileInputStream dataStream = new FileInputStream(fileName);
        DataReaderFactory factory = DataReaderFactory.getInstance();
        DataReader reader = factory.get("text/csv");
        try {
            dataSource = reader.read(dataStream, String.class,
                    Integer.class, Integer.class, Integer.class,
                    Integer.class, Integer.class,
                    String.class, String.class, String.class,
                    Integer.class);
        } finally {
            dataStream.close();
        }
        return this.dataSource;
    }
}
