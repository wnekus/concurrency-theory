import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.LinkedList;

public class ChartDrawer{
    private XYChart chart;
    private LinkedList<Time> producersTime;
    private LinkedList<Time> consumersTime;

    public ChartDrawer() {
        producersTime = new LinkedList<>();
        consumersTime = new LinkedList<>();

        chart = new XYChartBuilder().width(600).height(500).title("Producer - Consumer").xAxisTitle("Number of goods").yAxisTitle("Time(ms)").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setMarkerSize(8);
    }

    public void createTabs(){
        double[] producersTimes = new double[producersTime.size()];
        double[] producersGoods = new double[producersTime.size()];
        double[] consumersTimes = new double[consumersTime.size()];
        double[] consumersGoods = new double[consumersTime.size()];

        for(int i = 0; i<producersTime.size(); i++){
            Time time = producersTime.removeFirst();
                producersTimes[i] = time.getTime();
                producersGoods[i] = time.getNumberOfGoods();

        }

        for(int i = 0; i<consumersTime.size(); i++){
            Time time = consumersTime.removeFirst();
                consumersTimes[i] = time.getTime();
                consumersGoods[i] = time.getNumberOfGoods();

        }

        addSeries(producersTimes, producersGoods, consumersTimes, consumersGoods);
    }

    public void uploadData(Time time){
        if(time.getWorker() == Worker.CONSUMER){
            consumersTime.add(time);
        }
        else producersTime.add(time);
    }

    public void addSeries(double[] producersTimes, double[] producersGoods, double[] consumersTimes, double[] consumersGoods){
        chart.addSeries("Producers", producersGoods, producersTimes);
        XYSeries series = chart.addSeries("Consumers", consumersGoods, consumersTimes);
        series.setMarker(SeriesMarkers.DIAMOND);
    }

    public void display(){
        new SwingWrapper(chart).displayChart();
    }

}
