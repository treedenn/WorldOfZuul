package DAL;

import BLL.entity.player.Quiz;
import DAL.yaml.IOYaml;

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
	private IOYaml IOYaml;
	private List<Quiz> quizzes;

	/**
	 * Constructs a new Quiz handler.
	 * @param file the location of all the quizzes
	 */
	QuizHandler(File file) {
		this.IOYaml = new IOYaml(file);
		this.quizzes = null;
	}

	/**
	 * Gets all the quizzes.
	 * @return the quizzes
	 */
	List<Quiz> getQuizzes() {
		return quizzes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void load() throws IOException {
		Map<Integer, Map<String, Object>> database = IOYaml.getYaml().load(new FileReader(IOYaml.getFile()));

		if(!database.isEmpty()) {
			quizzes = new ArrayList<>(database.size());

			String question;
			List<String> opList;
			String[] options;
			Integer answer;

			for (Map<String, Object> map : database.values()) {
				question = (String) map.get("question");

				opList = (List<String>) map.get("options");
				options = opList.toArray(new String[opList.size()]);

				answer = (Integer) map.get("answer");

				quizzes.add(new Quiz(question, options, answer));
			}
		}
	}
}