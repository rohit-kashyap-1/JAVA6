 class MyRunnable implements Runnable  {
    public void run(){
        for(int i=1;i<=5;i++){
            System.out.println("Seperate Thread is running + "+Thread.currentThread().getName());
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println(e);
            }
            
        }
    }
}

class RunnableExample{
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());
        t1.start();
        Thread t2 = new Thread(new MyRunnable());
        t2.start();
    }
}