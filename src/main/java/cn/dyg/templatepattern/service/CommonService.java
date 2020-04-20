package cn.dyg.templatepattern.service;

import cn.dyg.templatepattern.domain.assessment.AssessmentResultDto;
import cn.dyg.templatepattern.domain.assessment.ItemParam;
import cn.hutool.core.util.NumberUtil;

import java.util.List;

/**
 * CommonService类(接口)是 公共业务接口
 *
 * @author dongyinggang
 * @date 2020-04-19 14:26
 **/
public class CommonService {

    /**
     * calIntervalAsc方法是 计算区间,计算的是升序list的区间(考核项值越小越好)
     *
     * 漏检率、开发返工比率
     *
     * @param itemParam 考核项基础信息
     * @param assessmentList 考核项值的升序list
     * @param index 上一个等级结束的位置
     * @param rangeIndex 当前等级的结束位置
     * @return 区间值
     * @author dongyinggang
     * @date 2020/4/19 15:43
     */
    public static String calIntervalAsc(ItemParam itemParam, List<Double> assessmentList,
                                        int index, int rangeIndex) {
        //定义区间默认值"~"代表当前值
        String interval = "~";
        //获取各等级的上下限,如果不是A,则说明漏检率有左值
        if (!"A".equals(itemParam.getGrade())) {
            //左值即被大于的那个数,是更高等级边界值
            double leftValue = assessmentList.get(index) ;
            interval =  leftValue+ "%<" + interval ;
        }

        //如果不是D,则说明漏检有右值
        if (!"D".equals(itemParam.getGrade())) {
            //右值即被小于等于的那个数,是当前等级的边界值
            double rightValue = assessmentList.get(rangeIndex);
            interval += "<=" + rightValue+"%";
        }
        return interval;
    }

    /**
     * calIntervalDesc方法是 计算区间,计算的是降序list的区间(考核项值越大越好)
     *
     * @param itemParam 考核项基础信息
     * @param assessmentList 考核项值的升序list
     * @param index 上一个等级结束的位置
     * @param rangeIndex 当前等级的结束位置
     * @return 计算得到的区间值
     * @author dongyinggang
     * @date 2020/4/19 15:43
     */
    public static String calIntervalDesc(ItemParam itemParam, List<Double> assessmentList,
                                  int index, int rangeIndex){
        //定义区间默认值"~"代表当前值
        String interval = "~";
        //获取各等级的上下限,如果不是D,则说明有左值
        if (!"D".equals(itemParam.getGrade())) {
            //左值即被大于的那个数,是当前等级边界值
            double leftValue = NumberUtil.sub(assessmentList.get(rangeIndex),(Double)0.01) ;
            interval =  leftValue+ "%<" + interval ;
        }

        //如果不是A,则说有右值
        if (!"A".equals(itemParam.getGrade())) {
            //右值即被小于等于的那个数,是更高等级的边界值
            double rightValue = NumberUtil.sub(assessmentList.get(index),(Double)0.01);
            interval += "<=" + rightValue + "%";
        }
        return interval;
    }

    /**
     * setGradeInfo方法是 设置当前考核项当前等级的相关信息
     *
     * @param itemParam 当前考核项内容
     * @param assessmentList 考核项统计结果list
     * @param interval  区间
     * @param personNum 人数
     * @author dongyinggang
     * @date 2020/4/19 16:29
     */
    public static void setGradeInfo(ItemParam itemParam, List<AssessmentResultDto> assessmentList,
                                    String interval, int personNum) {
        //根据计算结果设置当前grade的属性
        AssessmentResultDto assessmentResultDto = new AssessmentResultDto();
        assessmentResultDto.setAssessmentItem(itemParam.getItemName());
        assessmentResultDto.setGrade(itemParam.getGrade());
        assessmentResultDto.setPercent(itemParam.getPercent());
        assessmentResultDto.setInterval(interval);
        assessmentResultDto.setPersonNum(personNum);
        assessmentResultDto.setScore(itemParam.getScore());

        assessmentList.add(assessmentResultDto);
    }
}
