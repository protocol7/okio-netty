package com.protocol7.okio.benchmark;

import com.protocol7.okio.TestUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import okio.Buffer;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class SinkExecutionPlan {

    @Param({ "1024", "8192", "1048510"})
    public int size;

    public Buffer buffer;
    public ByteBuf bb;

    @Setup(Level.Invocation)
    public void setup() {
        bb = Unpooled.buffer();
        buffer = new Buffer().write(TestUtil.rnd(size));
    }
}
