package model;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SpiderChart extends ApplicationFrame {

    private static final int OBSERVATIONS = 2;
    private static final int VARIABLES = 6;
    private static final Random r = new Random();
    private JFreeChart chartj;
    static String[] eixos = {"Capital", "Empreendedor", "Gestao", "Impacto", "Mercado", "Tecnologia"};
    static ArrayList<Diagnosis> diagnostico;
    public SpiderChart(String s, ArrayList<Diagnosis> diagnosticos) {
        super(s);
        this.diagnostico = diagnosticos;
        add(createDemoPanel());
    }

    private static CategoryDataset createDataset() {
        HashMap<String, Integer> eixoScore = new HashMap<String,Integer>();
        String eixo= "";
        for(int i = 0; i < diagnostico.size(); i++)
        {
            if (eixoScore.containsKey(diagnostico.get(i).getEixo())){
                eixoScore.put(diagnostico.get(i).getEixo(), eixoScore.get(diagnostico.get(i).getEixo()) + diagnostico.get(i).getScore());
            }
            else
            {
                eixoScore.put(diagnostico.get(i).getEixo(),diagnostico.get(i).getScore());
            }
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 1; i <= OBSERVATIONS; i++) {
            String rowKey = "";
            switch (i)
            {
                case 1:
                    rowKey = "Diagnóstico";
                    break;
                case 2:
                    rowKey = "Totalização";
                    break;
                default:
                    break;
            }

            for (int j = 1; j <= VARIABLES; j++) {
                Comparable colKey = eixos[j-1]/*Character.valueOf((char) (j + 64))*/;
                if(i==2)
                {
                    dataset.addValue(25,rowKey,colKey);
                }
                else {
                    dataset.addValue(eixoScore.get(eixos[j-1].toLowerCase()), rowKey, colKey);
                }
            }
        }
        return dataset;
    }

    public JFreeChart getchart() {
        return chartj;
    }

    public JFreeChart createChart(CategoryDataset dataset) {
        SpiderWebPlot plot = new SpiderWebPlot(dataset);
        JFreeChart chart = new JFreeChart("Gráfico Diagnóstico", plot);
        chartj = chart;
        return chart;
    }

    public JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart(createDataset());
        System.out.println(jfreechart.getPlot().toString());
        return new ChartPanel(jfreechart);
    }
}