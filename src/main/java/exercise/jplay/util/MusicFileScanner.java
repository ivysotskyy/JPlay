package exercise.jplay.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public abstract class MusicFileScanner {

    private static final ArrayList<String> ALLOWED_EXTENSIONS = new ArrayList<>() {
        {
            add("mp3");
        }
    };

    public static List<Path> findFiles(List<Path> directories) {
        final List<Path> files = new ArrayList<>();
        directories.forEach(dir -> {
            if (Files.isDirectory(dir))
                files.add(dir.normalize());
            else if (Files.isRegularFile(dir)) {
                try {
                    files.addAll(findFiles(dir));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return files;
    }

    protected static List<Path> findFiles(final Path directory) throws IOException {
        final List<Path> files = new ArrayList<>();
        try(Stream<Path> paths = Files.walk(directory)) {
            return paths.filter(MusicFileScanner::isMusicFile).toList();
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected static boolean isMusicFile(Path file) {
        String fileExtension = file.getFileName().toString().split("\\.")[0];
        return ALLOWED_EXTENSIONS.contains(fileExtension);
    }

}
