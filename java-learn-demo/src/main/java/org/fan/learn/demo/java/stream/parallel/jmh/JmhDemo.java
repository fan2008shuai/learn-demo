package org.fan.learn.demo.java.stream.parallel.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author author
 * @date 2023/1/7
 */
// 输出每次方法执行的平均时间
@BenchmarkMode(Mode.AverageTime)
// 输出的时间单位
@OutputTimeUnit(TimeUnit.MICROSECONDS)
// 预热一下。因为 JVM 的 JIT 机制的存在，如果某个函数被调用多次之后，JVM 会尝试将其编译成为机器码从而提高执行速度
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)

@Measurement(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
// Fork几个进程跑
@Fork(2)
// 每个进程跑几个线程，一般是CPU核数
@Threads(2)
@State(Scope.Benchmark)
public class JmhDemo {

    @Param({"10000", "100000", "1000000"})
    long N;

    @Setup
    public void setup() {
        // 初始化工作
    }

    @TearDown(Level.Invocation)
    public void tearDown() {
        System.gc();
    }

    @Benchmark
    public long sequentialSum() {
        return Stream.iterate(0L, i -> i + 1).limit(N).reduce(0L, Long::sum);
    }

    @Benchmark
    public long iterativeSum() {
        long sum = 0;
        for (long i = 0; i < N; i++) {
            sum += i;
        }
        return sum;
    }

    @Benchmark
    public long rangedSum() {
        return LongStream.rangeClosed(1, N).reduce(0L, Long::sum);
    }

    @Benchmark
    public long parallelRangedSum() {
        return LongStream.rangeClosed(1, N).reduce(0L, Long::sum);
    }

    // 输出结果在benchmark.log
    /**
    Benchmark                      (N)  Mode  Cnt      Score      Error  Units
    JmhDemo.iterativeSum         10000  avgt   10      8.094 ±    7.533  us/op
    JmhDemo.iterativeSum        100000  avgt   10    176.457 ±  155.258  us/op
    JmhDemo.iterativeSum       1000000  avgt   10   2984.962 ± 2206.093  us/op
    JmhDemo.parallelRangedSum    10000  avgt   10     29.747 ±   15.225  us/op
    JmhDemo.parallelRangedSum   100000  avgt   10   1231.865 ± 1642.322  us/op
    JmhDemo.parallelRangedSum  1000000  avgt   10   5571.834 ± 1660.228  us/op
    JmhDemo.rangedSum            10000  avgt   10     34.918 ±   29.496  us/op
    JmhDemo.rangedSum           100000  avgt   10   1320.676 ±  870.643  us/op
    JmhDemo.rangedSum          1000000  avgt   10   5705.402 ± 2197.153  us/op
    JmhDemo.sequentialSum        10000  avgt   10    233.467 ±  153.067  us/op
    JmhDemo.sequentialSum       100000  avgt   10   7661.641 ±  195.048  us/op
    JmhDemo.sequentialSum      1000000  avgt   10  34183.864 ±  179.032  us/op
    */
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(JmhDemo.class.getSimpleName())
                .output("/tmp/benchmark.log")
                .build();
        new Runner(options).run();
    }

}
