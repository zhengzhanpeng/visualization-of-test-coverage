package com.github.zhengzhanpeng;

import com.github.zhengzhanpeng.annotation.TestCoverage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JavaFile {

    private static final String WORKING_PATH = System.getProperty("user.dir") + "/src/main/java/";

    public void updateTestCoverage(String relativePath, int coverageValue) throws IOException {
        final File file = new File(WORKING_PATH + relativePath);
        final List<String> lineList = getLineListBy(file);
        final List<String> finalList = generateLineListWhichContainsTestCoverage(lineList, coverageValue);
        FileUtils.writeLines(file, finalList);
    }

    List<String> getLineListBy(File file) throws IOException {
        if (!file.exists()) {
            return Collections.emptyList();
        }
        return FileUtils.readLines(file, "UTF-8");
    }

    List<String> generateLineListWhichContainsTestCoverage(List<String> lineList, int coverageValue) {
        List<String> copyTargetList = lineList.stream()
                                              .collect(Collectors.toList());
        final boolean isImportPackage = copyTargetList.stream()
                                                      .anyMatch(line -> line.contains(TestCoverage.class.getName()));
        if (!isImportPackage) {
            copyTargetList.add(2, String.format("import %s;", TestCoverage.class.getName()));
        }

        final int publicClassLineIndex = copyTargetList.stream()
                                                       .filter(line -> line.contains("class"))
                                                       .mapToInt(line -> copyTargetList.indexOf(line))
                                                       .findFirst()
                                                       .orElseThrow();
        copyTargetList.add(publicClassLineIndex, String.format("@TestCoverage(%s)", coverageValue));
        return copyTargetList;
    }
}
