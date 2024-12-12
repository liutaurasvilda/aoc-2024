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
                do {
                    defragmentedFilesystem.removeLast();
                } while (defragmentedFilesystem.getLast().equals("."));
            }
        }
        return defragmentedFilesystem;
    }

    private static List<String> defragmentFileBased(List<String> filesystem) {
        var defragmentedFilesystem = new ArrayList<>(filesystem);
        for (int i = defragmentedFilesystem.size()-1; i > 0; i--) {
            var value = defragmentedFilesystem.get(i);
            if (value.equals(".")) {
                continue;
            }

            var indicesFrom = new ArrayList<Integer>();
            for (int j = i; j > 0 && defragmentedFilesystem.get(j).equals(value); j--) {
                indicesFrom.add(j);
            }

            var indicesTo = new ArrayList<Integer>();
            for (int k = 0; k < defragmentedFilesystem.size() && k <= indicesFrom.getLast(); k++) {
                if (defragmentedFilesystem.get(k).equals(".")) {
                    indicesTo.add(k);
                } else if (!indicesTo.isEmpty()) {
                    if (indicesTo.size() >= indicesFrom.size()) {
                        break;
                    } else {
                        indicesTo.clear();
                    }
                }
            }

            if (!indicesTo.isEmpty()) {
                var tempIndicesFrom = new ArrayList<>(indicesFrom);
                for (int to : indicesTo) {
                    if (!tempIndicesFrom.isEmpty()) {
                        int from = tempIndicesFrom.getFirst();
                        tempIndicesFrom.removeFirst();
                        defragmentedFilesystem.set(to, defragmentedFilesystem.get(from));
                        defragmentedFilesystem.set(from, ".");
                    }
                }
            }
            i = indicesFrom.getLast();
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
