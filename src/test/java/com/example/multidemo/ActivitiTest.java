package com.example.multidemo;

import com.example.multidemo.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiTest {
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    private SecurityUtil securityUtil;

    /**
     * 手动部署
     */
//    @Test
//    public void testDeployment() {
//        DeploymentBuilder builder = repositoryService.createDeployment();
//        builder.addClasspathResource("processes/Process1.bpmn20.xml");
//        builder.addClasspathResource("processes/Process1.png");
//        builder.name("first_activiti_process");
//        Deployment deployment = builder.deploy();
//        //输出部署信息
//        log.info("流程部署id：{}", deployment.getId());
//        log.info("流程部署名称：{}", deployment.getName());
//    }

    /**
     * 分页查询流程
     */
    @Test
    public void testFindProcess() {
        securityUtil.logInAs("user1");
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        processRuntime.processDefinitions(Pageable.of(0, 10));
        log.info("流程总数： {}", processDefinitionPage.getTotalItems());
        for (ProcessDefinition processDefinition : processDefinitionPage.getContent()) {
            log.info("流程内容： {}", processDefinition);
        }
    }
}
