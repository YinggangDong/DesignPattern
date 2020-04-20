package cn.dyg.templatepattern.service;

import cn.dyg.templatepattern.domain.assessment.AssessmentResultDto;
import cn.dyg.templatepattern.domain.assessment.ItemParam;
import cn.dyg.templatepattern.util.Constants;
import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractRateTemplate类(接口)是 计算百分数结果的模板
 * 继承了BaseAssessmentTemplate的一个抽象类,相当于在模板之后又新增了一个模板
 * 在本程序中实际作为漏检率、开发返工率、任务按时完成率的实际模板
 *
 * @author dongyinggang
 * @date 2020-04-19 17:30
 **/
abstract class AbstractRateTemplate extends BaseAssessmentTemplate {

    /**
     * getAssessmentResult方法是 根据参数得到考核结果
     * <p>
     * 实现了模板中的这个抽象方法
     *
     * @param itemParamList 考核项的可配置参数
     * @return 考核结果
     * @author dongyinggang
     * @date 2020/4/16 16:10
     */
    @Override
    List<AssessmentResultDto> getAssessmentResult(List<ItemParam> itemParamList) {
        List<AssessmentResultDto> assessmentList = new ArrayList<>();

        //获取随机生成的考核值数组
        List<Double> rateList = getAssessmentValue();
        //如果为空,直接返回空list
        if (CollectionUtil.isEmpty(rateList)) {
            return assessmentList;
        }
        //记录当前已经遍历到有序数组的哪个位置
        int index = 0;
        //获取每个等级的人数
        for (ItemParam itemParam : itemParamList) {
            //根据总人数设置当前等级人数的理论值
            itemParam.setCurrentGradePersonNum(rateList.size());
            //设置当前等级的统计结果,并返回进行到了哪一个下标
            index = getRangeIndex(itemParam, assessmentList, rateList, index);
            //如果的已经到了数组的最后,则考核项结果已经全部生成,进行return,不再继续循环
            if (index == rateList.size() - 1) {
                return assessmentList;
            }
        }

        return assessmentList;
    }

    /**
     * getAssessmentValue方法是 获取随机生成的(任务按时完成率)考核值数组
     *
     * @return 倒序排列的考核值数组
     * @author dongyinggang
     * @date 2020/4/17 15:33
     */
    abstract List<Double> getAssessmentValue();

    /**
     * getRangeIndex方法是 设置当前等级的统计结果,并返回进行到了哪一个下标
     *
     * @param itemParam      当前等级的初始信息
     * @param assessmentList 最终返回的list结果
     * @param rateList       存储考核项值的数组
     * @param index          上一个等级进行到了哪个下标
     * @return 当前等级进行到了哪个下标
     * @author dongyinggang
     * @date 2020/4/19 13:57
     */
    private int getRangeIndex(ItemParam itemParam, List<AssessmentResultDto> assessmentList,
                              List<Double> rateList, int index) {

        //前一个等级的最后位置+当前等级的人数就是本等级最终理论应该达到的位置,但如果已经超出数组,则将其赋值为数组最后一位
        int rangeIndex = itemParam.getPersonNum() + index > rateList.size() - 1 ?
                rateList.size() - 1 : itemParam.getPersonNum() + index;
//        String interval = calInterval(itemParam, rateList, index,rangeIndex);

        //尝试通过钩子方法控制流程进行区间计算
        String interval;
        if (Constants.DESC.equals(intervalHock())) {
            interval = CommonService.calIntervalDesc(itemParam, rateList, index, rangeIndex);
        } else if (Constants.ASC.equals(intervalHock())) {
            interval = CommonService.calIntervalAsc(itemParam, rateList, index, rangeIndex);
        } else {
            //未实现钩子方法的可以采用子类自行实现的方式
            interval = calInterval(itemParam, rateList, index, rangeIndex);
        }

        //获取到当前等级的实际最终位置,如果界限出现相等值，会全部算作更高的考核级别
        int actualRangeIndex = rateList.lastIndexOf(rateList.get(rangeIndex));

        //人数是实际最终下标-开始下标,如果是A的话,因为从0开始,要进行+1
        int personNum = actualRangeIndex - index;
        if ("A".equals(itemParam.getGrade())) {
            personNum++;
        }
        CommonService.setGradeInfo(itemParam, assessmentList, interval, personNum);

        return actualRangeIndex;
    }

    /**
     * calInterval方法是 计算区间
     * 具体计算的是降序list的区间(考核项值越大越好),还是升序list的区间(考核项值越小越好)由实现类自己决定
     *
     * @param itemParam      考核项基础信息
     * @param assessmentList 考核项值的升序list
     * @param index          上一个等级结束的位置
     * @param rangeIndex     当前等级的结束位置
     * @return 计算得到的区间值
     * @author dongyinggang
     * @date 2020/4/19 15:43
     */
    abstract String calInterval(ItemParam itemParam, List<Double> assessmentList,
                                int index, int rangeIndex);

    /**
     * intervalHock方法是 钩子方法,子类可以覆盖这个方法做一定处理(但非必须),并依此做到控制模板流程
     * <p>
     * 在这里通过返回不同desc或asc来控制计算区间调用的方法
     *
     * @author dongyinggang
     * @date 2020/4/16 18:33
     */
    String intervalHock() {
        return "";
    }

}
