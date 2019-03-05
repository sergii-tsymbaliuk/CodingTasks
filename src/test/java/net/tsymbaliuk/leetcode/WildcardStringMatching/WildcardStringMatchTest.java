package net.tsymbaliuk.leetcode.WildcardStringMatching;

import static org.junit.Assert.*;

import static net.tsymbaliuk.leetcode.WildcardStringMatching.WildcardStringMatch.match;

import org.junit.Test;

public class WildcardStringMatchTest {

  @Test
  public void match_testThatMatch() {
    assertTrue(match("",""));
    assertTrue(match("test","test"));
    assertTrue(match("","*"));
    assertTrue(match("test","*"));
    assertTrue(match("test","t*s*"));
    assertTrue(match("test","t*"));
    assertTrue(match("test","te.t"));
    assertTrue(match("test","te*."));
    assertTrue(match("test","te*st"));
  }

  @Test
  public void match_testThatNotMatch() {
    assertFalse(match("test",""));
    assertFalse(match("","a"));
    assertFalse(match("test","a"));
    assertFalse(match("test","t*td"));
    assertFalse(match("test","t*z"));
  }
}