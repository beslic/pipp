package hr.fer.pipp.sza.webapp.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.modeli.Ispunjavanje;
import hr.fer.pipp.sza.webapp.modeli.Pitanje;

public class ChartData {

	public static List<String> getData(Anketa a) {
		List<String> data = new ArrayList<>();
		a.getPitanja().stream().forEach(p -> data.add(createData(p, a.getIspunjavanja())));
		return data;
	}

	private static String createData(Pitanje p, List<Ispunjavanje> ispunjavanja) {

		JsonArray dataPoints = new JsonArray();
		for (int i = 1; i <= 5; i++) {
			JsonObject jo = new JsonObject();
			jo.addProperty("y", i);
			jo.addProperty("indexLabel", "odgovor " + i);
			dataPoints.add(jo);
		}

		JsonObject dataSeries = new JsonObject();
		dataSeries.addProperty("name", "odgovori1"); // TODO redni broj odgovora
		dataSeries.addProperty("type", "pie"); // TODO dinamicki promijeniti
		dataSeries.addProperty("startAngle", -90);
		dataSeries.addProperty("showInLegend", true);
		dataSeries.addProperty("legenText", "{indexLabel}");
		dataSeries.add("dataPoints", dataPoints);

		JsonArray data = new JsonArray();
		data.add(dataSeries);

		JsonObject settings = getSettings(getLegend(), getTooltip(), data);

		Gson gson = new Gson();
		return gson.toJson(settings);
	}

	private static JsonObject getTooltip() {
		JsonObject tooltip = new JsonObject();
		tooltip.addProperty("enabled", true);
		tooltip.addProperty("animationEnabled", true);
		return tooltip;
	}

	private static JsonObject getLegend() {
		JsonObject legend = new JsonObject();
		legend.addProperty("fontFamily", "Arial");
		legend.addProperty("horizontalAlign", "left");
		legend.addProperty("verticalAlign", "center");
		return legend;
	}

	private static JsonObject getSettings(JsonElement legend, JsonElement tooltip, JsonElement data) {
		JsonObject settings = new JsonObject();
		settings.addProperty("animationEnable", true);
		settings.addProperty("exportEnable", true);
		settings.addProperty("exportFileName", "pitanje"); // TODO naziv pitanja
		settings.addProperty("theme", "theme2");
		settings.add("legend", legend);
		settings.add("toolTip", tooltip);
		settings.add("data", data);
		return settings;
	}

}
