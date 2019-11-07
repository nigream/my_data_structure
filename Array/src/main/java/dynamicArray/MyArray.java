package dynamicArray;

/**
 * @author Nigream Lin
 * @date 2019/10/27 15:16
 * @description 自定义的Array类 MyArray
 */

public class MyArray {
    private int[] data; // 数组
    private int length;    // 数组的容量
    private int size;   // 数组存入的有效元素个数

    /**
     * 空参构造方法
     */
    public MyArray(){
        this(10);
    }

    /**
     * 带参构造方法
     * @param length 数组的长度
     */
    public MyArray(int length){
        this.length = length;
        this.size = 0;
        data = new int[this.length];
    }

    /**
     * 判断数组是否为空
     * @return 若为空，返回true；否则，返回false。
     */
    public boolean isEmpty(){
        return (size == 0);
    }

    /**
     * 判断数组是否已满
     * @return 若数组已满，返回true；否则，返回false。
     */
    public boolean isFull(){
        return (size == length);
    }

    /**
     * 返回数组的容量
     * @return 返回数组的容量
     */
    public int getLength() {
        return length;
    }

    /**
     * 返回数组存入的有效元素个数
     * @return 返回数组存入的有效元素个数
     */
    public int getSize(){
        return size;
    }

    /**
     * 向数组中插入元素
     * @param index 插入元素的索引
     * @param value 插入的元素的值
     */
    public void add(int index,int value){
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("--插入元素失败：索引不正确！--");
        }
        if(isFull()){
            reSize(2 * size);
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i-1];
        }
        data[index] = value;
        size ++;
    }

    /**
     * 在数组尾部插入一个元素
     * @param value 需要插入的元素的值
     */
    public void addLast(int value){
        add(size,value);
    }

    /**
     * 在数组头部插入一个元素
     * @param value 需要插入的元素的值
     */
    public void addFirst(int value){
        add(0,value);
    }

    /**
     * 查询数组中是否包含某个元素值
     * @param value 需要查询的元素的值
     * @return 若包含，则返回true；否则，返回false。
     */
    public boolean contains(int value){
        if(getIndexByValue(value) != -1) return true;
        else return false;
    }

    /**
     * 获取值与参数相等的第一个元素的索引值
     * @param value 需要查询的元素的值
     * @return 返回值与参数相等的第一个元素的索引值；若该元素不存在，则返回-1
     */
    public int getIndexByValue(int value){
        for (int i = 0; i < size; i++) {
            if(data[i] == value){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取值与参数相等的所有元素的索引值
     * @param value 需要查询的元素的值
     * @return 返回值与参数相等的所有元素的索引值封装成的MyArray对象；若该元素不存在，则返回-1封装成的MyArray对象
     */
    public MyArray getAllIndexesByValue(int value){
        MyArray indexArray = new MyArray(length);
        for (int i = 0; i < size; i++) {
            if(data[i] == value){
                indexArray.addLast(i);
            }
        }
        if(indexArray.isEmpty()){
            indexArray.addLast(-1);
        }
        return indexArray;
    }

    /**
     * 获取相应索引处的元素值
     * @param index 需要删除的元素的索引值
     * @return 返回相应索引处的元素值
     */
    public int getValueByIndex(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("--查询元素失败：索引不正确！--");
        }
        return data[index];
    }

    /**
     * 移除相应索引处的元素
     * @param index 需要删除的元素的索引值
     * @return 返回相应索引处的元素值
     */
    public int removeElementByIndex(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("--移除元素失败：索引不正确！--");
        }
        int value = data[index];
        for (int i = index; i < size; i++) {
            data[i] = data[i+1];
        }
        if(size == length / 2){
            reSize(length / 2);
        }
        size --;
        return value;
    }

    /**
     * 移除数组的第一个元素
     * @return 返回数组的第一个元素
     */
    public int removeFirst(){
        removeElementByIndex(0);
        return getValueByIndex(0);
    }

    /**
     * 移除数组的最后一个元素
     * @return 返回数组的最后一个元素
     */
    public int removeLast(){
        removeElementByIndex(size-1);
        return getValueByIndex(size-1);
    }


    /**
     * 移除值与参数相等的第一个元素
     * @param value 需要删除的元素的值
     * @return 返回值与参数相等的第一个元素的索引值；若不存在，则返回-1
     */
    public int removeElementByValue(int value){
        if(getIndexByValue(value) != -1) removeElementByIndex(getIndexByValue(value));
        return getIndexByValue(value);
    }

    /**
     * 移除值与参数相等的所有元素
     * @param value 需要删除的元素的值
     * @return 返回值与参数相等的所有元素的索引值封装成的MyArray对象；若该元素不存在，则返回-1封装成的MyArray对象
     */
    public MyArray removeAllElementsByValue(int value){
        MyArray indexArray = getAllIndexesByValue(value);
        for (int i = 0; i < indexArray.size; i++) {
            if(indexArray.data[i] != -1) removeElementByIndex(indexArray.data[i]);
            for (int j = i + 1; j < indexArray.size; j++) {
                indexArray.data[j] --;
            }
        }
        return indexArray;
    }

    public void set(int index, int value){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("--移除元素失败：索引不正确！--");
        }
        data[index] = value;
    }

        /**
         * 重写toString方法
         * @return 返回数组的相关属性及所有元素
         */
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(String.format("length = %d\tsize = %d\n",length,size));
        stringBuilder.append("[");
        if(size > 0){
            for (int i = 0; i < size - 1; i++) {
                stringBuilder.append(data[i]);
                stringBuilder.append(",");
            }
            stringBuilder.append(data[size-1]);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * 改变数组的容量
     * @param newLength 新数组的容量
     */
    private void reSize(int newLength){
        int[] newData = new int[newLength];
        for (int i = 0; i < length; i++) {
            newData[i] = getValueByIndex(i);
        }
        data = newData;
    }
}
