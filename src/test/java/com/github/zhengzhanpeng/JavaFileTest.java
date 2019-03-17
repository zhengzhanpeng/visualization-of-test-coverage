package com.github.zhengzhanpeng;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JavaFileTest {

    private static final String FILE_PATH = "classpath:TestFile.java";
    private final JavaFile javaFile = new JavaFile();

    @Test
    void should_return_line_list_given_java_file() throws IOException {
        final List<String> lineList = javaFile.getLineListBy(ResourceUtils.getFile(FILE_PATH));
        assertEquals(13, lineList.size());
        assertEquals("package com.github.zhengzhanpeng;", lineList.get(0));
        assertEquals("", lineList.get(1));
        assertEquals("import org.junit.Test;", lineList.get(2));
    }

    @Test
    void should_add_test_coverage_annotation_given_java_file() throws IOException {
        final List<String> lineList = Arrays.asList(
                "package com.github.zhengzhanpeng;",
                "",
                "import java.io.IOException;",
                "import java.util.List;",
                "",
                "public class JavaFileTest {"
        );

        List<String> results = javaFile.generateLineListWhichContainsTestCoverage(lineList, 78);
        assertEquals("package com.github.zhengzhanpeng;", results.get(0));
        assertEquals("", results.get(1));
        assertEquals("import java.io.IOException;", results.get(2));
        assertEquals("import java.util.List;", results.get(3));
        assertEquals("", results.get(4));
        assertEquals("@TestCoverage(78)", results.get(5));
        assertEquals("public class JavaFileTest {", results.get(6));
    }
}