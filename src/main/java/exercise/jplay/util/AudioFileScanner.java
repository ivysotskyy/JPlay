package exercise.jplay.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public abstract class AudioFileScanner {

    private static final ArrayList<String> ALLOWED_EXTENSIONS = new ArrayList<>() {
        {
            add("mp3");
            //add("wav");
        }
    };

    public static List<Path> findFiles(List<Path> directories) throws IOException {
        final List<Path> files = new ArrayList<>();
        for (Path dir : directories) {
            if (isMusicFile(dir)) {
                files.add(dir);
            } else {
                List<Path> musicFiles = findFiles(dir);
                files.addAll(musicFiles);
            }
        }
        return files;
    }

    private static List<Path> findFiles(final Path directory) throws IOException, SecurityException {
        final List<Path> files = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            stream.forEach(path -> {
                if (isMusicFile(path)) {
                    files.add(path);
                }
            });
        }
        return files;
    }

    protected static boolean isMusicFile(Path file) {
        if (Files.isDirectory(file)) return false;
        String[] temp = file.getFileName()
                            .toString()
                            .split("[.]");
        String fileExtension = temp[temp.length - 1];
        return ALLOWED_EXTENSIONS.contains(fileExtension);
    }

}
