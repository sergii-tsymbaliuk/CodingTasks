package net.tsymbaliuk.meta;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CentaurusFriends {
    /* """
    A group of centaurs (mythical half-human, half-horse creatures) all sign
    up for Facebook accounts at the same time. They immediately start
    sending each other friend requests, in accordance with the ancient rules
    that have governed centaur friendship since the dawn of time:

    1) A centaur will only send a friend request to another centaur if the
    recipient is at least (X/2 + 7) of the sender's age. For example, a 200-year
    old centaur can only send friend requests to centaurs that are at least 107
    years old.
    2) A centaur will not send a friend request to another centaur that is older
    than it is.
    3) A centaur over 100 years old will not send a friend request to a recipient
    under 100 years old. But centaurs under 100 years old can friend each other.
    4) If any of the conditions for sending a friend request are not met, no
    friend request will be sent.

    Write a function that, given an array of centaur ages, returns an integer
    of the total number of friend requests that the group of centaurs will send
    to each other.

    Examples:
    count_all_friend_requests([120, 110])  => 1
    # Friend requests          1    0
    count_all_friend_requests([120, 110, 99]) => 1
    # Friend requests          1    0    0
    count_all_friend_requests([120, 45, 230, 400, 88, 300, 101]) => 4
    # Friend requests          1    0   0    2    0   1    0
    count_all_friend_requests([120, 45, 55, 230, 400, 88, 300, 101]) => 6
    # Friend requests          1    0   1   0    2    1   1    0

    """ */
    public class Solution {
        public int countFriends(int[] centaurEages) {
            Arrays.sort(centaurEages);
            int friends = 0;
            int left = 0;
            for (int i = 1; i < centaurEages.length; i++) {
                while (left <= i && (
                        centaurEages[left] < (centaurEages[i] / 2 + 7)
                                || centaurEages[i] > 100 && centaurEages[left] <= 100)) {
                    left++;
                }

                friends += (i - left); //+= 1 - 0
            }
            return friends;
        }
    }


    @Test
    public void testCases() {
        Solution s = new Solution();

        assertEquals(1, s.countFriends(new int[]{120, 110}),"case1");
        assertEquals(1, s.countFriends(new int[]{120, 110, 99}),"case2");
        assertEquals(4, s.countFriends(new int[]{120, 45, 230, 400, 88, 300, 101}),"case3");
        assertEquals(6, s.countFriends(new int[]{120, 45, 55, 230, 400, 88, 300, 101}),"case3");
    }
}
