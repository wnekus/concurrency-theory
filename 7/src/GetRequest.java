public class GetRequest implements IMethodRequest {
    private Future future;
    private Buffer buffer;

    public GetRequest(Buffer buffer, Future future){
        this.buffer = buffer;
        this.future = future;
    }

    @Override
    public void call() {
        future.set(buffer.get());
    }

    @Override
    public boolean guard() {
        return !buffer.isEmpty();
    }
}
