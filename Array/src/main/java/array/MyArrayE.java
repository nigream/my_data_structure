package array;

/**
 * @author Nigream Lin
 * @date 2019/10/27 15:16
 * @description 自定义的Array类 MyArray
 */

public class MyArrayE<E> {
    private E[] data; // 数组
    private int length;    // 数组的容量
    private int size;   // 数组存入的有效元素个数

    /**
     * 空参构造方法
     */
    public MyArrayE(){
        this(10);
    }

    /**
     * 带参构造方法
     * @param length 数组的长度
     */
    public MyArrayE(int length){
        this.length = length;
        this.size = 0;
        data = (E[])new Object[this.length];
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
    public void add(int index,E value){
        if(isFull()) throw new IllegalArgumentException("--插入元素失败：数组已满！--");
        if(index < 0 || index > size) throw new IllegalArgumentException("--插入元素失败：索引不正确！--");
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
    public void addLast(E value){
        add(size,value);
    }

    /**
     * 在数组头部插入一个元素
     * @param value 需要插入的元素的值
     */
    public void addFirst(E value){
        add(0,value);
    }

    /**
     * 查询数组中是否包含某个元素值
     * @param value 需要查询的元素的值
     * @return 若包含，则返回true；否则，返回false。
     */
    public boolean contains(E value){
        if(getIndexByValue(value) != -1) return true;
        else return false;
    }

    /**
     * 获取值与参数相等的第一个元素的索引值
     * @param value 需要查询的元素的值
     * @return 返回值与参数相等的第一个元素的索引值；若该元素不存在，则返回-1
     */
    public int getIndexByValue(E value){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(value)){
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
    public MyArrayE<Integer> getAllIndexesByValue(E value){
        MyArrayE<Integer> indexArray = new MyArrayE<Integer>(length);
        for (int i = 0; i < size; i++) {
            if(data[i].equals(value)){
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
    public E getValueByIndex(int index){
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
    public E removeElementByIndex(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("--移除元素失败：索引不正确！--");
        }
        E value = data[index];
        for (int i = index; i < size; i++) {
            data[i] = data[i+1];
        }
        size --;
        return value;
    }

    /**
     * 移除数组的第一个元素
     * @return 返回数组的第一个元素
     */
    public E removeFirst(){
        removeElementByIndex(0);
        return getValueByIndex(0);
    }

    /**
     * 移除数组的最后一个元素
     * @return 返回数组的最后一个元素
     */
    public E removeLast(){
        removeElementByIndex(size-1);
        return getValueByIndex(size-1);
    }

    /**
     * 移除值与参数相等的第一个元素
     * @param value 需要删除的元素的值
     * @return 返回值与参数相等的第一个元素的索引值；若不存在，则返回-1
     */
    public int removeElementByValue(E value){
        if(getIndexByValue(value) != -1) removeElementByIndex(getIndexByValue(value));
        return getIndexByValue(value);
    }

    /**
     * 移除值与参数相等的所有元素
     * @param value 需要删除的元素的值
     * @return 返回值与参数相等的所有元素的索引值封装成的MyArray对象；若该元素不存在，则返回-1封装成的MyArray对象
     */
    public MyArrayE<Integer> removeAllElementsByValue(E value){
        MyArrayE<Integer> indexArray = getAllIndexesByValue(value);
        for (int i = 0; i < indexArray.size; i++) {
            if(indexArray.getValueByIndex(i) != -1) {
                removeElementByIndex(indexArray.getValueByIndex(i));
            }
            for (int j = i + 1; j < indexArray.size; j++) {
                indexArray.set(j,indexArray.getValueByIndex(j) - 1);
            }
        }
        return indexArray;
    }

    public void set(int index, E value){
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
}
