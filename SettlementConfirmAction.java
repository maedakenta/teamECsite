package com.internousdev.florida.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.florida.dao.CartInfoDAO;
import com.internousdev.florida.dao.DestinationInfoDAO;
import com.internousdev.florida.dto.CartInfoDTO;
import com.internousdev.florida.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementConfirmAction extends ActionSupport implements SessionAware {

	private List<DestinationInfoDTO> destinationInfoDTOList;
	private Map<String, Object> session;

	public String execute() {

		String result = ERROR;
		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		List<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();
		String userId = null;

		int logined = Integer.parseInt(String.valueOf(session.get("logined")));
		if (logined == 1) {

			userId = session.get("userId").toString();

			cartInfoDTOList = cartInfoDAO.getCartInfo(userId);
			session.put("cartInfo", cartInfoDTOList);

			DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
			destinationInfoDTOList = destinationInfoDAO.getDestinationInfo(String.valueOf(session.get("userId")));

			result = SUCCESS;
		} else {
			session.put("cartFlag", "1");
			result = "login";
		}

		return result;
	}

	public List<DestinationInfoDTO> getDestinationInfoDTOList() {
		return destinationInfoDTOList;
	}

	public void setDestinationInfoDTOList(List<DestinationInfoDTO> destinationInfoDTOList) {
		this.destinationInfoDTOList = destinationInfoDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
