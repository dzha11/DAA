package algorithms;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MainPlot {

    public static void main(String[] args) throws IOException {
        String csvFile = "csv/results.csv";
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line = br.readLine(); // пропустить заголовок

        // структура: алгоритм -> размер n -> список времени
        Map<String, Map<Integer, List<Long>>> times = new HashMap<>();

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            String alg = parts[0];
            int n = Integer.parseInt(parts[1]);
            long time = Long.parseLong(parts[3]);

            times.putIfAbsent(alg, new HashMap<>());
            times.get(alg).putIfAbsent(n, new ArrayList<>());
            times.get(alg).get(n).add(time);
        }
        br.close();

        // строим один график для всех алгоритмов
        XYChart chart = new XYChartBuilder()
                .width(800).height(600)
                .title("Algorithm Benchmark Comparison")
                .xAxisTitle("Array Size n")
                .yAxisTitle("Time (ns)")
                .build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setMarkerSize(6);

        for (String alg : times.keySet()) {
            Map<Integer, List<Long>> data = times.get(alg);
            List<Integer> nValues = new ArrayList<>(data.keySet());
            Collections.sort(nValues);

            List<Double> avgTimes = new ArrayList<>();
            for (int n : nValues) {
                List<Long> trialTimes = data.get(n);
                double avg = trialTimes.stream().mapToLong(Long::longValue).average().orElse(0);
                avgTimes.add(avg);
            }

            chart.addSeries(alg, nValues, avgTimes);
        }

        new SwingWrapper<>(chart).displayChart();
    }
}
