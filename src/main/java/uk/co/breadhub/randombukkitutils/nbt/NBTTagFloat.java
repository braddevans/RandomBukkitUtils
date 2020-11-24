package uk.co.breadhub.randombukkitutils.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagFloat extends NBTNumber {

    private float data;

    NBTTagFloat() {}

    public NBTTagFloat(float f) {
        this.data = f;
    }

    void write(DataOutput dataoutput) {
        try {
            dataoutput.writeFloat(this.data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) {
        nbtreadlimiter.a(32L);
        try {
            this.data = datainput.readFloat();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte getTypeId() {
        return (byte) 5;
    }

    public String toString() {
        return "" + this.data + "f";
    }

    public NBTBase clone() {
        return new NBTTagFloat(this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagFloat nbttagfloat = (NBTTagFloat) object;

            return this.data == nbttagfloat.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ Float.floatToIntBits(this.data);
    }

    public long getTagLong() {
        return (long) this.data;
    }

    public int getTagInt() {
        return MathHelper.parseFloat(this.data);
    }

    public short getTagShort() {
        return (short) (MathHelper.parseFloat(this.data) & '\uffff');
    }

    public byte getTagByte() {
        return (byte) (MathHelper.parseFloat(this.data) & 255);
    }

    public double getTagDouble() {
        return (double) this.data;
    }

    public float getTagFloat() {
        return this.data;
    }
}
