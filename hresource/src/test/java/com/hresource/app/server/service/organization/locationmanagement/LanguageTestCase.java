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
import com.hresource.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.hresource.app.shared.organization.locationmanagement.Language;
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
public class LanguageTestCase extends EntityTestCriteria {

    @Autowired
    private LanguageRepository<Language> languageRepository;

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

    private Language createLanguage(Boolean isSave) throws Exception {
        Language language = new Language();
        language.setAlpha4parentid(10);
        language.setLanguageIcon("fqPg4YDEj4EfwLc62mbJl8iN62jhtY45sTISUoRhamDBVd0mbZ");
        language.setLanguageDescription("cldsIfgwY5tNzLxL1nEAqdng1mEryaBQ7xFHk00f9kcDkMqry6");
        language.setLanguage("9pJky2GyffNvLSE23DDoGootGMkDAKqGs9t1zpoIg8r3bXuASF");
        language.setAlpha4("KkeQ");
        language.setAlpha2("jj");
        language.setLanguageType("yyasPUvaXX5kmjFJvhXUt3hf6uAYh2Ss");
        language.setAlpha3("NhF");
        language.setEntityValidator(entityValidator);
        return language;
    }

    @Test
    public void test1Save() {
        try {
            Language language = createLanguage(true);
            language.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            language.isValid();
            languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LanguagePrimaryKey"));
            Language language = languageRepository.findById((java.lang.String) map.get("LanguagePrimaryKey"));
            language.setAlpha4parentid(1);
            language.setLanguageIcon("wnPa0YahVvNI2rCn710ckRKWaInQP7NOJXxxNClSnidzARgcag");
            language.setLanguageDescription("Mr2TSffraVrI6oQTxNZW5K1z5NormoFDYRnq5c0yXeRfXw4EaS");
            language.setLanguage("u8ebZJgKBBryg0ROOM9mB8CMm58FX5XmU1pOk2UpfajgXrJMsa");
            language.setAlpha4("p2HQ");
            language.setAlpha2("qF");
            language.setLanguageType("pNtwhmWFhQWDSkfypHas0lVh0n8GCeLe");
            language.setVersionId(1);
            language.setAlpha3("fb9");
            language.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            languageRepository.update(language);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LanguagePrimaryKey"));
            languageRepository.findById((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LanguagePrimaryKey"));
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLanguage(EntityTestCriteria contraints, Language language) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            language.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            language.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            language.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            languageRepository.save(language);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "language", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "language", "cVVV5QnyupIN3q3SPjfqVfH8JrmEAEvhzpa4ErigSmj86oUOZBnjeIkiRurNBtYvYDMrqt04UDP7HauY2apGevOtD632dxPGjH0ztnUnLNuZyWwQrEZpHNhon6K4IMt7X18PnnM8wjjanQXrLNciGb5EWG7rgrUFiTgRR2y8GECi8w96WfdEfoNhQalJLUEPhjQ9p0LVkf97biyiWlGzPB27WdsahkC7tytrgdsQsiTJCxLm4zvPIdy76zOzQpnXd"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "languageType", "ugjExvE6CjnBDPQAl9ZKEZq76hEIjvwjN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "languageDescription", "LGsMtl1dnxoHm2ALrLYX9PG2MJWLMjZFncO3PiFPv1fAhtR79hyXCwAh2dYHVVcS2dxYnmEuOGvPjylOxQKWm8ShjKbrhRrNUyUaMTbPuC8TOkfmLumu8QrmRWNykdIUavp8e6CCIE7KEmbfWVDWhm0rJPPf38XvI2Xh5CqqCuBFiUvnjgwsF1Zyi45y3ZbsVkLWF7g800SfbdOge7bNakqBAUvR0aNaRxttM8TFb2lP306FfKg2DumtDAPYOvg36"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "languageIcon", "plNqmMKWeNwfpbyg9DDVnb5x2YOSFwiVNWJwcKH7PKoWHCfwDw7zeRffackReAWDNZ892S7aQsGk1yiOdqwBD58wiYizwy6WpytvwpcST7HpRcZgA64LBRy4nta9PdJi0"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "alpha2", "DsQ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "alpha3", "26C1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "alpha4", "03CW3"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "alpha4parentid", 22));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Language language = createLanguage(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = language.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(language, null);
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 2:
                        language.setLanguage(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 3:
                        language.setLanguageType(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 4:
                        language.setLanguageDescription(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 5:
                        language.setLanguageIcon(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 6:
                        language.setAlpha2(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 7:
                        language.setAlpha3(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 8:
                        language.setAlpha4(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 9:
                        language.setAlpha4parentid(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLanguage(contraints, language);
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
