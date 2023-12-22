package net.tsymbaliuk.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DesignInMemoryFileSystem {



    /**
     * Your FileSystem object will be instantiated and called as such:
     * FileSystem obj = new FileSystem();
     * List<String> param_1 = obj.ls(path);
     * obj.mkdir(path);
     * obj.addContentToFile(filePath,content);
     * String param_4 = obj.readContentFromFile(filePath);
     */
    static class FileSystem {
        static class FileNotFoundException extends Exception {
            public FileNotFoundException(String message) {
                super(message);
            }
        }

        static class Directory {
            Map<String, Directory> subdirs = new HashMap<>();
            Map<String, StringBuilder> files = new HashMap<>();
        }

        Directory root;

        public FileSystem() {
            root = new Directory();
            root.subdirs.put("", new Directory());
        }

        public List<String> ls(String path) {
            if (path == null || path.length() == 0 || path.charAt(0) != '/') {
                return null;
            }
            try {
                if (path == "/") {
                    return lsDir(root.subdirs.get(""));
                }
                Directory parent = getParentDir(path);
                String fileName = getName(path);
                if (parent.subdirs.containsKey(fileName)) {
                    return lsDir(parent.subdirs.get(fileName));
                } else if (parent.files.containsKey(fileName)) {
                    return List.of(fileName);
                }
            } catch (FileNotFoundException | IllegalArgumentException exception){
                System.err.println(String.format("Cannot read dir %s due to %s", path, exception));
            }
            return List.of();
        }

        private List<String> lsDir(Directory dir) {
            List<String> list = new ArrayList<>(dir.subdirs.keySet());
            list.addAll(dir.files.keySet());
            Collections.sort(list);
            return list;
        }

        public void mkdir(String path) {
            if (path == null || path.length() == 0 || path.charAt(0) != '/') return;
            String [] parts = path.split("/");
            Directory dir = root;
            for (String dirName : parts) {
                dir = dir.subdirs.computeIfAbsent(dirName, notUsed -> new Directory());
            }
        }

        public void addContentToFile(String filePath, String content) {
            try {
                Directory parent = getParentDir(filePath);
                parent.files.computeIfAbsent(getName(filePath), f -> new StringBuilder()).append(content);
            } catch (FileNotFoundException | IllegalArgumentException exception) {
                System.err.println(String.format("Cannot read file %s due to %s", filePath, exception));
            }
        }

        public String readContentFromFile(String filePath) {
            try {
                Directory parent = getParentDir(filePath);
                String fileName = getName(filePath);
                return parent.files.containsKey(fileName) ? parent.files.get(fileName).toString() : null;
            } catch (FileNotFoundException | IllegalArgumentException exception) {
                System.err.println(String.format("Cannot read file %s due to %s", filePath, exception));
            }
            return null;
        }

        private String getPath(String fullPath) {
            int p = fullPath.lastIndexOf('/');
            if (p < 0) {
                throw new IllegalArgumentException();
            }
            return fullPath.substring(0, p);
        }

        private String getName(String fullPath) {
            int p = fullPath.lastIndexOf('/');
            if (p < 0) {
                throw new IllegalArgumentException();
            }
            return fullPath.substring(p + 1);
        }

        private Directory getParentDir(String fullPath) throws FileNotFoundException {
            String[] path = getPath(fullPath).split("/");
            Directory dir = this.root;
            for (String dirName : path) {
                if (!dir.subdirs.containsKey(dirName)) {
                    throw new FileNotFoundException(fullPath);
                }
                dir = dir.subdirs.get(dirName);
            }
            return dir;
        }
    }


    @Test
    public void testCase() {
        // ["FileSystem","ls",  "mkdir",    "addContentToFile",     "ls",   "readContentFromFile"]
        // [[],          ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"],  ["/"],  ["/a/b/c/d"]]

        FileSystem fs = new FileSystem();
        assertEquals(0, fs.ls("/").size());

        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "hello");

        List<String> r = fs.ls("/");
        assertEquals("a", String.join(",", r));

        assertEquals("hello", fs.readContentFromFile("/a/b/c/d"));
    }

}
