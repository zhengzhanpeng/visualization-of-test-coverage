package com.github.zhengzhanpeng;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class JavaFileTest {
    @Test
    public void should_return_line_list_given_java_file() throws IOException {
        final JavaFile javaFile = new JavaFile();
        final List<String> lineList = javaFile.getLineList("classpath:TestFile.java");
        assertEquals(13, lineList.size());
        assertEquals("package com.github.zhengzhanpeng;", lineList.get(0));
    }
}