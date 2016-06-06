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
import com.hresource.app.server.repository.appbasicsetup.usermanagement.PasswordPolicyRepository;
import com.hresource.app.shared.appbasicsetup.usermanagement.PasswordPolicy;
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
public class PasswordPolicyTestCase extends EntityTestCriteria {

    @Autowired
    private PasswordPolicyRepository<PasswordPolicy> passwordpolicyRepository;

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

    private PasswordPolicy createPasswordPolicy(Boolean isSave) throws Exception {
        PasswordPolicy passwordpolicy = new PasswordPolicy();
        passwordpolicy.setMinSmallLetters(5);
        passwordpolicy.setMinSpecialLetters(1);
        passwordpolicy.setAllowedSpecialLetters("e5Cbg0Kg95n7RP7DmDS5rQZs2mmxOR3i30Sim0JJhxjZh8Diq7");
        passwordpolicy.setMaxPwdLength(32);
        passwordpolicy.setMinPwdLength(3);
        passwordpolicy.setMinCapitalLetters(4);
        passwordpolicy.setPolicyName("PrKhv8ZCPXkQpb3LO07c5PlKdxIxNX3j1LNSAwmTQ9xmQPpxH0");
        passwordpolicy.setPolicyDescription("y8zU7Hjjz4jIWbafDPT81oonORtOROVSIHrJpSTghr633YOjQa");
        passwordpolicy.setMinNumericValues(2);
        passwordpolicy.setEntityValidator(entityValidator);
        return passwordpolicy;
    }

    @Test
    public void test1Save() {
        try {
            PasswordPolicy passwordpolicy = createPasswordPolicy(true);
            passwordpolicy.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            passwordpolicy.isValid();
            passwordpolicyRepository.save(passwordpolicy);
            map.put("PasswordPolicyPrimaryKey", passwordpolicy._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            PasswordPolicy passwordpolicy = passwordpolicyRepository.findById((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
            passwordpolicy.setMinSmallLetters(8);
            passwordpolicy.setMinSpecialLetters(8);
            passwordpolicy.setAllowedSpecialLetters("1oxsNBt4W7HuCcug11zvcOXQbDY4HjQnAo66nSruNRNxPeYATz");
            passwordpolicy.setMaxPwdLength(28);
            passwordpolicy.setMinPwdLength(9);
            passwordpolicy.setVersionId(1);
            passwordpolicy.setMinCapitalLetters(5);
            passwordpolicy.setPolicyName("snnroOLRnwjNGJYUWN06e9XCnfPPiTnzYAMB6pzb4oJDl7gjjh");
            passwordpolicy.setPolicyDescription("XV90utj1JwtghhwYpkWdQlC0kagLHux5Bp17stbtxKzBobYYcQ");
            passwordpolicy.setMinNumericValues(10);
            passwordpolicy.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            passwordpolicyRepository.update(passwordpolicy);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            passwordpolicyRepository.findById((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            passwordpolicyRepository.delete((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validatePasswordPolicy(EntityTestCriteria contraints, PasswordPolicy passwordpolicy) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            passwordpolicyRepository.save(passwordpolicy);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "policyName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "policyName", "suB9S9iY2It7QapQwTmRosDZZQ0Ma85p5k1HwzjRmUWTSfCi6onZlxOGIvfbgHIANGZiZ5DaZSSjtRBRQVzjKe1yoNpzt9u0tidSmxEOje4krRxRmzY1UmuxCyBeVLUI6ugjvsmsMdlKlKNwQgLWnnZakRwG38WdR2MGxlaIp27DSkZ69GXwWIfMJsIBDUOtpUhDGKPJ8RwzofoHLUkf7z9VszCLhEBcb0iVgGzWombVKV7ZfjwoxkD6kvW7Cpc64"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "policyDescription", "BYmQ6IxlKI2FfelpZylaI0oGzO0IOcRNzzkOhhVSdZtf9Z2qNIuEdFFeXBLOMvobgT8LAB1B9p0FSnoSpYdpXOoa2VsgOhyR2fCdVVMT0zkdZ742QndJbqKBDjz5trzUPLcOdnI1UFur7ISfClPI2ft34yC9bQ6Ikz0e2TxOHneBhcPc0K8GeVeWa8I0DqzIza2ruwc7mwq3S186DCerDi2XpZWOe1rUU5n61pr3DxMNoKcK51yvnofWkrPTaaHJz"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "maxPwdLength", 48));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "minPwdLength", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "minPwdLength", 22));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "minCapitalLetters", 15));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "minSmallLetters", 21));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "minNumericValues", 16));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "minSpecialLetters", 16));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "allowedSpecialLetters", "KwtxQqhQMg5ISbvMJlYzVQKrBp1aYgLY9GnpG92r5NpdzUkd7JviGsxunm4MaRPwZoOrTpF6jA5zpCgyNgy4CSZq0yCwQwSzkP4GNnhWAVNLz3I7vB0l9pl1ZSHls9nawoShEeLVm3p7cOFGVwHY7Vbz34aUJp0MgUCJSkzxSh3r0WN743MFxIv87ycDEjNGRbpThSyCwwyM2IzcDcBmIv2ieZJzbErktpufG9w2sQKXVmVB0XRvtr1ZY3qlMnvnU"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                PasswordPolicy passwordpolicy = createPasswordPolicy(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = passwordpolicy.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(passwordpolicy, null);
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 2:
                        passwordpolicy.setPolicyName(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 3:
                        passwordpolicy.setPolicyDescription(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 4:
                        passwordpolicy.setMaxPwdLength(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(passwordpolicy, null);
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 6:
                        passwordpolicy.setMinPwdLength(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 7:
                        passwordpolicy.setMinCapitalLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 8:
                        passwordpolicy.setMinSmallLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 9:
                        passwordpolicy.setMinNumericValues(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 10:
                        passwordpolicy.setMinSpecialLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 11:
                        passwordpolicy.setAllowedSpecialLetters(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
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
