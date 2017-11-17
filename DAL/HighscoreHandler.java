package DAL;

import BLL.scoring.Score;
import DAL.ACQ.Loadable;
import DAL.ACQ.Savable;
import DAL.yaml.YamlObject;

import java.io.*;
import java.util.*;

class HighscoreHandler implements Savable, Loadable {
	private List<Score> highscore;

	HighscoreHandler() {
		this.highscore = null;
	}

	List<Score> getHighscore() {
		return highscore;
	}

	@Override
	public void load() throws FileNotFoundException {
		YamlObject parser = new YamlObject(new File("./src/DAL/resource/highscore.yaml"));

		Map<Integer, Map<String, Object>> map = parser.getYaml().load(new FileReader(parser.getFile()));

		if(!map.isEmpty()) {
			highscore = new ArrayList<>();
			String name;
			int score;

			for (Map<String, Object> o : map.values()) {
				name = (String) o.get("name");
				score = (int) o.get("score");

				highscore.add(new Score(name, score));
			}
		}
	}

	@Override
	public void save() throws IOException {
		File file = new File("./src/DAL/resource/highscore.yaml");
		YamlObject yaml = new YamlObject(file);

		Map<Integer, HashMap<String, Object>> map = new HashMap<>();

		for (int i = 0; i < highscore.size(); i++) {
			HashMap<String, Object> data = new HashMap<>();

			data.put("name", highscore.get(i).getName());
			data.put("score", highscore.get(i).getScore());

			map.put(i, data);
		}

		yaml.getYaml().dump(map, new FileWriter(new File("./src/DAL/resource/highscore.yaml")));
	}
}
