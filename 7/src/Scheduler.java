public class Scheduler extends Thread {
    private ActivationQueue activationQueue;

    public Scheduler() {
        activationQueue = new ActivationQueue();
    }

    public void enqueue(IMethodRequest request) {
        activationQueue.enqueue(request);
    }

    public void run() {
        while (true) {
            IMethodRequest methodRequest = activationQueue.poll();
            if (methodRequest != null) {
                if (methodRequest.guard()) {
                    methodRequest.call();
                } else {
                    activationQueue.enqueue(methodRequest);
                }
            }
        }
    }
}
