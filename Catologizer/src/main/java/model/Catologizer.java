package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Catologizer {

    /**
     * @param args
     */

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //ВВод из командной строки
        String path = null;
        try {
            path = args[0];
        } catch (Exception e) {
            System.out.println("Enter correct filepath");
            System.exit(0);
        }

//        path = "d:\\MUSIC\\2016";
        ArrayList<Artist> artists = new ArrayList<Artist>();// хранение всех артистов
        Artist newArtist;
        Album newAlbum;
        Song newSong;
        String md5;

        //ArrayList<String> controlSum = new ArrayList<String>();
        ArrayList<String> dataFiles = new ArrayList<>();

        HashMap <String, ArrayList<String> > checkSum = new HashMap<>();
        //HashMap<String, String> list2 = new HashMap<String, String>();
        String lineFirtht = "<!DOCTYPE html>" + "<html>\n" +
                "\t<head>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<title>  </title>\n" +
                "\t</head>\n" +
                "\t<body bgcolor='#F0FFFF'> \n" +
                "\t\t<p>\n";
        String lineEnd = "\t\t</p>\n" + "\t</body>\n" + "</html>";

        Mp3FileReader fileHelper = new Mp3FileReader();
        fileHelper.generareListOfFile(path);
        ArrayList<File> listOfFiles = fileHelper.getFiles();// получаем список файлов с расширением mp3//


        ArrayList<File> duplicates = new ArrayList<>();


        for (File file : listOfFiles) {
            String fileLocation = file.toString();
            System.out.println("fL : "+ fileLocation);

            try {
                InputStream input = new FileInputStream(new File(fileLocation));

                md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(input);
                if (checkSum.containsKey(md5)) {
                    checkSum.get(md5).add(fileLocation);
                    // controlSum.put(md5,controlSum.get(md5).add(fileLocation))
                } else {
                    checkSum.put(md5, new ArrayList<String>());
                    checkSum.get(md5).add(fileLocation);
                }

                ContentHandler handler = new DefaultHandler();
                Metadata metadata = new Metadata();
                FileInputStream inputstream = new FileInputStream(new File(fileLocation));
                Parser parser = new Mp3Parser();
                ParseContext parseCtx = new ParseContext();
                parser.parse(inputstream, handler, metadata, parseCtx);
                input.close();

                newSong = new Song(file.getName(), metadata.get("xmpDM:duration"), file.getAbsolutePath());
                newAlbum = new Album(metadata.get("xmpDM:album"));
                newArtist = new Artist(metadata.get("xmpDM:artist"));

                boolean artistExist = false;
                boolean albumExist = false;
//                boolean songExist = false;
                for (Artist artist : artists) {
                    System.out.println("Compare artist:" + artist.getName() + " . with artist:" + newArtist.getName() + ".");
                    if (artist.getName().equals(newArtist.getName())) {
                        System.out.println("equals = true");
                        artistExist = true;
                        for (Album album : artist.getAlbums()) {
                            System.out.println("Compare album:" + album.getName() + " . with album:" + newAlbum.getName() + ".");
                            if (album.getName().equals(newAlbum.getName())) {
                                System.out.println("equals = true");
                                albumExist = true;
                                for (Song song: album.getSongs()) {
                                    System.out.println("Compare song:" + song.getName() + " . with song:" + newSong.getName() + ".");
                                    if (song.getName().equals(newSong.getName())) {
                                        System.out.println("equals = true");
                                        duplicates.add(file);
                                        duplicates.add(new File(song.getPath()));
                                        break;
                                    }
                                }
                                album.addSongs(newSong);
                                break;
                            }
                        }
                        if (!albumExist) {
                            newAlbum.addSongs(newSong);
                            artist.addAlbum(newAlbum);
                        }
                        break;
                    }
                }
                if (!artistExist) {
                    newAlbum.addSongs(newSong);
                    newArtist.addAlbum(newAlbum);
                    artists.add(newArtist);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (TikaException e) {
                e.printStackTrace();
            }
        }
        System.out.println(artists);

        // запись в файл
        PrintWriter writer = new PrintWriter("file.html", "UTF-8");
        for( Artist art : artists) {
            String line1 = "\t\t<h2>\n" + "Artist : " + art.getName() + "</h2>\n";

            for (Album alb : art.getAlbums()) {
                String line2 = "\t\t<h3>\n" + "Album : " + alb.getName() + "</h3>\n";
                for (Song sg : alb.getSongs()) {
                    String line3 = "\t\t<p>\n" + sg.getName() +" " +sg.getTime() +" (" + sg.getPath() + " )" + "</p>\n";
                    line2 = line2.concat(line3);
                }
                line1 = line1.concat(line2);
            }
            lineFirtht = lineFirtht.concat(line1);
        }

        writer.write(lineFirtht +lineEnd);
        writer.flush();
        writer.close();


//        for(File file: duplicates) {
//            System.out.println(file);
//        }
// TODO: complete logger
//        Logger logger = LogManager.getRootLogger();
        System.out.println(checkSum);
    }
}
