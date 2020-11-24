package uk.co.breadhub.randombukkitutils.nbt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class NBTCompressedStreamTools {

    public static NBTTagCompound toolsRead(InputStream inputstream) throws IOException {
        DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(inputstream)));

        NBTTagCompound nbttagcompound;

        try {
            nbttagcompound = toolsRead((DataInput) datainputstream, NBTReadLimiter.a);
        } finally {
            datainputstream.close();
        }

        return nbttagcompound;
    }

    public static void toolsRead(NBTTagCompound nbttagcompound, OutputStream outputstream) throws IOException {
        DataOutputStream dataoutputstream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(outputstream)));

        try {
            toolsRead(nbttagcompound, (DataOutput) dataoutputstream);
        } finally {
            dataoutputstream.close();
        }
    }

    public static NBTTagCompound toolsRead(byte[] abyte, NBTReadLimiter nbtreadlimiter) throws IOException {
        DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(abyte))));

        NBTTagCompound nbttagcompound;

        try {
            nbttagcompound = toolsRead((DataInput) datainputstream, nbtreadlimiter);
        } finally {
            datainputstream.close();
        }

        return nbttagcompound;
    }

    public static byte[] toolsRead(NBTTagCompound nbttagcompound) throws IOException {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        DataOutputStream dataoutputstream = new DataOutputStream(new GZIPOutputStream(bytearrayoutputstream));

        try {
            toolsRead(nbttagcompound, (DataOutput) dataoutputstream);
        } finally {
            dataoutputstream.close();
        }

        return bytearrayoutputstream.toByteArray();
    }

    public static NBTTagCompound toolsRead(DataInputStream datainputstream) throws IOException {
        return toolsRead((DataInput) datainputstream, NBTReadLimiter.a);
    }

    public static NBTTagCompound toolsRead(DataInput datainput, NBTReadLimiter nbtreadlimiter) throws IOException {
        NBTBase nbtbase = toolsRead(datainput, 0, nbtreadlimiter);

        if (nbtbase instanceof NBTTagCompound) {
            return (NBTTagCompound) nbtbase;
        } else {
            throw new IOException("Root tag must be a named compound tag");
        }
    }

    public static void toolsRead(NBTTagCompound nbttagcompound, DataOutput dataoutput) throws IOException {
        toolsRead((NBTBase) nbttagcompound, dataoutput);
    }

    private static void toolsRead(NBTBase nbtbase, DataOutput dataoutput) throws IOException {
        dataoutput.writeByte(nbtbase.getTypeId());
        if (nbtbase.getTypeId() != 0) {
            dataoutput.writeUTF("");
            nbtbase.write(dataoutput);
        }
    }

    private static NBTBase toolsRead(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) throws IOException {
        byte bit = datainput.readByte();

        if (bit == 0) {
            return new NBTTagEnd();
        } else {
            datainput.readUTF();
            NBTBase nbtbase = NBTBase.createTag(bit);

            try {
                nbtbase.load(datainput, i, nbtreadlimiter);
                return nbtbase;
            } catch (Exception e){}
        }
        return null;
    }
}
