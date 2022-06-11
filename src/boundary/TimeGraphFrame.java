package boundary;

import control.TimerController;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class TimeGraphFrame {
    String userId;
    TimerController timerController;

    public TimeGraphFrame(String userId) {
        this.userId = userId;
        timerController = new TimerController();
    }
//
//    public static void main(final String[] args) {
//        TimeGraphFrame demo = new TimeGraphFrame();
//        JFreeChart chart = demo.getChart();
//        ChartFrame frame=new ChartFrame("공부시간그래프",chart);
//        frame.setSize(1000,800);
//        frame.setVisible(true);
//        frame.setResizable(false);
//    }

    public JFreeChart getChart() {
        Map<String, String> studyTimeForMonth = timerController.getStudyTimeForMonth(userId);

        // 데이터 생성
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //List<Date> xData = new ArrayList<Date>();
        String xValue[]=new String[21];
        LocalDate now= LocalDate.now();
        String today=String.valueOf(now.getDayOfMonth());
        String monthValue = String.valueOf(now.getMonthValue());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;

        Calendar cal = Calendar.getInstance();
        //오늘로부터 3주 동안의 기록
        for(int i = 20; i >= 0; i--) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
            xValue[i]=sdf.format(cal.getTime());
            xValue[i]=xValue[i].substring(5);
        }

        for(int i = 0; i < 20; i++) {
            // 데이터 입력 ( 값, 범례, 카테고리 )
            // 여기서 받아오면 되는듯
            dataset.addValue(i*0.5, "" , xValue[i]);
        }


        // 렌더링 생성 및 세팅
        final LineAndShapeRenderer renderer = new LineAndShapeRenderer();

        // 공통 옵션 정의
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
        final ItemLabelPosition p_below = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
        Font f = new Font("Gulim", Font.BOLD, 10);
        Font axisF = new Font("Gulim", Font.PLAIN, 10);

        // 렌더링 세팅
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseShapesVisible(true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setBaseFillPaint(Color.WHITE);
        renderer.setBaseItemLabelFont(f);
        renderer.setBasePositiveItemLabelPosition(p_below);
        renderer.setSeriesPaint(0,new Color(219,121,22));
        renderer.setSeriesStroke(0,new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3.0f));

        // plot 생성
        final CategoryPlot plot = new CategoryPlot();
        // plot 에 데이터 적재
        plot.setDataset(dataset);
        plot.setRenderer(renderer);

        // plot 기본 설정
        plot.setOrientation(PlotOrientation.VERTICAL);			// 그래프 표시 방향
        plot.setRangeGridlinesVisible(true);					// X축 가이드 라인 표시여부
        plot.setDomainGridlinesVisible(true);					// Y축 가이드 라인 표시여부

        // 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 즉, 먼저 등록한게 아래로 깔림 )
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        // X축 세팅
        plot.setDomainAxis(new CategoryAxis());			// X축 종류 설정
        plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);		// 카테고리 라벨 위치 조정

        // Y축 세팅
        plot.setRangeAxis(new NumberAxis());			// Y축 종류 설정
        plot.getRangeAxis().setTickLabelFont(axisF);	// Y축 눈금라벨 폰트 조정

        // 세팅된 plot을 바탕으로 chart 생성
        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("공부 시간 기록"); // 차트 타이틀
        return chart;
    }
}
