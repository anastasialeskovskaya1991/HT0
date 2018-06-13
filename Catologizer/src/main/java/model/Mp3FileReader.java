package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Arrays;

public class Mp3FileReader {

    private File[] listOfFiles;
    private String path;
    private ArrayList<File> files = new ArrayList<File>();
//    private ArrayList<String> fileType = new ArrayList<>();
//    private ArrayList<String>  dateCreated = new ArrayList<>();
//    private  ArrayList<Long>  kilobytes = new ArrayList<>();
//    private int size;

    public Mp3FileReader() {
    }

    public void generareListOfFile(String path) {
        this.path = path;
        File folder = new File(path);

        if (folder.exists()) {
            processFilesFromFolder(folder);
        } else {
            System.out.println("Файла не существует");
        }
    }

    private void processFilesFromFolder(File folder) {
        String suffix = ".mp3";
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                processFilesFromFolder(entry);
                continue;
            }
            if (entry.toString().endsWith(suffix)) {

                files.add(entry);
                //System.out.println(" parse : "+ entry);
                //files.add(entry);
            }
        }
    }

    public ArrayList<File> getFiles() {
        //System.out.println(files);
        return files;
    }
}



//}

//    public void generateData() throws IOException {
//        for (int i = 0; i < listOfFiles.length; i++) {
//
//            BasicFileAttributes attr = Files.readAttributes(listOfFiles[i].toPath(), BasicFileAttributes.class);
//            FileTime date = attr.creationTime();
//            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//            dateCreated.add(df.format(date.toMillis()));
//
//            if (listOfFiles[i].isFile()) {
//                long bytes = listOfFiles[i].length();
//                long kb = bytes / 1024;
//                kilobytes.add(kb);
//                fileType.add("FILE");
//                // System.out.println("File!");
//
//            } else if (listOfFiles[i].isDirectory()) {
//                kilobytes.add( (getDirSize(listOfFiles[i])/1024));
//                fileType.add("DIR");
//
//            }
//        }
//    }
// Получение размера каталога в байтах
//    long getDirSize(File dir) {
//        long size = 0;
//        if (dir.isFile()) {
//            size = dir.length();
//        } else {
//            File[] subFiles = dir.listFiles();
//            for (File file : subFiles) {
//                if (file.isFile()) {
//                    size += file.length();
//                } else {
//                    size += getDirSize(file);
//                }
//            }
//        }
//        return size;
////    }
//    public void fileWriterToHtml() throws IOException {
//
////        FileWriter writer = new FileWriter(pathForHtmlFile);
//        PrintWriter writer = new PrintWriter("UTF-8");
//        String line1 = "<!DOCTYPE html>"+"<html>\n" +
//                "\t<head>\n" +
//                "\t<meta charset=\"utf-8\">\n" +
//                "\t<title>  </title>\n" +
//                "\t<style type=\"text/css\">\n" +
//                "    table {\n" +
//                "    border-collapse: collapse; \n" +
//                "   }\n" +
//                "  \n" +
//                "  </style>\n" +
//                "\t</head>\n" +
//                "\t<body bgcolor='#F0FFFF'> \n" +
//                "\t\t\n" +
//                "\t\t<p><table border=\"1\">\n" +
//                "\t\t<tr>\n" +
//                "\t\t\t<th>ИМЯ</th>\n" +
//                "\t\t\t<th>ТИП</th>\n" +
//                "\t\t\t<th>ДАТА СОЗДАНИЯ</th>\n" +
//                "\t\t\t<th>РАЗМЕР (В КВ)</th>\n" +
//                "\t\t</tr>\n";
//
////        for(int i = 0; i<size; i++){
////            String line2 = "\t\t<tr>\n" + "\t\t\t<td>" + listOfFiles[i].getNameAlbum() + "</td>\n" +
////                    "\t\t\t<td>" + fileType.get(i) + "</td>\n" +
////                    "\t\t\t<td>"+dateCreated.get(i)+"</td>\n" +
////                    "\t\t\t<td>"+kilobytes.get(i)+"</td>\n" +  "\t\t</tr>\n";
////            line1 = line1.concat(line2);
////        }
//        //System.out.println(line1);
//        String line3 = "\t\t</table>\n" + "\t\t</p>\n" + "\t</body>\n" + "</html>";
//        //System.out.println(line1+line3);
//        writer.write(line1+line3);
//        writer.flush();
//        writer.close();
//        System.out.println("Файл записан");
//    }
//}
//
//













