package uk.co.breadhub.randombukkitutils.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagLong extends NBTNumber {

    private long data;

    NBTTagLong() {}

    public NBTTagLong(long i) {
        this.data = i;
    }

    void write(DataOutput dataoutput) {
        try {
            dataoutput.writeLong(this.data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) {
        nbtreadlimiter.a(64L);
        try {
            this.data = datainput.readLong();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte getTypeId() {
        return (byte) 4;
    }

    public String toString() {
        return "" + this.data + "L";
    }

    public NBTBase clone() {
        return new NBTTagLong(this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagLong nbttaglong = (NBTTagLong) object;

            return this.data == nbttaglong.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ (int) (this.data ^ this.data >>> 32);
    }

    public long getTagLong() {
        return this.data;
    }

    public int getTagInt() {
        return (int) (this.data);
    }

    public short getTagShort() {
        return (short) ((int) (this.data & 65535L));
    }

    public byte getTagByte() {
        return (byte) ((int) (this.data & 255L));
    }

    public double getTagDouble() {
        return (double) this.data;
    }

    public float getTagFloat() {
        return (float) this.data;
    }
}
