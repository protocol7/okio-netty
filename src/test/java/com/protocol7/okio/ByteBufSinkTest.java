package com.protocol7.okio;

import io.netty.buffer.ByteBuf;
import okio.Sink;

public class ByteBufSinkTest extends SinkTest {

    @Override
    protected Sink sink(final ByteBuf bb) {
        return new ByteBufSink(bb);
    }
}
