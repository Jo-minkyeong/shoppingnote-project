package com.sharp.ing.domain;

import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("dataDAO")
public interface DataDAO {
	Logger logger = LoggerFactory.getLogger("controller.BoardController");

	public void insertShoppinglist(Shopping_listDTO listDTO) throws Exception;

	public void insertItem(ItemDTO itemDTO) throws Exception;
}
