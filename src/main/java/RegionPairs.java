import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RegionPairs {
    private static final Map REGIONS = Map.of(
            "BAH", "me-south-1",
            "BPM", "in-amazon-1",
            "CGK", "ap-southeast-3",
            "CPT", "af-south-1",
            "HKG", "ap-east-1",
            "HYD", "ap-south-2",
            "MDW", "us-northeast-1",
            "MXP", "eu-south-1",
            "TLV", "il-central-1");

    public void extractData(String admsRegionData, String amdbData, String statusData) throws IOException {
        String account = null;

        HashMap<String, String> amdbAccounts = new HashMap<>();
        HashMap<String, String> statuses = new HashMap<>();
        StringBuilder res = new StringBuilder();
        Map regionNameToCode = new HashMap<>();

        REGIONS.forEach((k, v) -> regionNameToCode.put(v, k));

        try {
            File amdbFile = new File(amdbData);
            Scanner amdbIn = new Scanner(amdbFile);

            while (amdbIn.hasNext()) {
                String line = amdbIn.nextLine();

                String [] parts = line.split("\t");
                amdbAccounts.put(String.format("%s:%s", parts[0].trim(), REGIONS.get(parts[1].trim())), line);
            }

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        try {
            File statusFile = new File(statusData);
            Scanner statusIn = new Scanner(statusFile);

            while (statusIn.hasNext()) {
                String line = statusIn.nextLine();
                String[] parts = line.split(" ");
                account = parts[parts.length - 1];

                statuses.put(parts[0].trim(), parts[1].trim());
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        try {
            File admsFile = new File(admsRegionData);
            Scanner admsIn = new Scanner(admsFile);

            while (admsIn.hasNext()) {
                String line = admsIn.nextLine();

                if (line.contains("Querying ADMS with account_id:")) {
                    String[] parts = line.split(" ");
                    account = parts[parts.length - 1].trim();
                } else if (line.matches("\\d+.*")) {
                    String[] parts = line.split("\t");
//                    String key = String.format("%s:%s", account, parts[1].trim());
//                    if (amdbAccounts.containsKey(key)) {
                    String regionName = parts[1].trim();
                    if (regionNameToCode.containsKey(regionName)) {
                        res.append(account).append('\t')
                                .append(regionNameToCode.get(regionName)).append('\t')
                                .append(parts[2].trim()).append('\t')
                                .append(statuses.get(account)).append("\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        System.out.println(res.toString());

        Path path = Paths.get("./out.txt");
        byte[] strToBytes = res.toString().getBytes();

        Files.write(path, strToBytes);
    }

    public static void main(String [] args) throws IOException {
        new RegionPairs().extractData(
                "/Users/stsymb/Documents/TR-M/P101006128-region-opt-output.txt",
                "/Users/stsymb/Documents/TR-M/account-region-pairs-20230927.txt",
                "/Users/stsymb/Documents/TR-M//P101006128-state-output.txt.filtered");
    }
}

