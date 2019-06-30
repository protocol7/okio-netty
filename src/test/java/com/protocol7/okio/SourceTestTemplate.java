package com.protocol7.okio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import okio.Buffer;
import okio.Source;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public abstract class SourceTestTemplate {

  private static final byte[] DATA1 = "hello".getBytes(StandardCharsets.US_ASCII);

  protected abstract Source source(ByteBuf bb);

  @Test
  public void read() throws IOException {
    final ByteBuf bb = Unpooled.wrappedBuffer(DATA1);
    Buffer buf = new Buffer();
    assertEquals(DATA1.length, buf.writeAll(new ByteBufSource(bb)));
  }

  @Test
  public void readLarge() throws IOException {
    final int size = 10 * 1024 * 1024 + 123;
    final byte[] b = TestUtil.rnd(size);

    final ByteBuf bb = Unpooled.wrappedBuffer(b);

    final Buffer buf = new Buffer();
    assertEquals(size, buf.writeAll(source(bb)));

    assertArrayEquals(b, buf.readByteArray());
  }
}
