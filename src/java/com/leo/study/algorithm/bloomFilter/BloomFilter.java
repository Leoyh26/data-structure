package com.leo.study.algorithm.bloomFilter;

public class BloomFilter<T> {

    /**
     * 二进制向量的长度
     */
    private int bitSize;

    /**
     * hash函数数量
     */
    private int hashSize;

    /**
     * 二进制向量
     */
    private long[] bits;

    /**
     *
     * @param n 数据规模
     * @param p 误判率
     */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p <= 0 || p >= 1) {
            throw new IllegalArgumentException("wrong n or p");
        }
        double ln2 = Math.log(2);
        bitSize = (int) - ( (n * Math.log(p)) / (ln2 * ln2));
        hashSize = (int) (bitSize * ln2 / n);
        bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];
    }

    /**
     * 添加元素
     * @param value
     * @return
     */
    public boolean put(T value){
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;
        for (int i = 1; i <= hashSize; i++) {
            int combinesHash = hash1 + (i * hash2);
            if (combinesHash < 0) {
                combinesHash = ~combinesHash;
            }
            int index = combinesHash % bitSize;
            set(index);
        }
        return false;
    }

    private void set(int index) {
        long value = bits[index / Long.SIZE];
        bits[index / Long.SIZE] = value | 1 << (index % Long.SIZE);
    }

    public boolean contains(T value){
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;

        for (int i = 1; i <= hashSize; i++) {
            int combinesHash = hash1 + (i * hash2);
            if (combinesHash < 0) {
                combinesHash = ~combinesHash;
            }
            int index = combinesHash % bitSize;
            if (!get(index)) {
                return false;
            }
        }
        return true;
    }

    private boolean get(int index) {
        long value = bits[index / Long.SIZE];
        return (value & 1 << (index % Long.SIZE)) != 0;
    }
}
