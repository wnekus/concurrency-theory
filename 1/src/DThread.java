class DThread extends Thread {
    private Counter cnt;
    private int numberOfRepeat;
    private ExecutorLazySingleton executorLazySingleton;

    public DThread(Counter cnt){
        super();
        numberOfRepeat = 10000;
        executorLazySingleton = ExecutorLazySingleton.getInstance();
        this.cnt = cnt;
    }

    public void run(){
        for(int i=0;i<numberOfRepeat;i++){
            if(executorLazySingleton.RequestOperation()){
                cnt.dec();
                executorLazySingleton.setIsProcessing();
            }
            else
                numberOfRepeat++;
        }
    }
}