/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

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
