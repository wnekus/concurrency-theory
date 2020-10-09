class IThread extends Thread {
    private Counter cnt;
    private int numberOfRepeat;
    private ExecutorLazySingleton executorLazySingleton;

    public IThread(Counter cnt){
        super();
        numberOfRepeat = 10000;
        executorLazySingleton = ExecutorLazySingleton.getInstance();
        this.cnt = cnt;
    }

    public void run(){
        for(int i=0;i<numberOfRepeat;i++){
            if(executorLazySingleton.RequestOperation()){
                cnt.inc();
                executorLazySingleton.setIsProcessing();
            }
            else
                numberOfRepeat++;
        }
    }
}