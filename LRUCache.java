import java.util.HashMap;
import java.util.Map;

class Data {
    private int usedTimes = 0;
    private final int content;
    Data(int content) {
        this.content = content;
    }

    public int getUsedTimes() {
        return usedTimes;
    }

    public int getContent() {
        return content;
    }

    public void onUsage() {
        usedTimes ++;
    }
}
public class LRUCache {
    private final int capacity;
    private final Map<Integer, Data> dataList = new HashMap<>();
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    public int get(int k) {
        Data data = dataList.get(k);
        if(data == null) return -1; // Not found

        data.onUsage();
        if(k == lastUsedOneIndex) compareToGetCurrentlyLeastRecentlyUsedIndex();
        return data.getContent();
    }
    public void put(int k, int v) {
        if(dataList.size() == capacity) {
            dataList.remove(lastUsedOneIndex);
            System.out.println("Evict key " + lastUsedOneIndex);
        }
        dataList.put(k, new Data(v));

        Data lastUsedOneData = dataList.get(lastUsedOneIndex);
        if(lastUsedOneData == null || lastUsedOneData.getUsedTimes() > 0 || k < lastUsedOneIndex)
            lastUsedOneIndex = k;
    }
    public int delete(int k) {

        Data data = dataList.get(k);
        if(data == null) return -1; // Not found

        int currentContent = data.getContent();

        dataList.remove(currentContent);
        return currentContent;
    }


    private int lastUsedOneIndex = 0;

    private void compareToGetCurrentlyLeastRecentlyUsedIndex() {

        int currentLeastUsedTimes = Integer.MAX_VALUE;
        for(Map.Entry<Integer, Data> entry : dataList.entrySet()) {

            int currentIndex = entry.getKey();
            Data currentData = entry.getValue();
            if(currentData.getUsedTimes() < currentLeastUsedTimes)
                lastUsedOneIndex = currentIndex;

        }
    }
}
