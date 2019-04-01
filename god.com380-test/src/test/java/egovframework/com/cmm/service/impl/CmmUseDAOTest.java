package egovframework.com.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath*:egovframework/spring/com/**/context-*.xml" })

@ActiveProfiles({ "mysql", "dummy" })
// VM arguments:
// -Dspring.profiles.active=mysql,dummy

public class CmmUseDAOTest {

	protected Logger egovLogger = LoggerFactory.getLogger(CmmUseDAOTest.class);

	@Resource(name = "cmmUseDAO")
	private CmmUseDAO cmmUseDAO;

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
