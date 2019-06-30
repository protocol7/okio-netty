package com.protocol7.okio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import okio.Buffer;
import okio.Sink;
import org.junit.Test;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static org.junit.Assert.assertArrayEquals;

public abstract class SinkTestTemplate {

    private static final byte[] DATA = "hello".getBytes(US_ASCII);
    private static final byte[] PARTIAL = "ello".getBytes(US_ASCII);

    protected abstract Sink sink(ByteBuf bb);

    @Test
    public void write() throws IOException {
        final ByteBuf bb = Unpooled.buffer();
        final Buffer buf = new Buffer().write(DATA);

        buf.readAll(sink(bb));

        assertByteBuf(DATA, bb);
    }

    @Test
    public void writeLimitedByteCount() {
        final ByteBuf bb = Unpooled.buffer();
        final Buffer buf = new Buffer().write(DATA);

        final ByteBufSink sink = new ByteBufSink(bb);
        sink.write(buf, 2);

        assertByteBuf("he".getBytes(US_ASCII), bb);

        sink.write(buf, 4);

        assertByteBuf(DATA, bb);
    }

    @Test
    public void writePartiallyConsumed() throws IOException {
        final ByteBuf bb = Unpooled.buffer();
        final Buffer buf = new Buffer().write(DATA);

        // consume a byte
        buf.readByte();

        buf.readAll(sink(bb));

        assertByteBuf(PARTIAL, bb);
    }

    @Test
    public void writeLarge() throws IOException {
        final int size = 10 * 1024 * 1024 + 123;
        final byte[] b = TestUtil.rnd(size);

        final Buffer buf = new Buffer().write(b);

        final ByteBuf bb = Unpooled.buffer();
        buf.readAll(sink(bb));

        assertByteBuf(b, bb);
    }

    private void assertByteBuf(final byte[] expected, final ByteBuf bb) {
        final int pos = bb.readerIndex();
        final byte[] b = new byte[bb.readableBytes()];
        bb.readBytes(b);
        assertArrayEquals(expected, b);
        bb.readerIndex(pos);
    }
}
