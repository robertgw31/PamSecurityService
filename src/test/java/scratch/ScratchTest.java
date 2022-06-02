package scratch;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

public class ScratchTest {

    public static final void main(String[] args) {

//        ZoneId z = ZoneId.systemDefault();
//        System.out.printf("Zone ID: %s, name: %s\n",z.getId(),z.getDisplayName(TextStyle.FULL, Locale.US));
//
//        Set<String> zoneIds= ZoneId.getAvailableZoneIds();
//
//        for (String zone : zoneIds) {
//            System.out.println(zone);
//        }
        Arrays.stream(Locale.getISOCountries()).forEach(l -> {
            System.out.printf("%s\n",l);
        });

    }
}
