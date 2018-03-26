package basic;
/**
 * 扑克牌的花色
 * @author Plonk
 *
 */
public enum Color{
	HEART,SPADE,DIAMOND,CLUB,NULL;
	
	public static Color params(String str){
		if(str.equals(SPADE.name())){
			return SPADE;
		}else if(str.equals(HEART.name())){
			return HEART;
		}else if(str.equals(CLUB.name())){
			return CLUB;
		}else if(str.equals(DIAMOND.name())){
			return DIAMOND;
		}else{
			return NULL;
		}
	}
	
	// test
//	public static void main(String[] args) {
//		Color red = Color.HEARTS;
//		System.out.println((params(red.name())));
//		System.out.println(red.ordinal());
//		System.out.println(red.toString());
//	}
}