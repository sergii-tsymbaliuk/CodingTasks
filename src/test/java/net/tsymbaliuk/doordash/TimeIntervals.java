package net.tsymbaliuk.doordash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TimeIntervals {

}

// To find all the stores that are open in a user’s delivery radius, we need to check the store’s operating hours.
// We currently store this information in Elasticsearch but the query performance for our use-case is turning out to be
// not very efficient. The format that we store it in Elasticsearch is something like this:

// {
//   "store": {
//     "store_name": "Tasty Pizzas",
//     "store_id": "123345",
//     "operating_hours": [
//       {
//         "hours.open": "mon 10:00 am",
//         "hours.close": "mon 2:00 pm"
//       },
//       {
//         "hours.open": "tue 10:00 am",
//         "hours.close": "tue 2:00 pm"
//       },
//       {
//         "hours.open": "wed 10:00 am",
//         "hours.close": "wed 2:00 pm"
//       },
//       {
//         "hours.open": "thu 10:00 am",
//         "hours.close": "thu 2:00 pm"
//       },
//       {
//         "hours.open": "fri 10:00 am",
//         "hours.close": "fri 2:00 pm"
//       },
//       {
//         "hours.open": "sat 10:00 am",
//         "hours.close": "sat 2:00 pm"
//       }
//     ]
//   }
// }
// We want to experiment with improving the performance of fetching open stores by converting the operating hours into encoded tokens.
// These would be of fixed length of five.

// The first digit would represent the day, Monday maps to number 1, Tuesday to 2 and so on.
// The next four digits would represent the hours in 24 hours format.

// Examples:

// Monday, 10:00am transforms to 11000
// Monday, 2:00pm transforms to 11400 (as 2pm -> 14:00 in 24 hr format)

// Write a function that takes in a start_time and end_time and returns a list of all encoded tokens of in that range at 5 minute intervals.
// Ensure you validate the input.

// Ex: Input: ("mon 10:00 am", "mon 11:00 am")
// Output: [“11000”, “11005”, “11010”, “11015”, “11020”, “11025”, “11030”, “11035”, “11040”, “11045”, “11050”, “11055”, “11100”]

// 72305 - 7: sunday 2305 11:05 pm -> (sunday 2305 11:05 pm -> sunday 2305 11:10 pm)

// 22107  tuesday

// assumptions: 1. all the input is valid 2. not cross-day

// Input: ("mon 10:15 am", "mon 11:00 am")
// Output: [“11015”, “11020”, “11025”, “11030”, “11035”, “11040”, “11045”, “11050”, “11055”, “11100”]

// Ex: Input: ("mon 10:00 am", "tue 11:00 am")

class Solution {
    private static final Map<String, Integer> DAYS = Map.of(
            "mon", 1,
            "tue", 2,
            "wed", 3,
            "thu", 4,
            "fri", 5,
            "sat", 6,
            "sun", 7
    );

    public static List<String> convertTimeToTokens(String start, String end) {
        int[] startTime = toToken(start);
        int[] endTime = toToken(end);

        System.out.println("start:" + Arrays.toString(startTime));
        System.out.println("end:" + Arrays.toString(endTime));

        ArrayList<String> result = new ArrayList<>();
        // 22107

        for (int day = startTime[0]; day <= endTime[0]; day++) {
            int hour = (day == startTime[0]) ? startTime[1] : 0;
            while (true) {
                if (day == endTime[0] && hour >= endTime[1] || hour >= 24) {
                    break;
                }
                int minute = (hour == startTime[1]) ? startTime[2] : 0;
                while (true) {
                    if ((hour == endTime[1] && minute >= endTime[2] && minute != 0) || (minute >= 60)) {
                        break;
                    }
                    result.add(String.format("%d%02d%02d", startTime[0], hour, minute));
                    minute += 5;
                }
                hour++;
            }
        }
        return result;
    }
    // 12:05 am
    // 12:33 pm
    private static int[] toToken(String input) {
        String[] parts = input.split(" ");
        if (parts == null || parts.length != 3) {
            throw new IllegalArgumentException("Bad time format");
        }

        int day = getDay(parts[0].toLowerCase());
        int [] time = getTime(parts[1]);
        int amPm = getAmPm(parts[2].toLowerCase());

        // 12 am/pm case
        if (time[0] == 12) {
            if (amPm == 0) { // 12am -> hour ->0
                time[0] = 0;
            }
        } else {
            time[0] += amPm;
        }

        return new int []{day, time[0], time[1]};
    }

    static private int getDay(String day) {
        if (!DAYS.containsKey(day)) {
            throw new IllegalArgumentException("Wrong day format");
        }
        return DAYS.get(day);
    }

    static private int getAmPm(String amPm) {
        if ("am".equalsIgnoreCase(amPm)) {
            return 0;
        }
        if ("pm".equalsIgnoreCase(amPm)) {
            return 12;
        }
        throw new IllegalArgumentException("Wrong AM/PM format");
    }

    static private int[] getTime(String time) {
        String [] timeParts = time.split(":");
        if (timeParts == null || timeParts.length != 2) {
            throw new IllegalArgumentException("Wrong time format");
        }

        int hour = Integer.valueOf(timeParts[0]);
        int minute = Integer.valueOf(timeParts[1]);

        if (hour < 0 || hour > 12 || minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Wrong time format");
        }

        return new int []{hour, minute};
    }

    public static void main(String[] args) {
        System.out.println(convertTimeToTokens("mon 10:15 am", "mon 11:00 am").toString());
        System.out.println(convertTimeToTokens("mon 9:05 am", "mon 11:00 am").toString());
        System.out.println(convertTimeToTokens("mon 10:15 am", "tue 11:00 am").toString());
    }
}

