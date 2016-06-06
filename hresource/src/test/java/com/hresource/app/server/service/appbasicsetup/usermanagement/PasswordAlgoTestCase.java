package com.hresource.app.server.service.appbasicsetup.usermanagement;
import com.hresource.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hresource.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hresource.app.server.repository.appbasicsetup.usermanagement.PasswordAlgoRepository;
import com.hresource.app.shared.appbasicsetup.usermanagement.PasswordAlgo;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.hresource.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class PasswordAlgoTestCase extends EntityTestCriteria {

    @Autowired
    private PasswordAlgoRepository<PasswordAlgo> passwordalgoRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private PasswordAlgo createPasswordAlgo(Boolean isSave) throws Exception {
        PasswordAlgo passwordalgo = new PasswordAlgo();
        passwordalgo.setAlgoIcon("aO9lB9KdOLFppjta4HBFGNKIRc7c3e9Xitj090w8KBKb5KsNHP");
        passwordalgo.setAlgoName("fgTh8ucLphCZdSmGNUpF1xSPGbAIMDCDU7Peb1SCbq8aVlUENT");
        passwordalgo.setAlgoDescription("0q1oGnNDTRl0MlnlWsbCMufePsy2vCd3ZxyLUxsx5KGhTmXKdb");
        passwordalgo.setAlgorithm(8);
        passwordalgo.setAlgoHelp("y0SJeD70XgbhFcPo2yZWBps30AWMAspD5x4JFPf2zkjD6PsBZO");
        passwordalgo.setEntityValidator(entityValidator);
        return passwordalgo;
    }

    @Test
    public void test1Save() {
        try {
            PasswordAlgo passwordalgo = createPasswordAlgo(true);
            passwordalgo.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            passwordalgo.isValid();
            passwordalgoRepository.save(passwordalgo);
            map.put("PasswordAlgoPrimaryKey", passwordalgo._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            PasswordAlgo passwordalgo = passwordalgoRepository.findById((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
            passwordalgo.setAlgoIcon("aWXm3KdzoTY9pk6JgowzcYAf1RRbluwKwWHgW8VXbqxES9kM4O");
            passwordalgo.setAlgoName("36J5VxLwI72WXCYAjJkdlydlQyv0Cz7iJdiPeiDNl7uNK00cTy");
            passwordalgo.setAlgoDescription("sJfs8TqQfnKXgkJfoAj6ZtQAbr4kmtIUVmhF28mGLMqfH7c8PK");
            passwordalgo.setVersionId(1);
            passwordalgo.setAlgorithm(7);
            passwordalgo.setAlgoHelp("VgTIFNirdWcXR9UaBYXpt5SG2rOUqTN7RWVHwm1VB5H5eO1nj4");
            passwordalgo.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            passwordalgoRepository.update(passwordalgo);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            passwordalgoRepository.findById((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            passwordalgoRepository.delete((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validatePasswordAlgo(EntityTestCriteria contraints, PasswordAlgo passwordalgo) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            passwordalgoRepository.save(passwordalgo);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "algorithm", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "algorithm", 13));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "algoName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "algoName", "ddumnPX1JlNNLhbYjHiqvAYkmrkcfPj5C4gnf4ODxByUrLHJ7pOWYSI2rNQM8nIfAZEB9cDwSk6btHi5P2s09vvtBVyrXto64985qgiSuA2LwzKaWvM1Rd15ymEjP55qiTxXkbLXY0iWNX9fWkRI71RMmaNQTP9S9YwsifJMMzc1R3mw060DKzGLDqsT93RezMpVoZfhxr3HZ0WF2AUZHY1Ju7LVYI2cpyi7gdfWxIZU2A39fdNdtEZDZOeNPisRx"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "algoDescription", "IOUFDFfgEvzR7BwhsJSatDfHNicTrCTXaIVnr2tAfyn6LDAHXPMr4djeJsMlJ0CcmL3WkH7hHCEGyTMklEOQ7sHWKZWUnizsE7krs46fxMNh6JIAZsXysEbISp7YDf4AD0lzFpAeBI1goLc9unPBTBgo98PvSDZi9xkfJ4PEsJLJiFkVrixgKEDDDooR5HzEdVCK4CxgUfvT6WO0ZIZNXncpT3TYsVKem8OdFqhaXHY6szEwMk6VIv7KtRApHLuaZ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "algoIcon", "YvFk2CRFsV1mRE7POVuqF0wGtPbd8plnUIqZsplgQQ4XdzIrCBHHeXoXJu7OP0rW7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "algoHelp", "NX8HGcuiocVj42B3BhvtNYVmXcAQyXX7ymXHBXoCByu32XA4hxjXOnBtGhNH8j5lCAYmGYvUSTe0K6IluCAVGyjMvyHp27f021rYT8jbTstiV8SEwJugG9wNkaM6iA6qTuJdXw6scO2bin7zmrMju759rMxFIkmx2jqLpMF69kZiYez41VmRqRePMpODf46MvxkOG8ojROGvgZ3j10EYNpMtVZLtSVp6DOiJweIf9H92CKzJ2yH0XPpGgiovo96VD"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                PasswordAlgo passwordalgo = createPasswordAlgo(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = passwordalgo.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(passwordalgo, null);
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 2:
                        passwordalgo.setAlgorithm(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(passwordalgo, null);
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 4:
                        passwordalgo.setAlgoName(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 5:
                        passwordalgo.setAlgoDescription(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 6:
                        passwordalgo.setAlgoIcon(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 7:
                        passwordalgo.setAlgoHelp(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
