package model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.jfree.chart.ChartUtilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Gerar_PDF {
    public  Document document;
    public  Font f1;
    public  Paragraph paragraph;
    public  String alias_date;

    public Gerar_PDF(Enterprise empresa, ArrayList<Diagnosis> diagnosticos, Consultant consultor)
    {
        //gera alias baseado na data para gerar o nome dos documentos
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        alias_date = formatter.format(date).trim().replace("-", "").replace(":","").replace(" ","");

        //gera primeira pagina e gráficos
        document = new Document();
        paragraph = new Paragraph();
        try {
            FileOutputStream  fos = new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\java\\docs\\Diagnostico_" + alias_date +".pdf");
            PdfWriter writer = PdfWriter.getInstance(document, fos);
            document.open();
            addParagraf(1);
            addTitle();
            addParagraf(2);
            addLine();
            f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20);
            addParagraf(1);
            addText(" Dados da Empresa:",FontFactory.getFont(FontFactory.TIMES_ROMAN, 20));
            f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15);
            addParagraf(1);
            addText(String.format("%-40s", "   Nome: " + empresa.getName()) + String.format("%-32s"," Programa: " + empresa.getIdPrograma()) + String.format("%-20s"," Advisor: " +  consultor.getName()),FontFactory.getFont(FontFactory.TIMES_ROMAN, 14));
            addText(String.format("%-32s", "   Telefone: " + empresa.getPhone()) +String.format("%-25s"," Email: " +  empresa.getEmail()) +  String.format("%-20s", " Site: " +empresa.getSite()),FontFactory.getFont(FontFactory.TIMES_ROMAN, 14));
            addText(String.format("%-30s", "   Data Fundação: " + empresa.getLifetime().split(" ")[0].replace('-','/')) + String.format("%-25s", " Data Adesão: " + empresa.getRegistrationDate().split(" ")[0].replace('-','/')) + " Data Diagnóstico: " + String.format("%-10s", empresa.getRegistrationDate().split(" ")[0].replace('-','/')),FontFactory.getFont(FontFactory.TIMES_ROMAN, 14));
            addLine();
            addParagraf(3);
            addText(" Último Diagnóstico:",FontFactory.getFont(FontFactory.TIMES_ROMAN, 20));
            addParagraf(2);
            addChart(diagnosticos);
            addParagraf(2);
            String eixo= "";
            for(int i = 0; i < diagnosticos.size(); i++)
            {
                if(eixo.isEmpty()){
                    addText(" Eixo - "+ diagnosticos.get(i).getEixo(),FontFactory.getFont(FontFactory.TIMES_ROMAN, 18));
                    addParagraf(1);
                    addText("  Score - Pergunta",FontFactory.getFont(FontFactory.TIMES_ROMAN, 15));
                    addText("   0" + diagnosticos.get(i).getScore() + "    - " + diagnosticos.get(i).getPergunta(),FontFactory.getFont(FontFactory.TIMES_ROMAN, 13));
                    eixo = diagnosticos.get(i).getEixo();
                }
                else if (eixo.equals(diagnosticos.get(i).getEixo())){
                    addText("   0" + diagnosticos.get(i).getScore() + "    - " + diagnosticos.get(i).getPergunta(),FontFactory.getFont(FontFactory.TIMES_ROMAN, 13));
                }
                else if(!eixo.equals(diagnosticos.get(i).getEixo()))
                {
                    addParagraf(1);
                    addText(" Eixo - "+ diagnosticos.get(i).getEixo(),FontFactory.getFont(FontFactory.TIMES_ROMAN, 18));
                    addParagraf(1);
                    addText("  Score - Pergunta",FontFactory.getFont(FontFactory.TIMES_ROMAN, 15));
                    addText("   0" + diagnosticos.get(i).getScore() + "    - " + diagnosticos.get(i).getPergunta(),FontFactory.getFont(FontFactory.TIMES_ROMAN, 13));
                    eixo = diagnosticos.get(i).getEixo();
                }
            }
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }

    }
    public void closeDocument()
    {
        document.close();
    }
    public  void addParagraf(int i) throws DocumentException {
        paragraph = new Paragraph();
        for (int j = 0; j<i; j++)
        {
            paragraph.add("\n");
        }
        document.add(paragraph);
    }

    public  void addTitle() throws DocumentException {
        paragraph = new Paragraph();
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 30);
        paragraph.setFont(f1);
        paragraph.add("Relátorio de Diagnóstico");
        document.add(paragraph);
    }

    public  void addChart(ArrayList<Diagnosis> diagnosticos) throws IOException, DocumentException {
        paragraph = new Paragraph();
        SpiderChart spider = new SpiderChart("SpiderWebChart", diagnosticos);
        OutputStream out = new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\java\\docs\\SpiderChart_" + alias_date +".png");
        ChartUtilities.writeChartAsPNG(out,
                spider.getchart(),
                300,
                300);
        Image image = Image.getInstance(System.getProperty("user.dir")+"\\src\\main\\java\\docs\\SpiderChart_" + alias_date +".png");
        image.setAlignment(Image.ALIGN_CENTER);
        document.add(image);
    }

    public  void addText(String s, Font f) throws DocumentException {
        paragraph = new Paragraph();
        paragraph.setFont(f);
        paragraph.add(s);
        document.add(paragraph);
    }
    public  void addLine() throws DocumentException {
        document.add(new Paragraph(String.format("%-78s", " ").replace(' ','_')));
    }
}
