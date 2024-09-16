class MyThread extends Thread{
    public void run(){
        for(int i=1;i<=5;i++){
            System.out.println(i + " - Running in a separate thread: " + Thread.currentThread().getName());
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
}

public class MainExample {
    public static void main(String args[]){
        MyThread mythread1 = new MyThread();
        mythread1.start();

        MyThread myThread2 = new MyThread();
        myThread2.start();
    }
}