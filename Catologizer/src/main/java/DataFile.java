public class DataFile {


        private String artist;
        private String album;
        private String title;

        public DataFile(String artist, String album, String title) {
            this.artist = artist;
            this.album = album;
            this.title = title;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "DataFile{" +
                    "artist='" + artist + '\'' +
                    ", album='" + album + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }


