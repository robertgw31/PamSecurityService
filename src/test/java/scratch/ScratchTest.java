package scratch;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

public class ScratchTest {

    private static final DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnn'Z'");
    private static final DateTimeFormatter is8601Formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public static final void main(String[] args) {

        OffsetDateTime time1 = OffsetDateTime.now();
        OffsetDateTime time2 = Instant.now().atZone(ZoneId.systemDefault()).toOffsetDateTime();

        System.out.printf("time1 (is8601Formatter) = %s\n",is8601Formatter.format(time1));
        System.out.printf("time2 (is8601Formatter) = %s\n",is8601Formatter.format(time2));


        System.out.printf("time1 (customFormatter) = %s\n",customFormatter.format(time1));
        System.out.printf("time2 (customFormatter) = %s\n",customFormatter.format(time2));    }
}
