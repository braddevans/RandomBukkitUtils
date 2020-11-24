package uk.co.breadhub.randombukkitutils.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagByte extends NBTNumber {

    private byte data;

    NBTTagByte() {}

    public NBTTagByte(byte b0) {
        this.data = b0;
    }

    void write(DataOutput dataoutput) {
        try {
            dataoutput.writeByte(this.data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) {
        nbtreadlimiter.a(8L);
        try {
            this.data = datainput.readByte();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte getTypeId() {
        return (byte) 1;
    }

    public String toString() {
        return "" + this.data + "b";
    }

    public NBTBase clone() {
        return new NBTTagByte(this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagByte nbttagbyte = (NBTTagByte) object;

            return this.data == nbttagbyte.data;
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
        return (short) this.data;
    }

    public byte getTagByte() {
        return this.data;
    }

    public double getTagDouble() {
        return (double) this.data;
    }

    public float getTagFloat() {
        return (float) this.data;
    }
}
