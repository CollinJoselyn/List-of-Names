
/**
 * BaseballPlayer is a class that collects information
 * about the player. Information includes the name, team,
 * position, and age.
 * @author Collin
 * Date 10/29/2017
 * @version 1.8
 */
public class BaseballPlayer {
	private String name;
	private String team;
	private String position;
	private String age;
	
	/**
	 * No argument constructor that initializes the variables to 
	 * empty Strings.
	 */
	public BaseballPlayer(){
		name = "";
		team = "";
		position = "";
		age = "";
	}
	
	/**
	 * This constructor takes one argument.
	 * @param name -Takes the player name.
	 */
	public BaseballPlayer(String name){
		this.name = name;
	}

	/**
	 * A String method that gets the name.
	 * @return the player name. 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Mutator method setName which will set the player name
	 * @param name is the player name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Accessor method named getTeam. 
	 * @return the team.
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * Mutator method named setTeam. This will set the team.
	 * @param team is the team name.
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	/**
	 * Accessor method named getPosition. 
	 * @return this will return the position the player plays.
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Mutator method named setPosition. This will set the players position
	 * @param position is the position name.
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * Accessor method named getAge. 
	 * @return this returns the players age.
	 */
	public String getAge() {
		return age;
	}

	/**
	 * Mutator method named setAge. This will set the players age.
	 * @param age is the players age. 
	 */
	public void setAge(String age) {
		this.age = age;
	}
	
}
