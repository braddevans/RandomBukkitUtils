package uk.co.breadhub.randombukkitutils.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagDouble extends NBTNumber {

    private double data;

    NBTTagDouble() {}

    public NBTTagDouble(double d0) {
        this.data = d0;
    }

    void write(DataOutput dataoutput) {
        try {
            dataoutput.writeDouble(this.data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) {
        nbtreadlimiter.a(64L);
        try {
            this.data = datainput.readDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte getTypeId() {
        return (byte) 6;
    }

    public String toString() {
        return "" + this.data + "d";
    }

    public NBTBase clone() {
        return new NBTTagDouble(this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagDouble nbttagdouble = (NBTTagDouble) object;

            return this.data == nbttagdouble.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        long i = Double.doubleToLongBits(this.data);

        return super.hashCode() ^ (int) (i ^ i >>> 32);
    }

    public long getTagLong() {
        return (long) Math.floor(this.data);
    }

    public int getTagInt() {
        return MathHelper.floor(this.data);
    }

    public short getTagShort() {
        return (short) (MathHelper.floor(this.data) & '\uffff');
    }

    public byte getTagByte() {
        return (byte) (MathHelper.floor(this.data) & 255);
    }

    public double getTagDouble() {
        return this.data;
    }

    public float getTagFloat() {
        return (float) this.data;
    }
}
