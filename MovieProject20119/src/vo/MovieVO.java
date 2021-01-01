package vo;

public class MovieVO {
	private int movieNo;
	private String movieName;
	private String category;
	private int runtime;
	private String img;
	
	public MovieVO() {}

	public MovieVO(int movieNo, String movieName, String category, int runtime, String img) {
		this.movieNo = movieNo;
		this.movieName = movieName;
		this.category = category;
		this.runtime = runtime;
		this.img = img;
	}

	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@Override
	public String toString() {
		return "[영화 번호 : " + movieNo + "] [영화 제목 : " + movieName + "] [장르 : " + category + "] [런타임 : " + runtime + "] [이미지 파일 번호 : " + img + "]";
	}
}