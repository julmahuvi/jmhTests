package com.julmahuvi;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class MapBenchmark {

    Map<Integer, Entry> big = null;
    List<Integer> data = null;
    List<Integer> keys = null;
    List<Integer> offkeys = null;


    @Setup
    public void setup() {

        big = new HashMap<>();
        data = new ArrayList<>();

        Integer a[] = {11, 101, 4501, 666, 80000, 99001, 50101, 20111, 10453, 100101, 231006, 333333, 1,0, 151001};
        Integer b[] = {500011, 5101, 4501, 600000, 700000, 99001, 50101, 20111, 1000000, 700000, 231006, 333333, 1,0, 151001};

        keys = Arrays.asList(a);
        offkeys = Arrays.asList(b);

        for (int i = 0; i < 5000000; i++ ) {
            data.add(i);
            big.put(i, new Entry("" + (i+1),"" +(i+3) ));
        }

    }

    @Benchmark
    public void t1_SmallTest() {

        Map<Integer, Entry> small = new HashMap<>();

        keys.forEach(k -> {
            small.put(k, big.get(k));
        });

        data.forEach(d -> {
            if (keys.contains(d)) {
                Entry e = small.get(d);
                e.doSomething();
            }
        });


    }


    @Benchmark
    public void t2_BigTest() {
        data.forEach(d -> {
            if (keys.contains(d)) {
                Entry e = big.get(d);
                e.doSomething();
            }
        });

    }

    @Benchmark
    public void t3_NullCheck() {
        offkeys.forEach(d -> {
            Entry e = big.get(d);

            if (e != null) {
                e.doSomething();
            }
        });

    }

    @Benchmark
    public void t4_ContainsCheck() {
        offkeys.forEach(d -> {
            if (big.containsKey(d)) {
                Entry e = big.get(d);
                e.doSomething();
            }
        });

    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(MapBenchmark.class.getSimpleName())
            .warmupIterations(5)
            .measurementIterations(5)
            .mode(Mode.All)
            .forks(1)
            .build();

        System.out.println("Test started");
        new Runner(opt).run();
    }


}
