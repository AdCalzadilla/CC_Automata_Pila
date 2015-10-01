package es.ull.cc.pda;

public class Key {
	private String coor_x;
	private String coor_y;
	
	public Key(String coor_x, String coor_y){
		this.coor_x = coor_x;
		this.coor_y = coor_y;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if (!(obj instanceof Key)){
			return false;
		}
		Key theKey = (Key) obj;
		return coor_x.equals(theKey.coor_x) && coor_y.equals(theKey.coor_y);
	}

	@Override
	public int hashCode() {
		char c1 = coor_x.charAt(0);
		char c2 = coor_y.charAt(0);
		int result = c1;
        result = 31 * result + c2;
        return result;
	}

	@Override
	public String toString() {
		return "("+ coor_x +", "+coor_y+")";
	}
	
	
	
	
}
