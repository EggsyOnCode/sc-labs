package org.example;
import java.io.File;
        import java.util.ArrayList;
        import java.util.List;

public class FileSearcher {

    // Method to search for the specified file in a directory and its subdirectories
    public static List<String> searchFile(File directory, String fileName, boolean caseSensitive) {
        if (directory == null) {
            throw new NullPointerException("Directory cannot be null");
        }
        if (fileName == null) {
            throw new NullPointerException("File name cannot be null");
        }
        List<String> foundPaths = new ArrayList<>();
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("The specified directory does not exist.");
            return foundPaths;
        }
        searchFileRecursive(directory, fileName, caseSensitive, foundPaths);
        return foundPaths;
    }

    // Recursive function to traverse the directory tree
    private static void searchFileRecursive(File directory, String fileName, boolean caseSensitive, List<String> foundPaths) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                searchFileRecursive(file, fileName, caseSensitive, foundPaths);
            } else {
                String currentFileName = caseSensitive ? file.getName() : file.getName().toLowerCase();
                String searchFileName = caseSensitive ? fileName : fileName.toLowerCase();
                if (currentFileName.equals(searchFileName)) {
                    foundPaths.add(file.getAbsolutePath());
                    System.out.println("File found: " + file.getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java FileSearcher <directory_path> <file_name> [case_sensitive]");
            return;
        }

        String directoryPath = args[0];
        String fileName = args[1];
        boolean caseSensitive = args.length > 2 && Boolean.parseBoolean(args[2]);

        File directory = new File(directoryPath);
        List<String> foundFiles = searchFile(directory, fileName, caseSensitive);

        if (foundFiles.isEmpty()) {
            System.out.println("File not found.");
        } else {
            System.out.println("Number of occurrences: " + foundFiles.size());
        }
    }
}
