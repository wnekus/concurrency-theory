public class Servant implements IProxy {
    Scheduler scheduler;
    Buffer buffer;

    public Servant(Buffer buffer, Scheduler scheduler){
        this.scheduler = scheduler;
        this.buffer = buffer;
    }

    @Override
    public void add(Object object){
        scheduler.enqueue(new AddRequest(buffer, object));
    }

    @Override
    public Future get(){
        Future future = new Future();
        scheduler.enqueue(new GetRequest(buffer, future));
        return future;
    }
}
