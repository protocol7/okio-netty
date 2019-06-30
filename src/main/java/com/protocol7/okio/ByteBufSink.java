package com.protocol7.okio;

import io.netty.buffer.ByteBuf;
import okio.Buffer;
import okio.Sink;
import okio.Timeout;

import java.io.EOFException;

public class ByteBufSink implements Sink {

    private final ByteBuf bb;

    public ByteBufSink(final ByteBuf bb) {
        this.bb = bb;
    }

    @Override
    public Timeout timeout() {
        return Timeout.NONE;
    }

    @Override
    public void write(final Buffer source, final long byteCount) throws EOFException {
        if (byteCount < 0) {
            throw new IllegalArgumentException("Too small byteCount");
        }
        bb.writeBytes(source.readByteArray(byteCount));
    }

    @Override
    public void flush() {}

    @Override
    public void close() {}
}
