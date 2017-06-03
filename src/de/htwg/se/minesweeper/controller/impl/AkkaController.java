package de.htwg.se.minesweeper.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import org.apache.logging.log4j.core.appender.mom.kafka.KafkaAppender;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.inject.Inject;

import de.htwg.se.minesweeper.controller.IAkkaController;
import de.htwg.se.minesweeper.model.Cell;
import de.htwg.se.minesweeper.persistence.IGridDao;

public class AkkaController extends Controller implements IAkkaController {

	IAkkaController c;

	@Inject
	public AkkaController(IGridDao dao, IAkkaController c) throws IOException {
		super(dao);
		this.c = c;
	}

	@Override
	public String jsonObj() {
		JsonObject jsonObjectGrid = null;
		JsonObject jsonObjectCell = null;

		List<JsonObject> all = new ArrayList<>();

		jsonObjectGrid = Json.createObjectBuilder().add("State", c.getState().toString()).add("Points", "")

				.add("Grid",
						Json.createObjectBuilder().add("numberOfRows", c.getGrid().getNumberOfRows())
								.add("numberOfColumns", c.getGrid().getNumberOfColumns())
								.add("numberOfMines", c.getGrid().getNumberOfMines()))
				.build();
		all.add(jsonObjectGrid);
		for (Cell cell : c.getGrid().getCells()) {

			jsonObjectCell = Json.createObjectBuilder()
					.add("Cells", Json.createObjectBuilder()
							.add("position",
									Json.createObjectBuilder().add("row", cell.getPosition().getRow()).add("col",
											cell.getPosition().getCol()))
							.add("isFlagged", cell.isFlagged()).add("hasMine", cell.hasMine())
							.add("isRevealed", cell.isRevealed()).add("surroundingMines", cell.getSurroundingMines()))

					.build();

			all.add(jsonObjectCell);
		}

		return prettyJSON(all.toString());
	}

	private static String prettyJSON(String resp) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(resp);

		return gson.toJson(je);
	}

}