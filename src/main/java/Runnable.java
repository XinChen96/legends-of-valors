// a object that can be run as round-base interaction
public interface Runnable {
    boolean isRunning();
    void setRunning(boolean running);
    void run();
}
