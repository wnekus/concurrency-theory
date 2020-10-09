import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.util.Arrays;

public class ChartDrawer {
    private CategoryChart chart;

    public ChartDrawer(Integer[] counterValues){
        chart = new CategoryChartBuilder().width(800).height(600).title("Counter value").xAxisTitle("Attempt").yAxisTitle("Value").build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);

        chart.addSeries("Counter value",null, Arrays.asList(counterValues));
    }

    public void show(){
        new SwingWrapper(chart).displayChart();
    }

    public Integer[] getTable(){
        Integer[] attempt = new Integer[100];

        for(int i=0;i<100;i++){
            attempt[i] = i+1;
        }
        return attempt;
    }
}
