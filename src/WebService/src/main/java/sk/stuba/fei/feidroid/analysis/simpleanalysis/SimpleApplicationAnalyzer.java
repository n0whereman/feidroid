package sk.stuba.fei.feidroid.analysis.simpleanalysis;

import java.util.ArrayList;
import java.util.List;

import sk.stuba.fei.feidroid.analysis.ApplicationAnalyzer;
import sk.stuba.fei.feidroid.analysis.analysisresult.AnalysisResultResource;
import sk.stuba.fei.feidroid.entities.Application;
import sk.stuba.fei.feidroid.entities.PermissionUsage;

public class SimpleApplicationAnalyzer implements ApplicationAnalyzer<SimpleAnalysisResult> {
	public final String FILES_DIRECTORY = "PermissionGroups/";
	public final static Double MAX_SCORE = 100d;
	private StringArrayParser parser;
	private ArrayList<String> suspiciousPermissions;

	public SimpleApplicationAnalyzer() {
		parser = new StringArrayParser();
	}

	@Override
	public SimpleAnalysisResult analyze(Application application) {
		suspiciousPermissions = new ArrayList<String>();

		double categoryScore = calculateScoreFromCategory(application);
		double colorScore = calculateScoreFromColor(application);
		int permissionCount = application.getPermissions().size();

		permissionCount = permissionCount == 0 ? 1 : permissionCount;

		categoryScore = categoryScore / permissionCount / 5 * 100;
		colorScore = colorScore / permissionCount / 5 * 100;

		SimpleAnalysisResult result = new SimpleAnalysisResult();
		result.setScore(categoryScore + colorScore);
		result.setSuspiciousPermissions(suspiciousPermissions);
		result.setDescription("Suspicious permissions: " + suspiciousPermissions.toString());

		return result;
	}

	private double calculateScoreFromColor(Application application) {
		ArrayList<Category> colors = new ArrayList<Category>();
		List<PermissionUsage> appPermissions = application.getPermissions();
		double score = 0;

		colors.add(Category.Green);
		colors.add(Category.Yellow);
		colors.add(Category.Orange);
		colors.add(Category.Red);

		for (Category color : colors) {
			List<String> colorPermissions = parseDataFromXml(color.getFilename());

			for (PermissionUsage appPermission : appPermissions) {
				if (colorPermissions.contains(appPermission.getPermission().getTitle())) {
					score += color.getScore();
				}
			}
		}

		return score;
	}

	private double calculateScoreFromCategory(Application application) {
		double score = 0;
		if (application.getCategories() == null || application.getCategories().size() == 0) {
			return score;
		}

		String appCategory = application.getCategories().get(0).getTitle();
		try {
			Category category = Category.valueOf(appCategory);
			List<String> categoryPermissions = parseDataFromXml(category.getFilename());

			if (categoryPermissions != null) {
				for (PermissionUsage appPermission : application.getPermissions()) {
					for (String catPermission : categoryPermissions) {
						if (appPermission.getPermission().getTitle().equals(catPermission)) {
							score += category.getScore();
							suspiciousPermissions.add(catPermission);
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			score = 0;
		}

		return score;
	}

	private List<String> parseDataFromXml(String filename) {
		return parser.parse(FILES_DIRECTORY + filename);
	}

	private enum Category {
		Communication(2.4, "communications.xml"), Entertainment(5.5, "entertainment.xml"), Finance(3.25, "finance.xml"), Games(4.25, "games.xml"), Health(
		    3.75, "health.xml"), Lifestyle(1.75, "lifestyle.xml"), Multimedia(2, "multimedia.xml"), Productivity(2.75, "productivity.xml"), Shopping(3.5,
		    "shopping.xml"), Social(1.5, "social.xml"), Sports(3, "sports.xml"), Themes(4, "themes.xml"), Tools(1, "tools.xml"), Travel(3, "travel.xml"), Green(
		    0.5, "green_permissions.xml"), Yellow(1, "yellow_permissions.xml"), Orange(2, "orange_permissions.xml"), Red(5, "red_permissions.xml");

		private final double score;
		private final String file;

		Category(double score, String file) {
			this.score = score;
			this.file = file;
		}

		public double getScore() {
			return score;
		}

		public String getFilename() {
			return file;
		}
	}

	@Override
	public AnalysisResultResource convertResultToResource(SimpleAnalysisResult result) {
		return new SimpleAnalysisResultResource(result);
	}

}
