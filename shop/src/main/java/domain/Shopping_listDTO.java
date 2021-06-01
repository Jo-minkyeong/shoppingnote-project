package domain;

public class Shopping_listDTO {
	
	//사용자_id
	private String user_id;
	//영수증 번호
	public int list_id;
	//구매날짜
	public String purcahse_date;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPurcahse_date() {
		return purcahse_date;
	}
	public void setPurcahse_date(String purcahse_date) {
		this.purcahse_date = purcahse_date;
	}
	public int getList_id() {
		return list_id;
	}
}
