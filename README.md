# JMH Tests for map performance

Needs maven to compile

#Run cmd line:
mvn clean install
java -jar target/benchmarks.jar MapBenchmark -bm all -wi 5 -i 5 -f 1

#Run in IntelliJ via main class (not recommended though)
Open the pom.xml (New > Project from Existing source.. > Select pom.xml). Use JDK 1.8 and language lvl 8
run mvn targets clean & install
Install JMH plugin
Right click under benchmark method and select Run (MapBenchmark.main)


Run takes about ~5mins to complete, results are something like this:
# Run complete. Total time: 00:05:11

Benchmark                        Mode    Cnt        Score        Error  Units
MapBenchmark.t1_SmallTest       thrpt      5        8.098 ±      0.319  ops/s
MapBenchmark.t2_BigTest         thrpt      5        8.198 ±      0.221  ops/s
MapBenchmark.t3_NullCheck       thrpt      5  1760157.995 ± 101310.006  ops/s
MapBenchmark.t4_ContainsCheck   thrpt      5  1633326.493 ±  79216.155  ops/s
MapBenchmark.t1_SmallTest        avgt      5        0.125 ±      0.005   s/op
MapBenchmark.t2_BigTest          avgt      5        0.124 ±      0.009   s/op
MapBenchmark.t3_NullCheck        avgt      5       ≈ 10⁻⁶                s/op
MapBenchmark.t4_ContainsCheck    avgt      5       ≈ 10⁻⁶                s/op
MapBenchmark.t1_SmallTest      sample     41        0.127 ±      0.002   s/op
MapBenchmark.t2_BigTest        sample     41        0.135 ±      0.019   s/op
MapBenchmark.t3_NullCheck      sample  68699       ≈ 10⁻⁶                s/op
MapBenchmark.t4_ContainsCheck  sample  65108       ≈ 10⁻⁶                s/op
MapBenchmark.t1_SmallTest          ss      5        0.118 ±      0.021   s/op
MapBenchmark.t2_BigTest            ss      5        0.124 ±      0.007   s/op
MapBenchmark.t3_NullCheck          ss      5       ≈ 10⁻⁵                s/op
MapBenchmark.t4_ContainsCheck      ss      5       ≈ 10⁻⁴                s/op


