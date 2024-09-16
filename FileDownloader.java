class FileDownloader implements Runnable{
    private String fileName;
    FileDownloader(String name){
        this.fileName = name;
    }
    public void run(){
        System.out.println("File ("+this.fileName+") is Downloading...");
        try{
            Thread.sleep(3000);
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("\033[0;32m" +  this.fileName+" Download Dompleted!!!" +  "\033[0m");
    }
}

class Downloader{
    public static void main(String[] args) {
        Thread t1 = new Thread(new FileDownloader("publishing_data.zip"));
        Thread t2 = new Thread(new FileDownloader("undefined_cppp.zip"));
        Thread t3 = new Thread(new FileDownloader("helloworld_data_large.zip"));

        t1.start();
        t2.start();
        t3.start();
    }
}