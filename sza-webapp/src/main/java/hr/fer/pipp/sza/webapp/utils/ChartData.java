package hr.fer.pipp.sza.webapp.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import hr.fer.pipp.sza.webapp.modeli.Anketa;
import hr.fer.pipp.sza.webapp.modeli.Ispunjavanje;
import hr.fer.pipp.sza.webapp.modeli.Odgovor;
import hr.fer.pipp.sza.webapp.modeli.Pitanje;

public class ChartData {

	private static long suma = 0;

	public static List<String> getData(Anketa a) {
		List<String> data = new ArrayList<>();
		a.getPitanja().stream().forEach(p -> data.add(createData(p, a.getIspunjavanja())));
		return data;
	}

	private static String createData(Pitanje p, List<Ispunjavanje> ispunjavanja) {
		Map<Odgovor, Long> brojOdg = new HashMap<>();
		ispunjavanja.forEach(i -> brojOdg.compute(i.getOdgovori().get(p), (key, val) -> (val == null) ? 1 : val + 1));
		JsonArray data = new JsonArray();
		data.add(getDataSeries(getDataPoints(brojOdg, p), p.getTextPitanje()));
		Gson gson = new Gson();
		return gson.toJson(getSettings(getLegend(), getTooltip(), data));
	}

	private static JsonArray getDataPoints(Map<Odgovor, Long> brojOdgovora, Pitanje p) {
		JsonArray dataPoints = new JsonArray();
		suma = brojOdgovora.values().stream().mapToLong(Long::longValue).sum();
		DecimalFormat df = new DecimalFormat("#.##%");
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		for (Odgovor odg : p.getOdgovor()) {
			JsonObject jo = new JsonObject();
			jo.addProperty("y", brojOdgovora.getOrDefault(odg, (long) 0));
			jo.addProperty("p", df.format(brojOdgovora.get(odg) / ((double) suma)));
			jo.addProperty("label", odg.getTextOdgovor());
			jo.addProperty("exploded", true);
			dataPoints.add(jo);
		}
		return dataPoints;
	}

	private static JsonObject getDataSeries(JsonArray dataPoints, String name) {
		JsonObject dataSeries = new JsonObject();
		dataSeries.addProperty("name", name);
		dataSeries.addProperty("type", "pie");
		dataSeries.addProperty("startAngle", -90);
		dataSeries.addProperty("indexLabelFontFamily", "Sans");
		dataSeries.addProperty("indexLabelFontSize", 15);
		dataSeries.addProperty("indexLabel", "{label}: {p}%");
		dataSeries.addProperty("toolTipDataContent", "{label}: {y}");
		dataSeries.addProperty("showInLegend", true);
		dataSeries.addProperty("legendText", "{label}");
		dataSeries.add("dataPoints", dataPoints);
		return dataSeries;
	}

	private static JsonObject getTooltip() {
		JsonObject tooltip = new JsonObject();
		tooltip.addProperty("enabled", true);
		tooltip.addProperty("animationEnabled", true);
		return tooltip;
	}

	private static JsonObject getLegend() {
		JsonObject legend = new JsonObject();
		legend.addProperty("horizontalAlign", "left");
		legend.addProperty("verticalAlign", "center");
		legend.addProperty("fontFamily", "Sans");
		legend.addProperty("fontWeight", "lighter");
		legend.addProperty("fontColor", "black");
		legend.addProperty("fontSize", 20);
		return legend;
	}

	private static JsonObject getSettings(JsonElement legend, JsonElement tooltip, JsonElement data) {
		JsonObject subtitle = new JsonObject();
		subtitle.addProperty("text", "Ukupan broj glasova: " + suma);
		subtitle.addProperty("fontFamily", "Sans");
		JsonArray subtitles = new JsonArray();
		subtitles.add(subtitle);
		JsonObject settings = new JsonObject();
		settings.addProperty("animationEnabled", true);
		settings.addProperty("exportEnabled", true);
		settings.addProperty("exportFileName",
				data.getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString());
		settings.addProperty("theme", "theme1");
		settings.addProperty("height", 400);
		settings.add("subtitles", subtitles);
		settings.add("legend", legend);
		settings.add("toolTip", tooltip);
		settings.add("data", data);
		return settings;
	}

}
