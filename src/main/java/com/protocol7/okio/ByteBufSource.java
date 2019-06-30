package com.protocol7.okio;

import io.netty.buffer.ByteBuf;
import okio.Buffer;
import okio.Source;
import okio.Timeout;

public class ByteBufSource implements Source {

  private final ByteBuf bb;

  public ByteBufSource(final ByteBuf bb) {
    this.bb = bb;
  }

  @Override
  public long read(final Buffer sink, final long byteCount) {
    if (byteCount < 0) {
      throw new IllegalArgumentException("Too small byteCount");
    } else if (byteCount > Integer.MAX_VALUE) {
      throw new IllegalArgumentException("Too large byteCount");
    }

    final int readBytes = Math.min(bb.readableBytes(), (int) byteCount);
    if (readBytes == 0) {
      return -1;
    }

    final byte[] b = new byte[readBytes];
    bb.readBytes(b);
    sink.write(b);
    return readBytes;
  }

  @Override
  public Timeout timeout() {
    return Timeout.NONE;
  }

  @Override
  public void close() {}
}
