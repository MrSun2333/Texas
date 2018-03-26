package basic;
/**
 * 
 * @author Plonk
 *
 */
public class Poker implements Comparable<Poker>{
	private Color color;
	
	/*
	 * 0	1	2	3	4	5	6	7	8	9	10	11	12
	 * 2	3	4	5	6	7	8	9	10	J	Q	K	A
	 */
	
	private int point = -1;
	private String pointStr;// J, Q, K, A
	
	public Poker(String point){
		this(Color.NULL,point);
	}
	
	public Poker(Color color, String pointStr) {
		this.color = color;
		this.pointStr = pointStr;
		this.point = parsePoint(pointStr);
	}
	
	public Poker(Color color,int point){
		this.color = color;
		this.point = point;
		this.pointStr = parsePoint(point);
	}
	
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.pointStr = point;
		this.point = parsePoint(point);
	}
	public String getPointStr(){
		return pointStr;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(this.point != ((Poker)obj).point){
			return false;
		}
		if(this.getColor() == Color.NULL || ((Poker)obj).getColor() == Color.NULL){
			return true;
		}
		if(this.getColor() == ((Poker)obj).getColor()){
			return true;
		}
		return false;
	}

	public int compareTo(Poker o) {
		return this.point>o.point?1:-1;
	}
	
	public int parsePoint(String point){
		if(point.equals("J")){
			return 9;
		}else if(point.equals("Q")){
			return 10;
		}else if(point.equals("K")){
			return 11;
		}else if(point.equals("A")){
			return 12;
		}else{
			return Integer.parseInt(point)-2;
		}
	}
	
	public String parsePoint(int point){
		if(point == 9){
			return "J";
		}else if(point == 10){
			return "Q";
		}else if(point == 11){
			return "K";
		}else if(point == 12){
			return "A";
		}else{
			return (point+2)+"";
		}
	}
	
}

	
