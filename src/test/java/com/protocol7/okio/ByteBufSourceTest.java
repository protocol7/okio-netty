package com.protocol7.okio;

import io.netty.buffer.ByteBuf;
import okio.Source;

public class ByteBufSourceTest extends SourceTest {
    @Override
    protected Source source(final ByteBuf bb) {
        return new ByteBufSource(bb);
    }
}
