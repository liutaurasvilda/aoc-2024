package solutions.day09;

import java.util.ArrayList;
import java.util.List;

final class Day09 {

    public static long filesystemChecksum1(List<Integer> diskMap) {
        var filesystem = buildFilesystem(diskMap);
        var defragmentedFilesystem = defragmentBlockBased(filesystem);
        return calculateChecksum(defragmentedFilesystem);
    }

    public static long filesystemChecksum2(List<Integer> diskMap) {
        var filesystem = buildFilesystem(diskMap);
        var defragmentedFilesystem = defragmentFileBased(filesystem);
        return calculateChecksum(defragmentedFilesystem);
    }

    private static ArrayList<String> buildFilesystem(List<Integer> diskMap) {
        var filesystem = new ArrayList<String>();
        boolean isFileBlock = true;
        int fileId = 0;
        for (int e : diskMap) {
            if (isFileBlock) {
                for (int i = 0; i < e; i++) {
                    filesystem.add(String.valueOf(fileId));
                }
                isFileBlock = false;
                fileId++;
            } else {
                for (int i = 0; i < e; i++) {
                    filesystem.add(".");
                }
                isFileBlock = true;
            }
        }
        return filesystem;
    }

    private static List<String> defragmentBlockBased(List<String> filesystem) {
        var defragmentedFilesystem = new ArrayList<>(filesystem);
        for (int i = 0; i < defragmentedFilesystem.size(); i++) {
            if (defragmentedFilesystem.get(i).equals(".")) {
                defragmentedFilesystem.set(i, defragmentedFilesystem.getLast());
                defragmentedFilesystem.removeLast();
                while (defragmentedFilesystem.getLast().equals(".")) {
                    defragmentedFilesystem.removeLast();
                }
            }
        }
        return defragmentedFilesystem;
    }

    private static List<String> defragmentFileBased(List<String> filesystem) {
        var defragmentedFilesystem = new ArrayList<>(filesystem);
        for (int i = 0; i < defragmentedFilesystem.size(); i++) {
            if (defragmentedFilesystem.get(i).equals(".")) {
                defragmentedFilesystem.set(i, defragmentedFilesystem.getLast());
                defragmentedFilesystem.removeLast();
                while (defragmentedFilesystem.getLast().equals(".")) {
                    defragmentedFilesystem.removeLast();
                }
            }
        }
        return defragmentedFilesystem;
    }

    private static long calculateChecksum(List<String> filesystem) {
        var checksum = 0L;
        for (int i = 0; i < filesystem.size(); i++) {
            if (filesystem.get(i).equals(".")) {
                continue;
            }
            checksum += Long.parseLong(filesystem.get(i)) * i;
        }
        return checksum;
    }
}
