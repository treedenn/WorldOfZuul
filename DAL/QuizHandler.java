package DAL;

import BLL.entity.player.Quiz;
import DAL.ACQ.Loadable;
import DAL.yaml.YamlObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Handles the quizzes. It loads all the quizzes.
 */
class QuizHandler implements Loadable {
	private YamlObject yamlObject;
	private List<Quiz> quizes;

	QuizHandler(File file) {
		this.yamlObject = new YamlObject(file);
		this.quizes = null;
	}

	/**
	 * Gets all the quizzes.
	 * @return the quizzes
	 */
	List<Quiz> getQuizes() {
		return quizes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void load() throws IOException {
		Map<Integer, Map<String, Object>> database = yamlObject.getYaml().load(new FileReader(yamlObject.getFile()));

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