import java.util.LinkedList;
import java.util.List;

public class Buffer {
    private List<Object> buffer;
    private int bufSize;

    public Buffer(int bufSize){
        this.bufSize = bufSize;
        this.buffer = new LinkedList<Object>();
    }

    public void add(Object object) {
        if(isNotFull()){
            buffer.add(object);
        }
    }

    public Object get() {
        return buffer.isEmpty() ? null : buffer.get(0);
    }

    public boolean isEmpty(){
        return buffer.isEmpty();
    }

    public boolean isNotFull(){
        return buffer.size() != bufSize;
    }

}
