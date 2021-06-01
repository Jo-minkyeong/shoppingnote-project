package domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface DataDAO {
	//spring boot는 알아서 메소드 이름과 id를 일치시켜주면 mapper와 자동으로 연결해주기 때문에 DAOImpl(implementation)이 필요 없음  
	Logger logger = LoggerFactory.getLogger("controller.BoardController");
	
	public void insertShoppinglist(Shopping_listDTO param) throws Exception;
	
	public void insertItem(ItemDTO param) throws Exception;

}
