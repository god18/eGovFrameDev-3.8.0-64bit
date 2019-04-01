package egovframework.com.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import god.com.cmm.service.impl.GodComAbstractTest;

public class CmmUseDAOSelectOgrnztIdDetailTest extends GodComAbstractTest {

	@Resource(name = "cmmUseDAO")
	private CmmUseDAO cmmUseDAO;

	@Test
	public void test() throws Exception {
		egovLogger.debug("god");

		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setDetailCondition("ORGNZT_0000000000000-");

		List<CmmnDetailCode> results = cmmUseDAO.selectOgrnztIdDetail(vo);

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
