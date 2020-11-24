package uk.co.breadhub.randombukkitutils.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagInt extends NBTNumber {

    private int data;

    NBTTagInt() {}

    public NBTTagInt(int i) {
        this.data = i;
    }

    void write(DataOutput dataoutput) {
        try {
            dataoutput.writeInt(this.data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) {
        nbtreadlimiter.a(32L);
        try {
            this.data = datainput.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte getTypeId() {
        return (byte) 3;
    }

    public String toString() {
        return "" + this.data;
    }

    public NBTBase clone() {
        return new NBTTagInt(this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagInt nbttagint = (NBTTagInt) object;

            return this.data == nbttagint.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ this.data;
    }

    public long getTagLong() {
        return (long) this.data;
    }

    public int getTagInt() {
        return this.data;
    }

    public short getTagShort() {
        return (short) (this.data & '\uffff');
    }

    public byte getTagByte() {
        return (byte) (this.data & 255);
    }

    public double getTagDouble() {
        return (double) this.data;
    }

    public float getTagFloat() {
        return (float) this.data;
    }
}
