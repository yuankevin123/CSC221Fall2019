import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class NetworkLogManager {

    private final SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyy HH:mm:ss");

    private final ArrayList<LogEntry> listLogEntries;

    public enum SearchField {
        ID, TIMESTAMP, SOURCE, DESTINATION, PROTOCOL, LENGTH, DESCRIPTION
    }

    public NetworkLogManager() {

        this.listLogEntries = new ArrayList<LogEntry>();
    }

    public boolean loadFile(String fileName) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(fileName));

        String line = "";

        while (scanner.hasNext()) {

            line = scanner.nextLine();

            String arr[] = line.split(",");

            try {

                listLogEntries.add(new LogEntry(arr[0].trim(), arr[1].trim(), arr[2].trim(), arr[3].trim(), arr[4].trim(), arr[5].trim(), arr[6].trim()));
            }
            catch (Exception ex) {
                System.out.printf("Skipping line: %s%n", line);
            }
        }

        scanner.close();

        return true;
    }

    public String toString() {

        return String.format("NetworkLogManager: there are %,d records", listLogEntries.size());
    }

    public LogEntry searchById(String str) {

        return searchBy(str, SearchField.ID);
    }

    public LogEntry searchByTimestamp(String str) {

        return searchBy(str, SearchField.TIMESTAMP);
    }

    public LogEntry searchBySource(String str) {

        return searchBy(str, SearchField.SOURCE);
    }

    public LogEntry searchByDestination(String str) {

        return searchBy(str, SearchField.DESTINATION);
    }

    public LogEntry searchByProtocol(String str) {

        return searchBy(str, SearchField.PROTOCOL);
    }

    public LogEntry searchByLength(String str) {

        return searchBy(str, SearchField.LENGTH);
    }

    public LogEntry searchByDescription(String str) {

        return searchBy(str, SearchField.DESCRIPTION);
    }

    public ArrayList<LogEntry> searchByRange(String fromDate, String toDate) throws ParseException {

        ArrayList<LogEntry> retList = new ArrayList<LogEntry>();

        Date from = formatter.parse(fromDate);
        Date to = formatter.parse(toDate);

        for (LogEntry le : listLogEntries) {

            Date timestamp = formatter.parse(le.getTimestamp());

            if (timestamp.compareTo(from) >= 0 && timestamp.compareTo(to) <= 0)
                retList.add(le);
        }

        return retList;
    }

    private LogEntry searchBy(String searchVal, SearchField field) {

        for (LogEntry le : listLogEntries) {

            String val;

            switch (field) {
                case ID:
                    val = le.getId();
                    break;
                case TIMESTAMP:
                    val = le.getTimestamp();
                    break;
                case SOURCE:
                    val = le.getSource();
                    break;
                case DESTINATION:
                    val = le.getDestination();
                    break;
                case DESCRIPTION:
                    val = le.getDescription();
                    break;
                case PROTOCOL:
                    val = le.getProtocol();
                    break;
                case LENGTH:
                    val = le.getLength();
                    break;
                default:
                    val = null;
            }

            if (val.equals(searchVal))
                return le;
        }

        return null;
    }
}
