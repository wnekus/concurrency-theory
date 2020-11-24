public class AddRequest implements IMethodRequest {
    private Buffer buffer;
    private Object object;

    public AddRequest(Buffer buffer, Object object){
        this.buffer = buffer;
        this.object = object;
    }

    @Override
    public void call() {
        buffer.add(object);
    }

    @Override
    public boolean guard() {
        return buffer.isNotFull();
    }
}
