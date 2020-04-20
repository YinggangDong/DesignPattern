package cn.dyg.templatepattern.service;

import cn.dyg.templatepattern.domain.assessment.AssessmentResultDto;
import cn.dyg.templatepattern.domain.assessment.ItemParam;

import java.util.List;

/**
 * BaseAssessmentTemplate 类（或接口）是 考核模板
 *
 * @author dongyinggang
 * @date 2020/4/16
 */
public abstract class BaseAssessmentTemplate {

    /**
     * execute方法是 模板方法
     *
     * @author dongyinggang
     * @date 2020/4/16 18:16
     */
    public final void execute(){

        //生产中由各子类可以查询数据库获取
        List<ItemParam> itemParamList = getItemParam();

        //各子类根据规则进行排序
        List<AssessmentResultDto> resultList = getAssessmentResult(itemParamList);

        //输出结果
        printAssessmentResult(resultList);
    }

    /**
     * getItemParam方法是 获取考核项的参数
     *
     * @return ItemParam 参数列表
     * @author dongyinggang
     * @date 2020/4/16 16:05
     */
    abstract List<ItemParam> getItemParam();

    /**
     * getAssessmentResult方法是 根据参数得到考核结果
     *
     * @param itemParamList 考核项的可配置参数
     * @return  考核结果
     * @author dongyinggang
     * @date 2020/4/16 16:10
     */
    abstract List<AssessmentResultDto> getAssessmentResult(List<ItemParam> itemParamList);

    /**
     * printAssessmentResult方法是 输出考核结果
     *
     * @param resultList 考核结果
     * @author dongyinggang
     * @date 2020/4/16 16:15
     */
    private void printAssessmentResult(List<AssessmentResultDto> resultList){
        if(resultList.isEmpty()){
            System.out.println("未查询到该考核项数据,未生成汇总数据!");
            return;
        }
        System.out.println("该考核项汇总信息如下：");
        System.out.printf("%-10s %-5s %-5s %-20s %-5s %-5s %n","考核项","结果","比例","区间","人数","考核得分");
        for(AssessmentResultDto assessmentResult:resultList){
            assessmentResult.printResult();
        }
        //打印空行,分隔开,空两行
        System.out.println("\n");
    }

    /**
     * hock方法是 钩子方法,子类可以覆盖这个方法做一定处理(但非必须),并依此做到控制模板流程
     *
     * @author dongyinggang
     * @date 2020/4/16 18:33
     */
    String hock(){
        return "";
    }

}
