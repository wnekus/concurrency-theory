public class Future {
    private Object object;

    public void set(Object object){
        this.object = object;
    }

    public Object get(){
        return object;
    }

    public boolean isAvailable(){
        return object != null;
    }

}
