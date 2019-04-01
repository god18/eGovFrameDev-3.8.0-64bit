package egovframework.com.cmm.service.impl;

import java.util.List;

import org.junit.Test;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;

public class CmmUseDAOSelectCmmCodeDetailTest extends CmmUseDAOTest {

	@Test
	public void test() throws Exception {
		egovLogger.debug("god");

		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM001");

		List<CmmnDetailCode> results = cmmUseDAO.selectCmmCodeDetail(vo);

		egovLogger.debug("results: {}", results);

		for (CmmnDetailCode result : results) {
			egovLogger.debug("result: {}", result);
			egovLogger.debug("getCodeId: {}", result.getCodeId());
			egovLogger.debug("getCode: {}", result.getCode());
			egovLogger.debug("getCodeNm: {}", result.getCodeNm());
			egovLogger.debug("getCodeDc: {}", result.getCodeDc());
		}
	}

}
