package net.tsymbaliuk.woven;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.*;

public class MonthlyChargeTests {
    Subscription plan = new Subscription(1, 1, 5000);

    User[] users = {
            new User(1, "Employee #1", LocalDate.of(2019, 1, 1), null, 1),
            new User(2, "Employee #2", LocalDate.of(2019, 1, 1), null, 1)
    };

    @Test
    public void worksWhenNoUsersAreActive() {
        assertEquals(0, Challenge.monthlyCharge("2018-10", plan, users));
    }

    @Test
    public void worksWhenTheActiveUsersAreActiveTheEntireMonth() {
        float expectedUserCount = 2;
        assertEquals(expectedUserCount * 5000, Challenge.monthlyCharge("2020-12", plan, users), 1);
    }


    @Test
    public void worksWhenTheNoActiveUsers() {
        User[] testUsers = {
                new User(1, "Terminated before", LocalDate.of(2020, 11, 1), LocalDate.of(2020, 11, 30), 1),
                new User(2, "Activated after", LocalDate.of(2021, 1, 1), null, 1)
        };
        assertEquals(0, Challenge.monthlyCharge("2020-12", plan, testUsers));
    }


    @Test
    public void worksWhenOnMonthStart() {
        User[] testUsers = {
                new User(1, "Terminated first day", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 12, 1), 1)
        };
        assertEquals(5000.0/31.0, Challenge.monthlyCharge("2020-12", plan, testUsers), 1);
    }

    @Test
    public void worksWhenOnMonthEnd() {
        User[] testUsers = {
                new User(2, "Activated last day", LocalDate.of(2020, 12, 31), null, 1)
        };
        assertEquals((int)(5000.0/31.0), Challenge.monthlyCharge("2020-12", plan, testUsers));
    }

    @Test
    public void worksNeverActivatedAndNegative() {
        User[] testUsers = {
                new User(2, "Activated last day", null, LocalDate.of(2020, 12, 31), 1),
                new User(2, "Activated last day", LocalDate.of(2020, 12, 31), LocalDate.of(2020, 12, 1), 1),
        };
        assertEquals(0, Challenge.monthlyCharge("2020-12", plan, testUsers));
    }
}