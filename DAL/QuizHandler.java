package DAL;

import BLL.character.player.Quiz;
import DAL.ACQ.Loadable;
import DAL.yaml.YamlObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class QuizHandler implements Loadable {
	private List<Quiz> quizes;

	QuizHandler() {
		this.quizes = null;
	}

	List<Quiz> getQuizes() {
		return quizes;
	}

	@Override
	public void load() throws IOException {
		YamlObject parser = new YamlObject(new File("./src/DAL/resource/quizdatabase.yaml"));

		Map<Integer, Map<String, Object>> database = parser.getYaml().load(new FileReader(parser.getFile()));

		if(!database.isEmpty()) {
			quizes = new ArrayList<>(database.size());

			String question;
			List<String> opList;
			String[] options;
			Integer answer;

			for (Map<String, Object> map : database.values()) {
				question = (String) map.get("question");

				opList = (List<String>) map.get("options");
				options = opList.toArray(new String[opList.size()]);

				answer = (Integer) map.get("answer");

				quizes.add(new Quiz(question, options, answer));
			}
		}
	}
}
