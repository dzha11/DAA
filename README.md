Assignment 1: Divide-and-Conquer Algorithms Benchmark

Overview

This project implements classic divide-and-conquer algorithms and benchmarks their performance.
All algorithms are implemented in Java with safe recursion patterns, metrics collection, and visualization.

Algorithms included:
	1.	MergeSort (O(n log n), Master Theorem Case 2)
	•	Linear merge
	•	Reusable buffer
	•	Small-n cut-off (insertion sort)
	2.	QuickSort (robust)
	•	Randomized pivot
	•	Recurse on smaller partition, iterate over larger
	•	Bounded stack depth (~O(log n) typical)
	3.	Deterministic Select (Median-of-Medians, O(n))
	•	Groups of 5, median of medians as pivot
	•	In-place partition
	•	Recurse only into the needed side (prefer smaller side)
	4.	Closest Pair of Points (2D, O(n log n))
	•	Sort points by x
	•	Recursive split
	•	Check “strip” in y-order (scan 7–8 neighbors)

⸻

Project Structure
src/
├── main/java/algorithms/
│   ├── MainBenchmark.java   // Runs benchmarks and generates CSV
│   ├── MainPlot.java        // Plots time vs n and depth vs n
│   ├── Metrics.java         // Tracks comparisons, allocations, recursion depth
│   ├── Point.java           // Point class for ClosestPair
│   ├── MergeSort.java
│   ├── QuickSort.java
│   ├── DeterministicSelect.java
│   └── ClosestPair.java
└── test/java/algorithms/
    ├── MergeSortTest.java
    ├── QuickSortTest.java
    ├── DeterministicSelectTest.java
    └── ClosestPairTest.java

	Maven Setup
	•	Java 17
	•	Dependencies:
	•	JUnit5 for testing
	•	XChart for plotting graphs
	•	Build commands via IntelliJ IDEA Maven panel: clean → package

⸻

Benchmarking
	•	MainBenchmark.java runs all algorithms on different input sizes and trials.
	•	Output CSV: csv/results.csv

CSV columns: algorithm,n,trial,time_ns,comparisons,allocations,max_depth

	•	MainPlot.java generates graphs:
	•	Time vs n
	•	Max recursion depth vs n

Example CSV rows: mergesort,100,1,241500,537,198,8
quicksort,100,1,258792,646,0,11
select,100,1,1197292,0,0,0
closestpair,100,1,960250,4950,0,0

Metrics Collected
	•	Time (ns) — actual runtime in nanoseconds
	•	Comparisons — number of element comparisons
	•	Allocations — array/buffer allocations
	•	Max depth — recursion depth

⸻

Testing
	•	Sorting: random and adversarial arrays, recursion depth check
	•	Select: verify results against Arrays.sort(a)[k] for multiple trials
	•	Closest Pair: small n ≤ 2000 verified against O(n²) brute-force

All tests pass without errors.

⸻

Recurrence Analysis
	•	MergeSort: T(n) = 2T(n/2) + Θ(n) → Θ(n log n) (Master Case 2)
	•	QuickSort: T(n) = T(k) + T(n-k-1) + Θ(n) → Θ(n log n) average (random pivot)
	•	Deterministic Select: T(n) = T(n/5) + T(7n/10) + Θ(n) → Θ(n)
	•	Closest Pair: T(n) = 2T(n/2) + Θ(n) → Θ(n log n)

Practical measurements match theory, with slight variations due to cache effects and garbage collection.

⸻

GitHub Workflow
	•	Branches:
	•	main — stable releases (v1.0)
	•	feature/mergesort, feature/quicksort, feature/select, feature/closest, feature/metrics
	•	Commit storyline example:
	1.	init: Maven, JUnit5, CI, README
	2.	feat(metrics): Metrics counters, depth tracker, CSV writer
	3.	feat(mergesort): MergeSort + tests
	4.	feat(quicksort): QuickSort + tests
	5.	refactor(util): swap, partition, shuffle helpers
	6.	feat(select): Deterministic Select + tests
	7.	feat(closest): Closest Pair + tests
	8.	feat(cli): MainBenchmark + CSV output
	9.	bench(jmh): NanoTime benchmarking harness
	10.	docs(report): plots and analysis
	11.	fix: edge cases (duplicates, tiny arrays)
	12.	release v1.0: final working version

⸻

Usage
	1.	Build project:
	•	IntelliJ IDEA: Maven → clean → package
	2.	Run benchmark: java -cp target/algo1-1.0-SNAPSHOT.jar algorithms.MainBenchmark
	•	Generates csv/results.csv

	3.	Plot results: java -cp target/algo1-1.0-SNAPSHOT.jar algorithms.MainPlot
	•	Displays time vs n and depth vs n plots

⸻

Summary
	•	Implemented 4 classic divide-and-conquer algorithms
	•	Collected metrics for performance, allocations, recursion depth
	•	Validated theoretical analysis (Master Theorem / Akra–Bazzi) with practical measurements
	•	Plots visualize runtime and depth trends
	•	Project fully tested and ready for submission
