package com.protocol7.okio;

import io.netty.buffer.ByteBuf;
import okio.Buffer;
import okio.Sink;
import okio.Timeout;

public class ByteBufSink implements Sink {

  private final ByteBuf bb;
  private long offset = 0;

  public ByteBufSink(final ByteBuf bb) {
    this.bb = bb;
  }

  @Override
  public Timeout timeout() {
    return Timeout.NONE;
  }

  @Override
  public void write(final Buffer source, final long byteCount) {
    if (byteCount < 0) {
      throw new IllegalArgumentException("Too small byteCount");
    }
    long read = 0;
    try (Buffer.UnsafeCursor cursor = source.readUnsafe()) {
      cursor.seek(offset);
      do {
        final int len = Math.min(cursor.end - cursor.start, (int) (byteCount - read));
        bb.writeBytes(cursor.data, cursor.start, len);
        read += len;
      } while (cursor.next() != -1 && read < byteCount);
    }
    offset += byteCount;
  }

  @Override
  public void flush() {}

  @Override
  public void close() {}
}
