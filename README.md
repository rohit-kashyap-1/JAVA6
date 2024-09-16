# JAVA Threads

In Java, **threads** represent a way to run multiple tasks concurrently within a program. A **thread** is essentially a lightweight subprocess, the smallest unit of processing that allows Java applications to perform multiple tasks simultaneously. This concept is useful when you want to execute time-consuming operations, such as fetching data from a database or running complex calculations, without blocking other tasks.

### Real-Life Examples of Threads in Java:

### 1. **Multitasking in a Web Browser**

Consider a web browser that can perform multiple tasks at once:

- Loading a web page.
- Downloading files.
- Running JavaScript on the page.
- Streaming media content.

Each of these tasks can be performed on a **separate thread** so that one doesn't block the others. For example, you can still scroll and interact with the page while it loads, or download a file while browsing other websites.

### 2. **ATM Machine Simulation**

Imagine an ATM machine where multiple people can perform transactions like withdrawing or depositing money at the same time. Each transaction is handled by a separate thread so that one user’s withdrawal or deposit does not affect another user’s transaction.

### 3. **Gaming Applications**

In a game, multiple actions are happening at the same time:

- The game logic (enemy movements, player actions).
- Background music and sound effects.
- Displaying graphics and animations.

All these tasks are handled by separate threads, ensuring smooth gameplay without delays or freezing.

### When to Use Threads in Java:

Threads are useful in the following scenarios:

1. **Parallel Processing**: When you want to perform tasks in parallel, like downloading multiple files or processing multiple transactions.
2. **Long-running Operations**: Threads can be used to offload long-running tasks like reading large files, making network requests, or performing CPU-intensive computations to prevent blocking the main application.
3. **Real-time Updates**: When you need real-time updates, such as in a chat application, stock price updates, or live video feeds, threads can be used to continuously check for new data without stopping other tasks.
4. **Responsive Applications**: In GUI applications (like desktop software or mobile apps), threads are used to keep the UI responsive while performing background tasks, like loading data.

```java
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
```

A better approach is to implement the `Runnable` interface, which separates the task (the code you want to run) from the thread itself. It’s more flexible and is often preferred in Java.

```java
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
```

### Example in detail

```java
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
```

### Key Thread Methods:

- `start()`: Starts the thread, calling the `run()` method.
- `run()`: Contains the code to be executed by the thread.
- `sleep(long millis)`: Pauses the thread for a specified time.
- `join()`: Waits for a thread to finish before continuing execution.
- `interrupt()`: Interrupts the thread.

The `join()` method in Java allows one thread to **wait for the completion of another thread**. This is particularly useful when you want to ensure that a certain task is finished before proceeding with further code execution.

```java
thread.join();  // Waits indefinitely until the thread finishes
thread.join(milliseconds);  // Waits for the specified milliseconds for the thread to finish

```

```java
class MyThread extends Thread {
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Thread: " + Thread.currentThread().getName() + " running iteration " + i);
            try {
                Thread.sleep(1000);  // Simulate some work with a 1-second delay
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class JoinExample {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();

        thread1.start();
        thread2.start();

        try {
            // Main thread waits for thread1 to finish before continuing
            thread1.join();
            System.out.println("Thread1 has finished. Now continuing...");
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        // Main thread does not wait for thread2, so this line may execute before thread2 finishes
        System.out.println("Main thread execution complete.");
    }
}

```

```java
Thread: Thread-0 running iteration 1
Thread: Thread-1 running iteration 1
Thread: Thread-0 running iteration 2
Thread: Thread-1 running iteration 2
Thread: Thread-0 running iteration 3
Thread: Thread-1 running iteration 3
Thread1 has finished. Now continuing...
Main thread execution complete.

```

The `interrupt()` method is used to **interrupt a thread** that is either sleeping, waiting, or blocked. When you interrupt a thread, it doesn't necessarily stop it immediately, but it sets the thread's **interrupted status** to `true`.

- **`join()`**:
    - Allows one thread to wait for another to finish.
    - Useful when you need to ensure tasks are completed in a specific order or one task depends on another.
- **`interrupt()`**:
    - Signals a thread that it should stop or respond to an interruption.
    - If the thread is in a blocked state, it throws an `InterruptedException`.
    - Allows a thread to be stopped gracefully rather than being forcefully terminated.
