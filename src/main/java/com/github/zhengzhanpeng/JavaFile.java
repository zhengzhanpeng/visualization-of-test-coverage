package com.github.zhengzhanpeng;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JavaFile {

    public List<String> getLineList(String path) throws IOException {
        final File file = ResourceUtils.getFile(path);
        if (!file.exists()) {
            return Collections.emptyList();
        }
        return FileUtils.readLines(file, "UTF-8");
    }
}
