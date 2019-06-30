package com.protocol7.okio.benchmark;

import com.protocol7.okio.ByteBufSink;
import com.protocol7.okio.ByteBufSource;
import java.io.IOException;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Fork(value = 1, warmups = 2)
@Measurement(iterations = 5)
@Warmup(iterations = 5)
@BenchmarkMode(Mode.Throughput)
public class Bench {

  public static void main(String[] args) throws Exception {
    org.openjdk.jmh.Main.main(args);
  }

  @Benchmark
  public void unsafeSink(final SinkExecutionPlan plan, final Blackhole blackhole)
      throws IOException {
    blackhole.consume(plan.buffer.readAll(new ByteBufSink(plan.bb)));
  }

  @Benchmark
  public void arraySource(final SourceExecutionPlan plan, final Blackhole blackhole)
      throws IOException {
    blackhole.consume(plan.buffer.writeAll(new ByteBufSource(plan.bb)));
  }
}
