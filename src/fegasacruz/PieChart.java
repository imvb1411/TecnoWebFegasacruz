/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fegasacruz;

/**
 *
 * @author Mijael
 */
import Entities.EstadisticaEntity;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class PieChart extends JFrame {

    private static final long serialVersionUID = 1L;
    private List<EstadisticaEntity> list;

    public PieChart(String applicationTitle, String chartTitle, List<EstadisticaEntity> list) {
        super(applicationTitle);
        this.list = list;
        // This will create the dataset
        PieDataset dataset = createDataset();
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample dataset
     */
    private PieDataset createDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        for (EstadisticaEntity entity : list) {
            result.setValue(entity.getLabel()+"="+entity.getValue(), Double.valueOf(entity.getValue()));
        }
//        result.setValue("Linux", 29);
//        result.setValue("Mac", 20);
//        result.setValue("Windows", 51);
        return result;

    }

    /**
     * Creates a chart
     */
    private JFreeChart createChart(PieDataset dataset, String title) {

        try {
            JFreeChart chart = ChartFactory.createPieChart3D(
                    title, // chart title
                    dataset, // data
                    true, // include legend
                    true,
                    false
            );
            ChartUtilities.saveChartAsJPEG(new File("chart.jpg"), chart, 500, 270);
            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
            return chart;
        } catch (IOException ex) {
            Logger.getLogger(PieChart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
