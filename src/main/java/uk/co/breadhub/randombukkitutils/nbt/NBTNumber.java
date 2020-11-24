package uk.co.breadhub.randombukkitutils.nbt;

public abstract class NBTNumber extends NBTBase {

    protected NBTNumber() {}

    public abstract long getTagLong();

    public abstract int getTagInt();

    public abstract short getTagShort();

    public abstract byte getTagByte();

    public abstract double getTagDouble();

    public abstract float getTagFloat();
}
