package model;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.LyricsHandler;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;


public class Catologizer {

    /**
     * @param args
     */
//
//
//    private static void getDublicatesList(ArrayList<String> data) {
//        Set<String> founderStrings = new HashSet<String>();
//        Set<String> dublicates = new HashSet<String>();
//
//        // ArrayList <DublicatList> dublicatLists = new D
//
//        for (String str : data) {
//            if (founderStrings.contains(str)) {
//                dublicates.add(str);
//            } else {
//                founderStrings.add(str);
//            }
//        }
//        System.out.println(dublicates);
//        System.out.println("size dubl :" + dublicates.size());
//
//
//    }


    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //ВВод из командной строки
        //String path = args[0];

        String path = "d:\\MUSIC\\2016";
        ArrayList<Artist> artists = new ArrayList<Artist>();// хранение всех артистов
        Artist artist;
        Album album;
        Song song;
        String md5;

        //ArrayList<String> controlSum = new ArrayList<String>();
        ArrayList<String> dataFiles = new ArrayList<>();

        HashMap <String, ArrayList<String> > controlSum = new HashMap<>();
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


        for (File file : listOfFiles) {
            String fileLocation = file.toString();
            System.out.println("fL : "+ fileLocation);

            try {
                InputStream input = new FileInputStream(new File(fileLocation));
//                  Получение контрольной суммы
                md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(input);
                if (controlSum.isEmpty()){
                    controlSum.put(md5,new ArrayList<String>());
                    controlSum.get(md5).add(fileLocation);
                }else if (controlSum.containsKey(md5)){
                    controlSum.get(md5).add(fileLocation);
                    // controlSum.put(md5,controlSum.get(md5).add(fileLocation))
                }else{
                    controlSum.put(md5,new ArrayList<String>());
                    controlSum.get(md5).add(fileLocation);
                }

                //detecting the file type
//                BodyContentHandler handler = new BodyContentHandler();
//                Metadata metadata = new Metadata();
//                FileInputStream inputstream = new FileInputStream(new File(fileLocation));
//                ParseContext pcontext = new ParseContext();
//
//                //Mp3 parser
//                Mp3Parser  Mp3Parser = new  Mp3Parser();
//                Mp3Parser.parse(inputstream, handler, metadata, pcontext);
//                LyricsHandler lyrics = new LyricsHandler(inputstream,handler);
//
//                while(lyrics.hasLyrics()) {
//                    System.out.println(lyrics.toString());
//                }
//
//                System.out.println("Contents of the document:" + handler.toString());
//                System.out.println("Metadata of the document:");
//                String[] metadataNames = metadata.names();
//
//                for(String name : metadataNames) {
//                    System.out.println(name + ": " + metadata.get(name));
//                }
//                System.out.println("----------------------------------------------");
//                System.out.println("Title: " + metadata.get("title"));
//                System.out.println("Artists: " + metadata.get("xmpDM:artist"));
//                System.out.println("Composer : "+metadata.get("xmpDM:composer"));
//                System.out.println("Genre : "+metadata.get("xmpDM:genre"));
//                System.out.println("Album : "+metadata.get("xmpDM:album"));

                ContentHandler handler = new DefaultHandler();
                Metadata metadata = new Metadata();
                FileInputStream inputstream = new FileInputStream(new File(fileLocation));
                Parser parser = new Mp3Parser();
                ParseContext parseCtx = new ParseContext();
                parser.parse(inputstream, handler, metadata, parseCtx);
                input.close();

                song = new Song(file.getName(), metadata.get("xmpDM:duration"), file.getAbsolutePath());
                album = new Album(metadata.get("xmpDM:album"));
                artist = new Artist(metadata.get("xmpDM:artist"));
                System.out.println("art " + artist);
                System.out.println("song" + song);
                System.out.println("alb" + album);



                if (artists.isEmpty()) {
                    album.addSongs(song);
                    //System.out.println("alb"+ album);
                    artist.addAlbum(album);
                    artists.add(artist);
                } else {
                    for (Artist artist1 : artists) {
                        if (artist1.getName().equals(artist.getName())) {
                            //     проверка на наличие альбома
                            for (Album album1 : artist1.getAlbums()) {
                                if (album1.getNameAlbum().equals(album.getNameAlbum())) {
                                    album1.addSongs(song);
                                } else {
                                    album.addSongs(song);
                                    artist1.addAlbum(album);
                                }
                            }
                        }else {
                            album.addSongs(song);
                            artist.addAlbum(album);
                        }
                    }
                    artists.add(artist);
                    //System.out.println(artist);
                }

                //String dataFile = metadata.get("xmpDM:artist") + metadata.get("xmpDM:album") + metadata.get("title");
                //dataFiles.add(dataFile);
//  заполняем hashMap
                //list1.put(md5, fileLocation);
                // list2.put(dataFile, fileLocation);


//                            for(String name : metadataNames){
//                                System.out.println(name + ": " + metadata.get(name));
//                            }

                // Retrieve the necessary info from metadata
                // Names - title, xmpDM:artist etc. - mentioned below may differ based


//                            line2 += "Artists: " + metadata.get("xmpDM:artist") + "/br" +
//                                    "\t\t\n" + "Title: " + metadata.get("title") +
//
//                                    "model.Album : " + metadata.get("xmpDM:album") + listOfFiles[i].getAbsolutePath() + "/br";

                //System.out.println(line1+line3);



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
                String line2 = "\t\t<h3>\n" + "Album : " + alb.getNameAlbum() + "</h3>\n";
                for (Song sg : alb.getSongs()) {
                    String line3 = "\t\t<p>\n" + sg.getNameSong() +" " +sg.getTime() +" (" + sg.getPath() + " )" + "</p>\n";
                    line2 = line2.concat(line3);
                }
                line1 = line1.concat(line2);
            }
            lineFirtht = lineFirtht.concat(line1);
        }

        writer.write(lineFirtht +lineEnd);
        writer.flush();
        writer.close();
        System.out.println(controlSum);
        // getDublicatesList(controlSum);

        //getDublicatesList(dataFiles);
    }
}
