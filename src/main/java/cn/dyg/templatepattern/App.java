package cn.dyg.templatepattern;

import cn.dyg.templatepattern.service.BaseAssessmentTemplate;
import cn.dyg.templatepattern.service.CaseNumService;
import cn.dyg.templatepattern.service.MissCheckRateService;
import cn.dyg.templatepattern.service.ReworkRateService;
import cn.dyg.templatepattern.service.TaskCompleteAbstractRateService;

/**
 * App类(接口)是 程序入口
 *
 * @author dongyinggang
 * @date 2020-04-19 14:27
 **/
public class App {

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
