package uk.co.breadhub.randombukkitutils.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class NBTTagCompound extends NBTBase {

    private final Map map = new HashMap<>();

    public NBTTagCompound() {
    }

    void write(DataOutput dataoutput) {

        for (Object o : this.map.keySet()) {
            String s = (String) o;
            NBTBase nbtbase = (NBTBase) this.map.get(s);

            loadNbtBase(s, nbtbase, dataoutput);
        }

        try {
            dataoutput.writeByte(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) {
        if (i > 512) {
            throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
        }
        else {
            this.map.clear();

            byte b0;

            while ((b0 = loadNbtBase(datainput, nbtreadlimiter)) != 0) {
                String s = readUtfString(datainput, nbtreadlimiter);

                nbtreadlimiter.a((long) (16 * s.length()));
                NBTBase nbtbase = loadNbtBase(b0, s, datainput, i + 1, nbtreadlimiter);

                this.map.put(s, nbtbase);
            }
        }
    }

    public Set c() {
        return this.map.keySet();
    }

    public byte getTypeId() {
        return (byte) 10;
    }

    public void set(String s, NBTBase nbtbase) {
        this.map.put(s, nbtbase);
    }

    public void setByte(String s, byte b0) {
        this.map.put(s, new NBTTagByte(b0));
    }

    public void setShort(String s, short short1) {
        this.map.put(s, new NBTTagShort(short1));
    }

    public void setInt(String s, int i) {
        this.map.put(s, new NBTTagInt(i));
    }

    public void setLong(String s, long i) {
        this.map.put(s, new NBTTagLong(i));
    }

    public void setFloat(String s, float f) {
        this.map.put(s, new NBTTagFloat(f));
    }

    public void setDouble(String s, double d0) {
        this.map.put(s, new NBTTagDouble(d0));
    }

    public void setString(String s, String s1) {
        this.map.put(s, new NBTTagString(s1));
    }

    public void setByteArray(String s, byte[] abyte) {
        this.map.put(s, new NBTTagByteArray(abyte));
    }

    public void setIntArray(String s, int[] aint) {
        this.map.put(s, new NBTTagIntArray(aint));
    }

    public void setBoolean(String s, boolean flag) {
        this.setByte(s, (byte) (flag ? 1 : 0));
    }

    public NBTBase get(String s) {
        return (NBTBase) this.map.get(s);
    }

    public byte readUtfString(String s) {
        NBTBase nbtbase = (NBTBase) this.map.get(s);

        return nbtbase != null ? nbtbase.getTypeId() : 0;
    }

    public boolean hasKey(String s) {
        return this.map.containsKey(s);
    }

    public boolean hasKeyOfType(String s, int i) {
        byte b0 = this.readUtfString(s);

        return b0 == i || (i == 99 && (b0 == 1 || b0 == 2 || b0 == 3 || b0 == 4 || b0 == 5 || b0 == 6));
    }

    public byte getByte(String s) {
        try {
            return !this.map.containsKey(s) ? 0 : ((NBTNumber) this.map.get(s)).getTagByte();
        } catch (ClassCastException classcastexception) {
            return (byte) 0;
        }
    }

    public short getShort(String s) {
        try {
            return !this.map.containsKey(s) ? 0 : ((NBTNumber) this.map.get(s)).getTagShort();
        } catch (ClassCastException classcastexception) {
            return (short) 0;
        }
    }

    public int getInt(String s) {
        try {
            return !this.map.containsKey(s) ? 0 : ((NBTNumber) this.map.get(s)).getTagInt();
        } catch (ClassCastException classcastexception) {
            return 0;
        }
    }

    public long getLong(String s) {
        try {
            return !this.map.containsKey(s) ? 0L : ((NBTNumber) this.map.get(s)).getTagLong();
        } catch (ClassCastException classcastexception) {
            return 0L;
        }
    }

    public float getFloat(String s) {
        try {
            return !this.map.containsKey(s) ? 0.0F : ((NBTNumber) this.map.get(s)).getTagFloat();
        } catch (ClassCastException classcastexception) {
            return 0.0F;
        }
    }

    public double getDouble(String s) {
        try {
            return !this.map.containsKey(s) ? 0.0D : ((NBTNumber) this.map.get(s)).getTagDouble();
        } catch (ClassCastException classcastexception) {
            return 0.0D;
        }
    }

    public String getString(String s) {
        try {
            return !this.map.containsKey(s) ? "" : ((NBTBase) this.map.get(s)).getData();
        } catch (ClassCastException classcastexception) {
            return "";
        }
    }

    public byte[] getByteArray(String s) {
        byte[] arr = null;
        try {
            arr = !this.map.containsKey(s) ? new byte[0] : ((NBTTagByteArray) this.map.get(s)).c();
        } catch (ClassCastException ignored) {}
        return arr;
    }

    public int[] getIntArray(String s) {
        int[] arr = null;
        try {
            arr = !this.map.containsKey(s) ? new int[0] : ((NBTTagIntArray) this.map.get(s)).c();
        } catch (ClassCastException ignored) {}
        return arr;
    }

    public NBTTagCompound getCompound(String s) {
        NBTTagCompound ntc = null;
        try {
            ntc = !this.map.containsKey(s) ? new NBTTagCompound() : (NBTTagCompound) this.map.get(s);
        } catch (ClassCastException ignored) {}
        return ntc;
    }

    public NBTTagList getList(String s, int i) {
        NBTTagList ntl = new NBTTagList();

        try {
            if (this.readUtfString(s) != 9) {
                return new NBTTagList();
            }
            else {
                NBTTagList nbttaglist = (NBTTagList) this.map.get(s);

                ntl = nbttaglist.size() > 0 && nbttaglist.d() != i ? new NBTTagList() : nbttaglist;
            }
        } catch (ClassCastException ignored) {}

        return ntl;
    }

    public boolean getBoolean(String s) {
        return this.getByte(s) != 0;
    }

    public void remove(String s) {
        this.map.remove(s);
    }

    public String toString() {
        String s = "{";

        String s1;

        for (Iterator iterator = this.map.keySet().iterator(); iterator.hasNext(); s = s + s1 + ':' + this.map.get(s1) + ',') {
            s1 = (String) iterator.next();
        }

        return s + "}";
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public NBTBase clone() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        for (Object o : this.map.keySet()) {
            String s = (String) o;

            nbttagcompound.set(s, ((NBTBase) this.map.get(s)).clone());
        }

        return nbttagcompound;
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) object;

            return this.map.entrySet().equals(nbttagcompound.map.entrySet());
        }
        else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ this.map.hashCode();
    }

    private static void loadNbtBase(String s, NBTBase nbtbase, DataOutput dataoutput) {
        try {
            dataoutput.writeByte(nbtbase.getTypeId());
            if (nbtbase.getTypeId() != 0) {
                dataoutput.writeUTF(s);
                nbtbase.write(dataoutput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte loadNbtBase(DataInput datainput, NBTReadLimiter nbtreadlimiter) {
        byte bit;

        try {
            bit = datainput.readByte();
        } catch (IOException e) {
            e.printStackTrace();
            bit = Byte.parseByte(null);
        }

        return bit;
    }

    private static String readUtfString(DataInput datainput, NBTReadLimiter nbtreadlimiter) {
        String out = "";
        try {
            out = datainput.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static NBTBase loadNbtBase(byte b0, String s, DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) {
        NBTBase nbtbase = NBTBase.createTag(b0);

        try {
            nbtbase.load(datainput, i, nbtreadlimiter);
        } catch (IOException ignored) {}
        return nbtbase;
    }

    public static Map loadNbtBase(NBTTagCompound nbttagcompound) {
        return nbttagcompound.map;
    }
}
