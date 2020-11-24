package uk.co.breadhub.randombukkitutils.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagShort extends NBTNumber {

    private short data;

    public NBTTagShort() {}

    public NBTTagShort(short short1) {
        this.data = short1;
    }

    void write(DataOutput dataoutput) {
        try {
            dataoutput.writeShort(this.data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) {
        nbtreadlimiter.a(16L);
        try {
            this.data = datainput.readShort();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte getTypeId() {
        return (byte) 2;
    }

    public String toString() {
        return "" + this.data + "s";
    }

    public NBTBase clone() {
        return new NBTTagShort(this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagShort nbttagshort = (NBTTagShort) object;

            return this.data == nbttagshort.data;
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
        return this.data;
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
