package com.hresource.app.server.service.organization.locationmanagement;
import com.hresource.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.hresource.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.hresource.app.server.repository.organization.locationmanagement.StateRepository;
import com.hresource.app.shared.organization.locationmanagement.State;
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
import com.hresource.app.shared.organization.locationmanagement.Country;
import com.hresource.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLongitude(2);
        country.setCapitalLatitude(11);
        country.setCountryName("UtOFpTY82njJXdWiC2WDT4HvXdwtNK8fcvVEtIGCtP5jEytZ7W");
        country.setCurrencyCode("LyL");
        country.setCapital("GktGxmCJfMEKULwitTENR0GyIyXA9dWB");
        country.setIsoNumeric(440);
        country.setCountryCode1("ba6");
        country.setCurrencySymbol("3pcFRiJAin8T9fLgs3ES6j7F163zKHUv");
        country.setCountryCode2("OfG");
        country.setCountryFlag("IeUbl2WdQBPyDPYOkdNuuAhPMKzETKrRiv8bqhbxyu7Y3c9SQE");
        country.setCurrencyName("efdqKH5o9f17kAzpysWdVpN7pzp7MsZlWI5t04ZVBIi9dqVhBb");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapitalLongitude(3);
        state.setStateCodeChar2("V3HJSKialSHbjaJ4iVQavZ2qwVLgFlCU");
        state.setStateCapital("wPdM74j3rMsCP7Dqck6Pd3ldvGj1NX4mKeewXvmLUC2lHCQuLP");
        state.setStateCodeChar3("LUuig6Wz5icCZhBQcMQJXqTUtd3Nh9Hf");
        state.setStateName("k28erHBdLuNtO2ETGlhfGYk9RRSZq0IZJFfjYKFkMFEg2jlB96");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateDescription("zkEjUGW6jORXtnE3T74MgzdnvOc3sFkVy4kabZNtvPz7YlJCBK");
        state.setStateFlag("SjPbBx7eNKsoJ8d9CpeU3wKHzwSGkLkqu1T3jbUW6yx8GDPZVe");
        state.setStateCode(2);
        state.setStateCapitalLatitude(3);
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateCapitalLongitude(10);
            state.setStateCodeChar2("fx8ShuplBXZ1Er3lg19wj4CDbbembIjN");
            state.setStateCapital("AHqYxddyjSiA66PKow310GEj4D48Ev0qPkoJ1rzJNov2YZ47r7");
            state.setStateCodeChar3("bIkIOwuy2e7k3D6r1tcdUC7cVgFCNYxW");
            state.setStateName("0dnEYeyliQLZAZDFpXzG1FM9XBTvF6go4DExeKIyPYRf30MNWc");
            state.setStateDescription("40q92oI6ogoqsmg3qLXgLYOqHM5VpkRWrO7P1j739U6O0TxCyc");
            state.setStateFlag("aD5eQImV9KiolaX6T9CPCGEtTQxMhlzwJbRbfm0yDJJoG17I8H");
            state.setStateCode(1);
            state.setVersionId(1);
            state.setStateCapitalLatitude(5);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "NjF9f7G8dEQYcHmOp0FAQnNNnp7cIHqfHGeN9gWiNLTHAeURcbNUOMW4Ax7doxL3FpsOV8VXoEvvCBca9RisTMiYNvoQsPrgpV8VkOJMIOYDtxcFKzqOhbwPqTwI9I0fIoFu54jTaKZOidkyrHZa9imk4C2ssXgID91ekymYarN38gQEEARuVnELXS37NdUtMlgymSCZk14U8Tfn9aCC2edI2dOQvCxkFgjU5UhYoP8oJwYniuEPdOm82HhbsRWFn"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "I1Tut9GYemiRGT5DCIy9HtpFXQv1W8bZu"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "BKYpRfGr96b5cBYuxHft0Lv1kxV7iSHuD"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "6BIWSQlt93P2v75BROy8peOZZooA8d1R52ioDO81SQLGDl3CaHEnPAVkcEMImnusOJf502lERgmEofv0HFtRlqXG3UPJ4q6boMnOzSccrMEmBpH3XFB7ClI3IwU680Hab2kjwUpppxAcDyoFnnbEKrpIEgiLVqitMGDkiUckX2QfAIBXdSNCna1YmX02jQ7x2zEwUy7Akol8UkWqOo3XzMbTejV00BkH7iFC2LBXxUxfd4gqhCvezo4BdPnFzbtMW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "IvgcN8OKplOyHgzYGTBjqxPe5RXAAXuCOIsO9DnEkUyunzNYpE0FDG6ao3DJiSR99cNmXNqWuWlSQKiix4V6H6mX8rWF1K2yS5adyYhZW7dWelKPPtImzFlTypak1mQKg"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "fDj8H0z7e4cvpIlfVBX1FaC9yF4ApNVY6YRfll4NtE63aMA02K8ZSeFyeWIKTJ226mxjRUm1cX3yqDV1jLplRuaSvvGv4IW1Kt7zmzotLxFCXWGrucU17oRIbBkfAGKv2"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 14));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 16));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
