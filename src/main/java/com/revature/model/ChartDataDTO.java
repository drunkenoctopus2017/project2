package com.revature.model;

import java.util.ArrayList;
import java.util.List;

public class ChartDataDTO {
//app.value("chartData", {
//  "chart": {
//      "caption": "Monthly revenue for last year",
//      "subCaption": "Harry's SuperMart",
//      "xAxisName": "Month",
//      "yAxisName": "Revenues (In USD)",
//      "numberPrefix": "$",
//      "theme": "fint"
//  },
//  "data": [
//      {
//          "label": "Jan",
//          "value": "420000"
//      },
//      {
//          "label": "Feb",
//          "value": "810000"
//      },
//      {
//          "label": "Mar",
//          "value": "720000"
//      },
//      {
//          "label": "Apr",
//          "value": "550000"
//      },
//      {
//          "label": "May",
//          "value": "910000"
//      },
//      {
//          "label": "Jun",
//          "value": "510000"
//      },
//      {
//          "label": "Jul",
//          "value": "680000"
//      },
//      {
//          "label": "Aug",
//          "value": "620000"
//      },
//      {
//          "label": "Sep",
//          "value": "610000"
//      },
//      {
//          "label": "Oct",
//          "value": "490000"
//      },
//      {
//          "label": "Nov",
//          "value": "900000"
//      },
//      {
//          "label": "Dec",
//          "value": "730000"
//      }
//  ]
//});
	private Chart chart;
	private List<GraphCoordinate> data = new ArrayList<GraphCoordinate>();

	public ChartDataDTO(){
		chart = new Chart("Burndown Chart not finished");
		data.add(new GraphCoordinate("Mon","5123"));
		data.add(new GraphCoordinate("Tue","4233"));
		data.add(new GraphCoordinate("Wed","5507"));
		data.add(new GraphCoordinate("Thu","4110"));
		data.add(new GraphCoordinate("Fri","5529"));
		data.add(new GraphCoordinate("Sat","5803"));
		data.add(new GraphCoordinate("Sun","6202"));
	}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public List<GraphCoordinate> getData() {
		return data;
	}

	public void setData(List<GraphCoordinate> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ChartDataDTO [chart=" + chart + ", data=" + data + "]";
	}
}

class Chart{
	private String theme = "fint";
	private String caption;
	private String subCaption = "";
	private String xAxisName = "Days";
	private String yAxisName = "Points";
	private String showValues = "0";
	public Chart(String caption) {
		super();
		this.caption = caption;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getSubCaption() {
		return subCaption;
	}
	public void setSubCaption(String subCaption) {
		this.subCaption = subCaption;
	}
	public String getxAxisName() {
		return xAxisName;
	}
	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}
	public String getyAxisName() {
		return yAxisName;
	}
	public void setyAxisName(String yAxisName) {
		this.yAxisName = yAxisName;
	}
	public String getShowValues() {
		return showValues;
	}
	public void setShowValues(String showValues) {
		this.showValues = showValues;
	}
	@Override
	public String toString() {
		return "Chart [theme=" + theme + ", caption=" + caption + ", subCaption=" + subCaption + ", xAxisName="
				+ xAxisName + ", yAxisName=" + yAxisName + ", showValues=" + showValues + "]";
	}
}
class GraphCoordinate {
	
	private String label;
	private String value;
	
	public GraphCoordinate(String x, String y) {
		this.label = x;
		this.value = y;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String x) {
		this.label = x;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String y) {
		this.value = y;
	}
	@Override
	public String toString() {
		return "GraphCoordinate [label=" + label + ", value=" + value + "]";
	}
	
}