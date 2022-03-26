package com.example.bootdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class HideUserGradeFilterTest {
  @Test
  public void ex1() {
    Pattern pattern = Pattern.compile("(\"(gradeName|epid|gradeCode)\":\")(.*?)(\")");
    String input = "{\"gradeCode\":\"001\",\"gradeName\":\"회장\",\"name\":\"박은용\",\"epid\":\"34234324000\",\"id\":200}";
    Matcher matcher = pattern.matcher(input);
    while (matcher.find()) {
      log.info("Match: {}, Start: {}, End: {}", matcher.group(0), matcher.start(), matcher.end());
      String matchString = matcher.group(0);
      String replaceString = String.format("%s*******%s", matcher.group(1), matcher.group(4));
      log.info("matchString: {}, replaceString: {}", matchString, replaceString);
      input = input.replace(matchString, replaceString);
      log.info("input: {}", input);
    }
  }
}