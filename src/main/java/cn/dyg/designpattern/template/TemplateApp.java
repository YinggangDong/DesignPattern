package cn.dyg.designpattern.template;

import cn.dyg.designpattern.template.service.BaseAssessmentTemplate;
import cn.dyg.designpattern.template.service.CaseNumService;
import cn.dyg.designpattern.template.service.MissCheckRateService;
import cn.dyg.designpattern.template.service.ReworkRateService;
import cn.dyg.designpattern.template.service.TaskCompleteAbstractRateService;

/**
 * TemplateApp 类是 模板设计模式样例启动类
 *
 * @author dongyinggang
 * @date 2020-07-15 11:49
 **/
public class TemplateApp {
    public static void main(String[] args) {
        BaseAssessmentTemplate caseNumService = new CaseNumService();
        caseNumService.execute();

        BaseAssessmentTemplate missCheckRateService = new MissCheckRateService();
        missCheckRateService.execute();

        ReworkRateService reworkRateService = new ReworkRateService();
        reworkRateService.execute();

        TaskCompleteAbstractRateService taskCompleteRateService = new TaskCompleteAbstractRateService();
        taskCompleteRateService.execute();
    }
}
