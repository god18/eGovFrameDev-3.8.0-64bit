package god.com.cmm.service.impl;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath*:egovframework/spring/com/**/context-*.xml" })

// @ActiveProfiles({ "mysql", "dummy" })
@ActiveProfiles({ "maria", "dummy" })
// VM arguments:
// -Dspring.profiles.active=mysql,dummy
// -Dspring.profiles.active=maria,dummy

public class GodComAbstractTest {

	protected Logger egovLogger = LoggerFactory.getLogger(GodComAbstractTest.class);

}
