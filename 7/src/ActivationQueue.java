import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ActivationQueue {
    private Queue<IMethodRequest> activationQueue;

    public ActivationQueue(){
        activationQueue = new ConcurrentLinkedQueue<>();
    }

    public void enqueue(IMethodRequest request) {
        activationQueue.add(request);
    }

    public IMethodRequest poll(){
        return activationQueue.poll();
    }
}
