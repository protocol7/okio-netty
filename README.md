# okio-netty

[okio](https://github.com/square/okio)
[Source](https://square.github.io/okio/2.x/okio/jvm/okio/-source/index.html)
and [Sink](https://square.github.io/okio/2.x/okio/jvm/okio/-sink/index.html)
implementations for [Netty](https://netty.io/)
[ByteBuf](https://netty.io/4.1/api/io/netty/buffer/ByteBuf.html), allowing
for efficient transfer of data between the two buffer implementations.

okio is a convenient and high performance buffer and bytestring library. Netty
is a high performance networking library, containing its own buffer
implementation, ByteBuf. This small library allows you to easily convert
between the two.

## Performance

Different implementations of the Source and Sink has been benchmarked. This
library only contains what has been deemed the best performing one, but you can
find other implementations in the git history.

In general, the implementations should, for small-ish buffers allow for at least
a million ops/s.

```
Bench.arraySource     1024  thrpt    5  1781384.930 ± 559230.705  ops/s
Bench.arraySource     8192  thrpt    5  1110981.019 ± 387867.860  ops/s
Bench.arraySource  1048510  thrpt    5     8591.394 ±   1867.994  ops/s
```

```
Bench.unsafeSink      1024  thrpt    5  3485780.615 ± 550017.268  ops/s
Bench.unsafeSink      8192  thrpt    5   909012.949 ± 262954.930  ops/s
Bench.unsafeSink   1048510  thrpt    5     3816.934 ±    964.137  ops/s
```
