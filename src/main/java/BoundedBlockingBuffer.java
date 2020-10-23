public class BoundedBlockingBuffer<T> {
    T data = null;
    boolean isEmpty = true;

    public synchronized void put(T data) {
        if (!isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data = data;
            isEmpty = false;
            notify();
        }
    }
    public synchronized T take() {
        if (isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isEmpty = true;
        notify();
        return data;
    }

}
